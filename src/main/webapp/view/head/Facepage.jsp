<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="feature.forum.service.ArtService"%>
<%@page import="feature.forum.vo.ArtVo"%>
<%
    String itemNo = request.getParameter("itemNo");
    if (itemNo==null) itemNo="1";
    ArtService artSvc = new ArtService();
    List<ArtVo> list = artSvc.findByItemNo(Integer.valueOf(itemNo));
    if(request.getAttribute("ArtListData")==null) pageContext.setAttribute("ArtListData",list);
%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Modern Business - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/cb31023646.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
    .btn-outline-dark {
    color: #ffffff;
    }
        .navbar-expand-lg .navbar-nav .nav-link {  /*拉寬標題間距*/
            padding-right: 1.5rem!important;

        }

                .search-right {
            margin-left: auto;
            }

         body {
             font-family: Arial, Helvetica, sans-serif;
             margin: auto;
         }

         .carousel-inner {
             height: 300px; /* 設定一個你希望的高度 */
             width: 70%;   /* 根據你的需求，可能需要定義具體的寬度 */
             margin: auto;
         }

         .carousel-inner img {
             /* 如果你希望圖片不超過某個尺寸，但仍然保持原始比例 */
            height: 300px;  /* 設定想要的高度 */
            width: 500px;   /* 設定想要的寬度 */

             /* 如果你想直接設定一個固定的尺寸，使用以下設定（但可能會失去原始比例） */
        /*
             width: 120px;   // 固定寬度
             height: 80px;   // 固定高度
        */

             /* 讓圖片在 Carousel 中水平和垂直居中 */
             display: block;
             margin: auto;
         }

        /* 輪播圖上方的字*/
        .centered-white-text { /* 輪播圖上方的字*/
            text-align: center; /* 文字置中 */
            color: white;       /* 字體顏色為白色 */
        }
    /* 卡片分頁 */
    .card.text-center {
    top: 5px;
    width: 800px;
    max-width: 800px;
    margin: 0 auto;  /* 將卡片置於頁面中央 */
}
    .fw-bolder {
    text-align: center;
}

/* 會員名稱調整 */
  #memName {
    line-height: 2.3;  /* 调整为所需的值 */
    text-shadow: 2px 2px 2px rgba(0,0,0,0.3);
    color: white;
    margin-left: 12px;
}

/* 改變分頁的字體位置 */
    .centered-tabs {
        display: flex;
        justify-content: center;
        gap: 20px;  /* 调整此值以更改链接之间的距离 */
    }

    .centered-tabs a {
        color: black;
        text-decoration: none;
    }

    .centered-tabs a:hover {
        text-decoration: underline; /* 悬停时的效果 */
    }

/* 改變標題的字體位置 */
 .table-title {
        text-align: left;
        color: black;
    }

    .table-title a {
        color: black;
        text-decoration: none;
    }

    .table-title a:hover {
        text-decoration: underline;  /* 悬停时的效果 */
    }
    </style>
</head>

