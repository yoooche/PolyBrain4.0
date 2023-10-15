package feature.forum.controller;


import feature.forum.service.ArtService;
import feature.forum.vo.ArtVo;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class ArtServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        doPost(req,res);
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        String action =req.getParameter("action");

        if("getOne_For_Update".equals(action)){
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs",errorMsgs);

           Integer artNo = Integer.valueOf(req.getParameter("artNo"));

           ArtService ArtSvc =new ArtService();
           ArtVo artVo =ArtSvc.getOneArt(artNo);



           String param = "?artNo=" + artVo.getArtNo()+
                          "&memNo="+artVo.getMemNo()+
                          "&artTitle="+artVo.getArtTitle()+
                          "&artCon="+artVo.getArtCon()+
                          "&artTime="+artVo.getArtTime()+
                          "&artState="+artVo.getArtState()+
                          "&itemNo="+artVo.getItemNo()+
                          "&upFiles="+artVo.getUpFiles();

//           req.setAttribute("artVo", artVo);
           String url ="/view/forum/mainpage/Update_Art_input.jsp"+param;
           RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("update".equals(action)){
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs",errorMsgs);

            System.out.println("----------------------------");

            Integer artNo =null;
            try {
                artNo = Integer.valueOf(req.getParameter("artNo").trim());
            }catch (NumberFormatException e){
                errorMsgs.put("artNo","貼文編號請勿空白");
            }

            Integer memNo =null;
            try {
                memNo =Integer.valueOf(req.getParameter("memNo").trim());
            }catch (NumberFormatException e){
                errorMsgs.put("memNo","請輸入會員編號");
            }


            String artTitle =req.getParameter("artTitle");
            if(artTitle == null || artTitle.trim().length() == 0){
                errorMsgs.put(artTitle,"貼文主題請勿空白");
            }

            String artCon =req.getParameter("artCon");
            if(artCon == null || artCon.trim().length() == 0){
                errorMsgs.put(artCon,"貼文內容請勿空白");
            }

            Timestamp artTime = new Timestamp(System.currentTimeMillis());

            Byte artState =null;
            try {
                artState =Byte.valueOf(req.getParameter("artState").trim());
            }catch (NumberFormatException e){
                errorMsgs.put("artState","貼文狀態請勿空白");
            }
            Integer itemNo =null;
            try {
                itemNo =Integer.valueOf(req.getParameter("itemNo").trim());
            }catch (NumberFormatException e){
                errorMsgs.put("itemNo","    遊戲類別請勿空白");
            }

            InputStream in = req.getPart("upFiles").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
            byte[] upFiles = null;
            if(in.available()!=0){
                upFiles = new byte[in.available()];
                in.read(upFiles);
                in.close();
            }  else {
                ArtService artSvc = new ArtService();
                upFiles = artSvc.getOneArt(artNo).getUpFiles();
            }






            if (!errorMsgs.isEmpty()){
                errorMsgs.put("Exception","修改資料失敗:-------");
                RequestDispatcher failureView =req
                        .getRequestDispatcher("/view/forum/mainpage/Update_Art_input.jsp");
                failureView.forward(req,res);
                return;
            }


            ArtService artSvc =new ArtService();
            ArtVo artVo =artSvc.updateArt(artNo,memNo,artTitle,artCon,artTime,artState,itemNo,upFiles);

            req.setAttribute("success","- (修改成功)");
            req.setAttribute("ArtVo",artVo);
            String url ="/view/forum/mainpage/Listallarti.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req,res);


        }

        if ("insert".equals(action)){

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs",errorMsgs);

            Integer memNo =Integer.valueOf(req.getParameter("memNo"));

            Integer itemNo =Integer.valueOf(req.getParameter("itemNo"));

                String artTitle =req.getParameter("artTitle");
                if(artTitle == null || artTitle.trim().length() == 0){
                    errorMsgs.put(artTitle,"貼文主題請勿空白");
                }

                String artCon =req.getParameter("artCon");
                if(artCon == null || artCon.trim().length() == 0){
                    errorMsgs.put(artCon,"貼文內容請勿空白");
                }

                if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/view/forum/addnewpage.jsp");
                failureView.forward(req, res);
                return;
            }
            InputStream in = req.getPart("upFiles").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
            byte[] upFiles = null;
            if(in.available()!=0){
                upFiles = new byte[in.available()];
                in.read(upFiles);
                in.close();
            }

            ArtService artSvc =new ArtService();
            artSvc.addArt(memNo,artTitle,artCon,itemNo,upFiles);

            req.setAttribute("success","-(新增成功)");

            String url ="/view/forum/mainpage/Listallarti.jsp";
            RequestDispatcher successView =req.getRequestDispatcher(url);
            successView.forward(req,res);

        }

        if("delete".equals(action)){
            Map<String,String> errorMsgs =new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs",errorMsgs);

            Integer artNo =Integer.valueOf(req.getParameter("artNo"));

            ArtService artSvc =new ArtService();
            artSvc.deleteArt(artNo);

            req.setAttribute("success","-(刪除成功)");
            String url = "/view/forum/mainpage/Listallarti.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req,res);
        }
    }

}