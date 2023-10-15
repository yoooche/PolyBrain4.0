<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="feature.emp.vo.*"%>
<%@ page import="feature.emp.service.*"%>
<%@ page import="feature.emp.dao.*"%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Login - SB Admin</title>
    <link rel="stylesheet" href="./css/style_PB.css"/>
    <link rel="stylesheet" href="./css/styles.css"/>
    <!--        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>-->
</head>
<body class="bg-dark">

<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5" >
                        <div class="card shadow-lg border-0 rounded-lg mt-5" style="width: 700px; height: 500px; margin: 0 -100px; background-color: rgb(60, 60, 60);">
                            <div style="background-color: rgb(60, 60, 60); text-align: center; padding: 10px; width: 505px;">
                                <a>
                                    <img src="../logo/PolyBrain_Logo.png" style="width: 200px; height: auto; margin-top: 30px; margin-left: 175px;">
                                </a>
                            </div>

                            <div style="text-align: right;">
                                <h1 class="h4 text-gray-900 mb-4" style="color: white; margin-right: 290px;">- 後台登入 -</h1>
                            </div>
                            <div class="card-body">
                                <form action="LoginController"  method="post">
                                    <div class="form-group" style="width: 400px; margin: 0 auto; display: flex; align-items: center;">
                                        <label for="email" style="color: white; flex: 1;">帳號</label>
                                        <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" autocomplete="username" style="height: 50px; width: 90%;">
                                    </div>
                                    <br>
                                    <div class="form-group" style="width: 400px; margin: 0 auto; display: flex; align-items: center;">
                                        <label for="password" style="color: white; flex: 1;">密碼</label>
                                        <input class="form-control" id="password" name="password" type="password" placeholder="Password" autocomplete="current-password" style="height: 50px; width: 90%;">
                                    </div>
                                    <div style="text-align: center;">
                                        <div id="errMsg"></div>
                                        <div class="d-flex align-items-center justify-content-between mt-4 mb-0"style="width: 400px; margin: 0 305;">
                                            <button id="btn_login"  class="btn btn-primary">登入</button>
                                    </div>
                                    <hr style="border-color: white;">
                                    <div style="text-align: center;">
                                        <a href="http://localhost:8080/PolyBrain/view/member/login.html">前台登入</a>
                                    </div>
                            </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</div>
<script src="./js/scripts.js"></script>
<script src="./js/login.js"></script>
<!--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>-->
</body>
</html>
