<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="feature.mem.service.*"%>
<%@page import="feature.mem.controller.*"%>
<%@page import="feature.mem.dao.*"%>
<%@page import="feature.mem.vo.*"%>

    <link href="http://localhost:8080/PolyBrain/view/member/css/styles.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/cb31023646.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>會員資料</title>

<style type="text/css">

    span {
		display: inline-block;
		width: 150px;
		text-align: left;
		font-weight: bold;
	}

</style>
</head>
<body id="page-top">
<!---------------------------------------------以下為頂板--------------------------------------------------------->
<main class="flex-shrink-0">
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: black;">
        <div class="container px-5">
            <img src="${pageContext.request.contextPath}/view/logo/PolyBrain_Logo.png" style="width: 110px; height: auto; margin-bottom: 5px;"></a>
        </div>
    </nav>
</main>
<!---------------------------------------------以上為頂板--------------------------------------------------------->



    <div id="layoutSidenav">  <!--勿刪到-->
<!---------------------------------------------以下為側邊攔--------------------------------------------------------->
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">

                                <!-------------會員中心-------------->
                        <br>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#mem" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            會員
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="mem" aria-labelledby="headingOne"data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="http://localhost:8080/PolyBrain/view/member/listAllmem.jsp"><div class="sb-nav-link-icon"></div>所有會員管理</a>
                                <a class="nav-link" href="http://localhost:8080/PolyBrain/view/forum/Listallarti.jsp"><div class="sb-nav-link-icon"></div>討論區文章管理</a>
                            </nav>
                        </div>
                                <!-------------會員中心-------------->
                                <!--------------商城---------------->
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#item" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            商城
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="item" aria-labelledby="headingOne"data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="http://localhost:8080/PolyBrain/view/item/itemConsole.html"><div class="sb-nav-link-icon"></div>商城管理</a>
                                <a class="nav-link" href="http://localhost:8080/PolyBrain/view/item/itemClassConsole.html"><div class="sb-nav-link-icon"></div>商品類別管理</a>
                                <a class="nav-link" href="http://localhost:8080/PolyBrain/view/order/listAllOrder.jsp"><div class="sb-nav-link-icon"></div>訂單管理</a>
                            </nav>
                        </div>
                                <!-------------商城-------------->
                                <!-------------預約-------------->
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#booking" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                           預約
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="booking" aria-labelledby="headingOne"data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/book/BookTable.html"><div class="sb-nav-link-icon"></div>報到管理</a>
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/bookingtast/Modifytimeperiod.jsp"><div class="sb-nav-link-icon"></div>時段管理</a>
                            </nav>
                        </div>
                                <!-------------預約-------------->
                                <!-------------競標-------------->
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#bid" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                           競標
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="bid" aria-labelledby="headingOne"data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/bid/BidEventList.jsp"><div class="sb-nav-link-icon"></div>競標活動管理</a>
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/bid/BidItemTable.html"><div class="sb-nav-link-icon"></div>競標商品管理</a>
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/order/bidOrderBackend.html"><div class="sb-nav-link-icon"></div>競標訂單</a>
                            </nav>
                        </div>
                                <!-------------競標-------------->
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/emp/allEmpList.jsp"><div class="sb-nav-link-icon"></div>
                            所有員工管理
                        </a>
                        <hr>
                        <a class="nav-link" href="http://localhost:8080/PolyBrain/view/member/back_logout.jsp"><div class="sb-nav-link-icon"></div>
                            後台登出
                        </a>
                    </div>
                </div>
            </nav>
        </div>
<!---------------------------------------------以上為側邊攔--------------------------------------------------------->


        <div id="layoutSidenav_content"> <!--勿刪到-->
<!-----------------------------以下為內容---------------------------->
            <main>
                    <br>
                	<div align="center"> <h2>會員個人資料</h2>
                	<h3 style="color: gray;"><a class="navbar-brand" href="<%=request.getContextPath()%>/view/member/listAllmem.jsp">回查詢頁${success}</a></h3></div>
                    <hr>
                	<div align="center">
                	<br><br>
                    		<h3><span>查詢結果 :</span></h3>
                    		<br>
                    		<span>會員編號:</span><span>${memVo.memNo}</span><br/>
                    		<span>會員姓名:</span><span>${memVo.memName}</span><br/>
                    		<span>身分證:</span><span>${memVo.memPid}</span><br/>
                    		<span>信箱:</span><span>${memVo.memEmail}</span><br/>
                    		<span>手機:</span><span>${memVo.memPh}</span><br/>
                    		<span>地址:</span><span>${memVo.memAddress}</span><br/>
                    		<span>生日:</span><span>${memVo.memBirth}</span><br/>
                    		<span>權限:</span><span>${memVo.memAuth}</span><br/>
                    </div>
            </main>
<!-----------------------------以上為內容---------------------------->
        </div>
    </div>
</body>
</html>