<body class="d-flex flex-column h-100">
    <main class="flex-shrink-0">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a class="navbar-brand" href="../head/Facepage.jsp">
                    <img src="../logo/PolyBrain_Logo.png" style="width: 110px; height: auto; margin-bottom: 5px;"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation"><span
                        class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <!-- <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 nav-list"> 添加 nav-list 類別 -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="buyitem" href="#" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">商城</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="../item/search.html">商品資訊</a></li>
                                <li><a class="dropdown-item" href="../order/memberOrder.html">商城訂單查詢</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="buybid" href="#" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">競標</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="../bid/BidOnHomePage.html">熱門競標</a></li>
                                <li><a class="dropdown-item" href="../order/bidOrderFront.html">競標訂單查詢</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="mybooking" href="#" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">預約場地</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="../book/Calendar.html">現在預約</a></li>
                                <li><a class="dropdown-item" href="../book/BookingCheck.html">預約場地查詢</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="discuss" href="../forum/mainpage/index.jsp" role="button">討論區</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="memberdistrict" href="../member/Member_Information.jsp"
                                role="button">會員中心</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="../head/question.html">常見問題</a></li>
                        <li>
                            <a href="../CartTrace/Cart.jsp" id="submitLink"
                                class="btn btn-outline-dark" style="color: #ffffff">
                                <i class="bi-cart-fill me-1" style="color: #ffffff"></i>
                                購物車
                            </a>
                        </li>
                        <span id="memName" style="margin-left:12px;"></span>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                            <ul id="dropdown-menu" class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdown">
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <h2 class="centered-white-text">店家推薦</h2>
            <!-- 旋轉木馬區 -->
            <div class="container">
                <div id="blah" class="carousel slide" data-ride="carousel">
                    <!--indicators-->
                    <ul class="carousel-indicators">
                        <li data-target="#blah" data-slide-to="0" class="active"></li>
                        <li data-target="#blah" data-slide-to="1"></li>
                        <li data-target="#blah" data-slide-to="2"></li>
                        <li data-target="#blah" data-slide-to="3"></li>
                        <li data-target="#blah" data-slide-to="4"></li>
                        <li data-target="#blah" data-slide-to="5"></li>
                        <li data-target="#blah" data-slide-to="6"></li>
                        <li data-target="#blah" data-slide-to="7"></li>
                    </ul>

                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="../item/itemDetail.html?itemNo=101   " >
                                <img src="images/1.jpg" alt="crystal handle">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=102">
                                <img src="images/2.jpg" alt="a group of rods by me" >
                            </a>
                            <div class="carousel-caption"> <!-- Example caption -->
                                <h3></h3>
                                <p></p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=103">
                                <img src="images/3.jpg" alt="black rod" >
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=104">
                                <img src="images/17.jpg" alt="royal blue rod">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=105">
                                <img src="images/20-1.jpg" alt="royal blue rod">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=106">
                                <img src="images/23-1.jpg" alt="royal blue rod">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=107">
                                <img src="images/24-1.jpg" alt="royal blue rod">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="../item/itemDetail.html?itemNo=108">
                                <img src="images/8.jpg" alt="royal blue rod">
                            </a>
                        </div>
                    </div>
                    <!--controls/arrows控制/箭頭-->
                    <a class="carousel-control-prev" href="#blah" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#blah" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>
            </div>

        </header>
        <!-- 討論區快捷區-->
        <section class="py-5">
            <h2 class="fw-bolder">討論區話題</h2>
                <!-- Call to action-->
                    <!-- Call to action-->
                             <nav>
                                 <div class="card text-center">
                                    <div class="card-header">
                                        <ul class="nav nav-tabs card-header-tabs centered-tabs">
                                            <li><a href="<%=request.getContextPath()%>/view/head/Facepage.jsp?itemNo=1">策略型討論區</a></li>
                                            <li><a href="<%=request.getContextPath()%>/view/head/Facepage.jsp?itemNo=2">派對型討論區</a></li>
                                            <li><a href="<%=request.getContextPath()%>/view/head/Facepage.jsp?itemNo=3">親子型討論區</a></li>
                                            <li><a href="<%=request.getContextPath()%>/view/head/Facepage.jsp?itemNo=4">陣營型討論區</a></li>
                                            <li><a href="<%=request.getContextPath()%>/view/head/Facepage.jsp?itemNo=5">其他型討論區</a></li>
                                        </ul>
                                    </div>
                                     <div class="tab-content">
                                         <div id="<%=request.getContextPath()%>/view/head/123.jsp?itemNo=1" class="tab-pane fade show active">
                                             <div class="card-body">
                                                 <h5 class="card-title">策略型討論區</h5>
                                     <table class="table">
                                                 <thead>
                                                      <tr>
                                                           <th>編號</th>
                                                           <th>標題</th>
                                                      </tr>
                                                 </thead>
                                                 <tbody>
                                                      <c:forEach var="ArtVo" items="${ArtListData}" begin="0" end="2">
                                                      <tr>
                                                            <td>${ArtVo.artNo}</td>
                                                            <td class="table-title"><a href="../forum/innerpage/detail.jsp?artNo=${ArtVo.artNo}">${ArtVo.artTitle}</a></td>
                                                      </tr>
                                                      </c:forEach>
                                                 </tbody>
                                     </table>
                                                             <div class="text-center mt-3">
                                                     <a href="../forum/list/List.jsp?itemNo=1" class="btn btn-primary">查看更多</a>
                                                 </div>
                                             </div>
                                         </div>
                                         <div id="<%=request.getContextPath()%>/view/head/123.jsp?itemNo=2" class="tab-pane fade">
                                             <div class="card-body">
                                                 <h5 class="card-title">派對型討論區</h5>
                                         <table class="table">
                                                 <thead>
                                                      <tr>
                                                           <th>編號</th>
                                                           <th>標題</th>
                                                      </tr>
                                                 </thead>
                                                 <tbody>
                                                      <c:forEach var="ArtVo" items="${ArtListData}" begin="0" end="2">
                                                      <tr>
                                                            <td>${ArtVo.artNo}</td>
                                                            <td class="table-title"><a href="..../forum/innerpage/detail.jsp?artNo=${ArtVo.artNo}">${ArtVo.artTitle}</a></td>
                                                      </tr>
                                                      </c:forEach>
                                                 </tbody>
                                     </table>
                                                 <div class="text-center mt-3">
                                                     <a href="#" class="btn btn-primary">查看更多</a>
                                                 </div>
                                             </div>
                                         </div>
                                         <div id="<%=request.getContextPath()%>/view/head/123.jsp?itemNo=3" class="tab-pane fade">
                                             <div class="card-body">
                                                 <h5 class="card-title">親子型討論區</h5>
                                   <table class="table">
                                                 <thead>
                                                      <tr>
                                                           <th>編號</th>
                                                           <th>標題</th>
                                                      </tr>
                                                 </thead>
                                                 <tbody>
                                                      <c:forEach var="ArtVo" items="${ArtListData}" begin="0" end="2">
                                                      <tr>
                                                            <td>${ArtVo.artNo}</td>
                                                            <td class="table-title"><a href="..../forum/innerpage/detail.jsp?artNo=${ArtVo.artNo}">${ArtVo.artTitle}</a></td>
                                                      </tr>
                                                      </c:forEach>
                                                 </tbody>
                                     </table>
                                                 <div class="text-center mt-3">
                                                     <a href="#" class="btn btn-primary">查看更多</a>
                                                 </div>
                                             </div>
                                         </div>
                                         <div id="<%=request.getContextPath()%>/view/head/123.jsp?itemNo=4" class="tab-pane fade">
                                             <div class="card-body">
                                                 <h5 class="card-title">陣營型討論區</h5>
                                       <table class="table">
                                                  <thead>
                                                       <tr>
                                                            <th>編號</th>
                                                            <th>標題</th>
                                                       </tr>
                                                  </thead>
                                                  <tbody>
                                                       <c:forEach var="ArtVo" items="${ArtListData}" begin="0" end="2">
                                                       <tr>
                                                             <td>${ArtVo.artNo}</td>
                                                             <td class="table-title"><a href="..../forum/innerpage/detail.jsp?artNo=${ArtVo.artNo}">${ArtVo.artTitle}</a></td>
                                                       </tr>
                                                       </c:forEach>
                                                  </tbody>
                                      </table>
                                                 <div class="text-center mt-3">
                                                     <a href="#" class="btn btn-primary">查看更多</a>
                                                 </div>
                                             </div>
                                         </div>
                                         <div id="<%=request.getContextPath()%>/view/head/123.jsp?itemNo=5" class="tab-pane fade">
                                             <div class="card-body">
                                                 <h5 class="card-title">其他型討論區</h5>
                                       <table class="table">
                                                   <thead>
                                                        <tr>
                                                             <th>編號</th>
                                                             <th>標題</th>
                                                        </tr>
                                                   </thead>
                                                   <tbody>
                                                        <c:forEach var="ArtVo" items="${ArtListData}" begin="0" end="2">
                                                        <tr>
                                                              <td>${ArtVo.artNo}</td>
                                                              <td class="table-title"><a href="..../forum/innerpage/detail.jsp?artNo=${ArtVo.artNo}">${ArtVo.artTitle}</a></td>
                                                        </tr>
                                                        </c:forEach>
                                                   </tbody>
                                       </table>
                                                 <div class="text-center mt-3">
                                                     <a href="#" class="btn btn-primary">查看更多</a>
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                             </nav>
                         </div>
             </section>

           </nav>
            </div>
        </section>
    </main>
    <!-- Footer-->
    <footer class="bg-dark py-4 mt-auto">
        <div class="container px-5">
            <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                <div class="col-auto">
                    <div class="small m-0 text-white">Copyright © 2023 PolyBrain. All rights reserved. </div>
                </div>
                <div class="col-auto">
                    <a class="link-light small" href="Privacy.html">隱私權協定</a>
                    <span class="text-white mx-1">&middot;</span>
                    <a class="link-light small" href="Forus.html">關於我們</a>
                    <span class="text-white mx-1">&middot;</span>
                    <a href="mailto:ps66391@gmail.com?subject=聯絡我們的請求&body=請在此寫下您的問題。"
                       title="用 Email 轉寄" onclick="return confirm('您確定要前往email寄信?');">
                        <img src="../head/images/信封.jpg" >
                    </a>
                </div>
            </div>
        </div>
    </footer>
    <!-- Bootstrap core JS-->
    <!-- JavaScript part -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.1/js/bootstrap.min.js"
        integrity="sha512-fHY2UiQlipUq0dEabSM4s+phmn+bcxSYzXP4vAXItBvBHU7zAM/mkhCZjtBEIJexhOMzZbgFlPLuErlJF2b+0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
    <script>
        $(document).ready(function(){
            validateMemStatus();
        });
    async function validateMemStatus() {
    const response = await fetch('/PolyBrain/general/validateMemStatus', {
    method: 'POST',
    headers: { 'content-type': 'application/json; charset:utf-8' },
    })
    .then(resp => resp.json())
    .then(data => {
    console.log(data);
    const { memNo, memName, loginStatus } = data;
    $('ul#dropdown-menu').append(`
    <li><a class="dropdown-item" href="#!">會員專區</a></li>
    <li><a class="dropdown-item" href="#!">購物車</a></li>
    <li><hr class="dropdown-divider" /></li>
    `);
    if (loginStatus) {
    $('span#memName').text(memName);
    $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/logout.jsp">登出</a></li>');
    let memDetail = [memNo, memName];
    return memDetail;
    } else {
    $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/login.html">登入</a></li>');
    }
    });
    return response;
    }

          let bidEventList = document.querySelectorAll('.bidEventList');
        bidEventList.forEach(link => {
            link.addEventListener('click', function (event) {
                event.preventDefault();
                const biddingEvent = link.getAttribute('data-event-id');
                const bidEventURL = 'http://localhost:8080/PolyBrain/view/bid/BidOnItemPage2.jsp';
                const url = `${bidEventURL}?bidEventId=${biddingEvent}`;
                window.location.href = url;
            });
        });
    </script>
</body>

</html>