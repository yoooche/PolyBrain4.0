<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>PolyBrain - 會員註冊</title>
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body class="bg-gradient-dark">
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
            <div class="col-lg-5 d-none d-lg-block bg-register-image" style="background-image: url('/PolyBrain/view/member/image/註冊畫面圖.jpg');"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">註冊會員</h1>
                        </div>
                        <form class="user" method="POST" action="<%= request.getContextPath() %>/general/RegistServlet/do">
                            <!-- 姓名 -->
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="fullName"
                                       name="name" placeholder="姓名" required>
                            </div>
                            <!-- 身分证 -->
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="pid"
                                       name="pid" placeholder="身分證" pattern="[a-zA-Z]\d{9}" required>
                            </div>
                            <!-- 性别 -->
                            <div class="form-group" style="width: 100%; height: auto;">
                                <select class="form-control form-control-user" id="gender" name="gender" required>
                                    <option value="" disabled selected>選擇性別</option> <!-- 默认选项 -->
                                    <option value="1">男</option>
                                    <option value="0">女</option>
                                </select>
                            </div>
                            <!-- 信箱 -->
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" id="email"
                                       name="email" placeholder="輸入信箱" required>
                            </div>
                            <!-- 密码 -->
                            <div class="form-group">
                                <input type="password" class="form-control form-control-user"
                                       id="password" name="password" placeholder="密碼" required>
                            </div>
                            <!-- 电话 -->
                            <div class="form-group">
                                <input type="tel" class="form-control form-control-user" id="phone"
                                       name="phone" placeholder="電話" pattern="\d{10}" required>
                            </div>
                            <!-- 地址 -->
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="address"
                                       name="address" placeholder="地址">
                            </div>
                            <!-- 生日 -->
                            <div class="form-group">
                                <input type="date" class="form-control form-control-user" id="birth"
                                       name="birth">
                            </div>


                            <!-- 显示错误消息 -->
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">${error}</div>
                            </c:if>


                            <!-- 提交按钮 -->
                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                註冊
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

                        <hr>
                        <div class="text-center">
                            <a class="small" href="forgot-password.jsp">忘記密碼?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="login.html">已經有帳號了 ? 點此登入 !</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>