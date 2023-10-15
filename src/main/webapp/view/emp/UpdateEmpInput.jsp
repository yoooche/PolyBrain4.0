<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="feature.emp.service.*"%>
<%@page import="feature.emp.controller.*"%>
<%@page import="feature.emp.dao.*"%>
<%@page import="feature.emp.vo.*"%>

    <link href="http://localhost:8080/PolyBrain/view/member/css/styles.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/cb31023646.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>員工資料修改</title>

<style type="text/css">

	button {
		padding: 5px;
	}
	form {
		display: table;
	}
	form div {
		display: table-row;
	}
	label, input, span, select {
		display: table-cell;
		margin: 5px;
		text-align: left;
	}
	input[type=text], input[type=password], select, textarea {
		width: 200px;
		margin: 5px;
	}
	form div div {
		display: table-cell;
	}
	.center {
        margin-left: auto;
        margin-right: auto;
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
        <br><br>
        <nav>
            <div align="center"> <h2>員工資料修改</h2>
        </nav>
        <br>
        <hr>
        	<div align="center">
        		<h3><b>所有欄位皆為必填欄位</b></h3>
        		<br>
        		<form action="<%=request.getContextPath()%>/general/allEmpListServlet/do" method="post">
        			<div>
        			<label for="empNo">會員編號:</label>
        			<input id ="empNo" name="empNo" type="text" value="${param.empNo}" style="border:0px ; font-weight: bold;" readonly />
        			</div>

        			<div>
        			<label for="empName">員工姓名:</label>
        			<input id ="empName" name="empName" type="text" value="${param.empName}" onclick="hideContent('empName.errors');" />
        			<span  id ="empName.errors" class="error">${errorMsgs.empName}<br/></span>
        			</div>

                    <div>
                        <label for="empGender">性別:</label>
                        <select id="empGender" name="empGender" onclick="hideContent('empGender.errors');">
                            <option value="0" ${param.empGender == '0' ? 'selected' : ''}>女</option>
                            <option value="1" ${param.empGender == '1' ? 'selected' : ''}>男</option>
                        </select>
                    </div>

        			<div>
        			<label for="empEmail">信箱:</label>
        			<input id ="empEmail" name="empEmail" type="text" value="${param.empEmail}" onclick="hideContent('empEmail.errors');" />
        			<span  id ="empEmail.errors" class="error">${errorMsgs.empEmail}</span>
        			</div>

        			<div>
        			<label for="empPwd">密碼:</label>
        			<input id ="empPwd" name="empPwd" type="text" value="${param.empPwd}" onclick="hideContent('empPwd.errors');" />
        			<span  id ="empPwd.errors" class="error">${errorMsgs.empPwd}</span>
        			</div>

        			<div>
                    <label for="empPh">手機:</label>
                    <input id ="empPh" name="empPh" type="text" value="${param.empPh}" onclick="hideContent('empPh.errors');" />
                    <span  id ="empPh.errors" class="error">${errorMsgs.empPh}</span>
                    </div>

                    <div>
                        <label for="empState">狀態:</label>
                        <select id="empState" name="empState" onclick="hideContent('empState.errors');">
                            <option value="0" ${param.empState == '0' ? 'selected' : ''}>離職</option>
                            <option value="1" ${param.empState == '1' ? 'selected' : ''}>在職中</option>
                            <option value="2" ${param.empState == '2' ? 'selected' : ''}>停職</option>
                        </select>
                    </div>
                    <br>
        			<div>
                    	<div></div>
                    	<input  type="hidden" name="action" value="update">
                    	<button type="submit" id="submit"> 送出修改 </button>
                    	<div></div>
                    </div>
        		</form>
        	</div>

    </main>
<!-----------------------------以上為內容---------------------------->
        </div>
    </div>
</body>
</html>