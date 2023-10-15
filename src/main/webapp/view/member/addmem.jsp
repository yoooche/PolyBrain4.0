<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />



    <link href="http://localhost:8080/PolyBrain/view/member/css/styles.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/cb31023646.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>員工資料新增 - addEmp.jsp</title>

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
                </nav>
                	<div align="center">
                	<br>
                	<br>
                		<h3><b>所有欄位皆為必填欄位</b></h3>
                		<form action="<%=request.getContextPath()%>/general/addmemServlet/do" method="post"
                        <!-- 姓名 -->
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="fullName"
                                name="name" placeholder="姓名" required>
                        </div>
                        <br>
                        <!-- 身分证 -->
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="pid"
                                name="pid" placeholder="身分證" pattern="[a-zA-Z]\d{9}" required>
                        </div>
                        <br>
                        <!-- 性别 -->
                        <div class="form-group" style="width: 100%; height: auto;">
                        <select class="form-control form-control-user" id="gender" name="gender" required>
                            <option value="" disabled selected>選擇性別</option> <!-- 默认选项 -->
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                        </div>
                        <br>
                        <!-- 信箱 -->
                        <div class="form-group">
                            <input type="email" class="form-control form-control-user" id="email"
                                name="email" placeholder="輸入信箱" required>
                        </div>
                        <br>
                        <!-- 密码 -->
                        <div class="form-group">
                            <input type="password" class="form-control form-control-user"
                                id="password" name="password" placeholder="密碼" required>
                        </div>
                        <br>
                        <!-- 电话 -->
                        <div class="form-group">
                            <input type="tel" class="form-control form-control-user" id="phone"
                                name="phone" placeholder="電話" pattern="\d{10}" required>
                        </div>
                        <br>
                        <!-- 地址 -->
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="address"
                                name="address" placeholder="地址">
                        </div>
                        <br>
                        <!-- 生日 -->
                        <div class="form-group">
                            <input type="date" class="form-control form-control-user" id="birth"
                                name="birth">
                        </div>


                        <!-- 显示错误消息 -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>
                        <br>

                        <!-- 提交按钮 -->
                        <button type="submit" class="btn btn-primary btn-user btn-block">
                            送出新增
                        </button>
                        </form>

                        <!-- 显示错误消息 -->
                        <div class="alert alert-danger" id="nameError" style="display: none;">
                            請輸入完整格式。
                        </div>
                        <div class="alert alert-danger" id="pidError" style="display: none;">
                            請輸入完整格式。
                        </div>
                        <div class="alert alert-danger" id="pidExistsError" style="display: none;">
                            此身分證已被註冊。
                        </div>
                        <div class="alert alert-danger" id="genderError" style="display: none;">
                            請選擇性別。
                        </div>
                        <div class="alert alert-danger" id="emailError" style="display: none;">
                            請輸入完整格式。
                        </div>
                        <div class="alert alert-danger" id="emailExistsError" style="display: none;">
                            此信箱已被註冊。
                        </div>
                        <div class="alert alert-danger" id="phoneError" style="display: none;">
                            請輸入正確格式。
                        </div>
                        <div class="alert alert-danger" id="addressError" style="display: none;">
                            請輸入完整地址。
                        </div>
                        <div class="alert alert-danger" id="birthError" style="display: none;">
                            請選擇生日。
                        </div>
            </main>
<!-----------------------------以上為內容---------------------------->
        </div>
    </div>
</body>
</html>
