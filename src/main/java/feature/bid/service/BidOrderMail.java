package feature.bid.service;


import feature.bid.dao.BidOrderDaoImpl;
import feature.bid.vo.BidOrderVo;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

public class BidOrderMail {
    private BidOrderDaoImpl bidOrderDao;
    private MemDaoImpl memDao;


    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendBidOrderMail(Integer orderNo) {
        Integer memNo = null;
        Integer orderTotal = null;


        if (orderNo >= 1) {
            System.out.println("這是一筆競標訂單");
            bidOrderDao = new BidOrderDaoImpl();
            BidOrderVo bidOrderVo = bidOrderDao.selectById(orderNo);
            memNo = bidOrderVo.getMemNo();
            System.out.println(memNo);
            System.out.println("訂單編號" + orderNo);
            orderTotal = bidOrderVo.getFinalPrice();
            System.out.println(orderTotal);
        } else {
            return;
        }


        try {
            memDao = new MemDaoImpl();
            MemVo memVo = memDao.selectById(memNo);
            String memName = memVo.getMemName();
            String memEmail = memVo.getMemEmail();
            System.out.println(memName);
            System.out.println(memEmail);


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
            messageHelper.setSubject("PolyBrain訂單資訊");
            messageHelper.setText("<!DOCTYPE html>" +
                    "<html lang=\"zh-Hant\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "</head>" +
                    "<body style=\"color: #F0F8FF; text-align: center;\">" +
                    "    <div style=\"background-color: #212121; border-radius: 5px; color: #F0F8FF;\">" +
                    "        <dir style=\"text-align: center;\">" +
                    "            <img class=\"mt-3 \" src=\"cid:1 \" height=\"50%\" width=\"50%\">" +
                    "        </dir>" +
                    "        <h2 style=\"text-align: center;\"><strong>PolyBrain 桌桌</strong> 感謝你的訂單</h2><br>" +
                    "        <div style=\"display: inline-block; text-align: left; color: #F0F8FF;\">" +
                    "            <p style=\"font-size: 1.1rem; color: #F0F8FF;\">訂單狀態為: <span style=\"font-weight: bold;\">處理中</span> <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">付款狀態為: <span style=\"font-weight: bold;\">已付款 </span><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">送貨狀態為:<span style=\"font-weight: bold;\"> 備貨中 </span> <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">有關訂單的查詢或要聯絡 <span style=\"font-weight: bold;\">PolyBrain</span><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">請登入以下連結。<br></p>" +
                    "            <button style=\"background-color: #d2b48c; border-radius:5px; width: 85%; height: 40px;\">" +
                    "                <a href=\"http://localhost:8080/PolyBrain/view/bid/BidOnHomePage.html\"" +
                    "                    style=\"text-decoration: none;color: #000000;font-size: 1.1rem;\">訂單連結</a></button><br>" +
                    "            <p style=\"font-size: 1.1rem; color: #F0F8FF;\">親愛的貴賓:<strong> " + memName.toString() + "</strong> <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">感謝您在<strong>PolyBrain桌桌訂購</strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">以下是訂單資訊 <br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">訂單號碼: <strong>" + orderNo + "</strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">結帳狀態: <strong>已付款 </strong><br></p>" +
                    "            <p style=\"font-size: 1.1rem;color: #F0F8FF;\">訂單總金額:<strong> " + orderTotal.toString() + " </strong><br></p>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>", true);

//            FileSystemResource img = new FileSystemResource(
//                    new File("http://localhost:8080/PolyBrain/view/lgog/PolyBrain_Logo.png"));
//                            //這個路徑要修正
//            messageHelper.addInline("1", img);

            // 傳送郵件
            senderImpl.send(mailMessage);

            System.out.println("傳送成功");

        } catch (Exception e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

}

