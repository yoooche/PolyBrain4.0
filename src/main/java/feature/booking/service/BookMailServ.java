package feature.booking.service;

import feature.booking.dao.BookingDaoImpl;
import feature.booking.vo.BookingVo;
import feature.booking.dao.BookingDao;
import feature.booking.vo.BookingVo;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;
import feature.order.dao.impl.ItemOrderDAOImpl;
import feature.order.vo.ItemOrderVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.sql.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

public class BookMailServ {
    private BookingDaoImpl bookingDao;
   // private BidOrderDaoImpl bidOrderDao;
    private MemDaoImpl memDao;

    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendMail(Integer BookNo) {

        Integer memNo = null;
        Integer orderTotal = null;
        Integer period = null;
        Date bookdate = null;
        String periodText = null;
        Integer tableno = null;


        if (BookNo >= 7001) {
            bookingDao = new BookingDaoImpl();
            BookingVo bookingVo = bookingDao.selectById(BookNo);
            memNo = bookingVo.getMemno();
            period = bookingVo.getPeriodtime();
            bookdate = bookingVo.getTabledate();
            tableno = bookingVo.getTableno();
            if (period == 0) {
                periodText = "上午";
            } else if (period == 1) {
                periodText = "下午";
            }else if(period == 2){
                periodText = "晚上";
            } else {
                periodText = "未知時段";
            }
        }

        //取得會員資料
        try {
            memDao = new MemDaoImpl();
            MemVo memVo = memDao.selectById(memNo);
            String memName = memVo.getMemName();
            String memEmail = memVo.getMemEmail();


            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            senderImpl.setHost("smtp.gmail.com");
            senderImpl.setPort(587);
            senderImpl.setUsername("ixlogic.wu@gmail.com");
            senderImpl.setPassword("ddjomltcnypgcstn");

            Properties props = senderImpl.getJavaMailProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            // 建立郵件訊息
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");

            // 設定收件人、寄件人、主題與內文
            messageHelper.setTo(memEmail);
            messageHelper.setFrom("noreply@baeldung.com");
            messageHelper.setSubject("PolyBrain預約資訊");
            messageHelper.setText("<!DOCTYPE html>" +
                    "<html lang=\"zh-Hant\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <meta name=\"viewport\" content=\"width = 100%, initial-scale=1.0\">" +
                    "</head>" +
                    "<body style=\"color: #F0F8FF; text-align: center;\">" +
                    "    <div style=\"background-color: #000000; border-radius: 5px; color: #000000; height: 100%; width: 60%;\">" +
                    "        <dir style=\"text-align: center;\">" +
                    "            <img class=\"mt-3 \" src=\"cid:1 \" height=\"100%\" width=\"100%\">" +
                    "        </dir>" +
                    "        <h2 style=\"text-align: center;\"><strong>PolyBrain 桌桌</strong> 感謝你的預約</h2><br>" +
                    "        <div style=\"display: inline-block; text-align: left; color: #F0F8FF;\">" +
                    "            <p style=\"font-size: 1.1rem; color: #F0F8FF;\">親愛的貴賓:<strong> " + memName.toString() + "</strong> <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">感謝您在<strong>PolyBrain桌桌預約</strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">以下是預約資訊 <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">預約單號: <strong>" + BookNo + "</strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">預約日期: <strong>" + bookdate + "</strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">預約時段:<strong> " + periodText + " </strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">桌號:<strong> " + tableno +"號桌" + " </strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #cf5656;\">注意事項:<br></p>"+
                    "            <p style=\"font-size: 1.1rem;color: #cf5656;\">1.如需取消請於當日前取消<br></p>"+
                    "            <p style=\"font-size: 1.1rem;color: #cf5656;\">2.請於時間內報到<br></p>"+
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>", true);

            FileSystemResource img = new FileSystemResource(
                    new File("C:\\PolyBrain3.0\\src\\main\\webapp\\view\\book\\PolyBrain_header.png"));
            //這個路徑要修正
            messageHelper.addInline("1", img);

            // 傳送郵件
            senderImpl.send(mailMessage);

            System.out.println("傳送成功");

        } catch (Exception e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        BookMailServ mailService = new BookMailServ();
        mailService.sendMail(100099);
    }

}