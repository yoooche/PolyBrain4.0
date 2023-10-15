<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 清除会话（注销用户）
    session.invalidate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>登出</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
        }

        .container {
            margin-top: 100px;
        }

        .card {
            border: none;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            text-align: center;
            padding: 20px;
        }

        .display-4 {
            font-size: 2.5rem;
            color: #333;
        }

        .lead {
            font-size: 1.25rem;
            color: #666;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        img {
            max-width: 150px;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <div class="card">
                <h1 class="display-4">已成功登出</h1>
                <p class="lead">如果想要重新登入，請<a href="login.jsp">點擊這裡</a>。</p>
                <img src="./images/PolyBrain_Logo.png"
            </div>
        </div>
    </div>
</div>

</body>
</html>