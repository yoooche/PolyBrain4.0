    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="feature.forum.service.ArtService"%>
    <%@page import="feature.forum.vo.ArtVo"%>
    <%@page import="feature.forum.service.RpyService"%>
    <%@page import="feature.forum.vo.RpyVo"%>
    <%@ page import="java.util.List" %>


    <%
        ArtService artSvc = new ArtService();
        ArtVo artVo = artSvc.getOneArt(Integer.valueOf(request.getParameter("artNo")));
        pageContext.setAttribute("artVo", artVo);
    %>
    <%
           RpyService rpySvc = new RpyService();
           List<RpyVo> rpyList = rpySvc.findByArt(Integer.valueOf(request.getParameter("artNo")));
           pageContext.setAttribute("rpyList", rpyList);
    %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>討論區內頁</title>

        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/view/forum/innerpage/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.22/dist/sweetalert2.min.css" rel="stylesheet">
        <style>
            nav.navbar {
                position: sticky;
                top:0;
                z-index: 100;
            }
        </style>
    </head>
    <body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.22/dist/sweetalert2.all.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="sweetalert2.all.min.js"></script>
    <!-- Responsive navbar-->
    <%
    String artNoParam = request.getParameter("artNo");
    out.println("artNoParam: " + artNoParam);
    int artNoValue = Integer.valueOf(artNoParam);
    out.println("artNoValue: " + artNoValue);
    %>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#!">PolyBrain</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="#">首頁</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">商品專區</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">競標專區</a></li>
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">會員中心</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Page content-->
    <div class="container mt-5">

        <div class="row">
            <div class="col-lg-8">
                <!-- Post content-->
                <article>
                    <!-- Post header-->
                    <header class="mb-4">
                        <!-- Post title-->
                        <h1 class="fw-bolder mb-1">${artVo.artTitle}</h1>
                        <!-- Post meta content-->
                        <div class="text-muted fst-italic mb-2">${artVo.artTime}</div>
                        <!-- Post categories-->
                        <button class="btn btn-primary " id="button-collect"  type="button">貼文檢舉</button>
                    </header>
                    <!-- Preview image figure-->
                    <figure class="mb-4"><img class="img-fluid rounded" src="<%=request.getContextPath()%>/Art/DBGifReader?artNo=${artVo.artNo}" width="100px" alt="..." /></figure>
                    <!-- Post content-->
                 <section class="mb-5">
                     <p class="fs-5 mb-4">${artVo.artCon}</p>
                 </section>
                </article>
                <!-- Comments section-->
                <section class="mb-5">
                    <div class="card bg-light">
                        <div class="card-body">
                            <!-- Comment form-->
                            <form class="mb-4">
                                <!-- Comment with nested comments-->
                                <!-- Comment with nested comments-->
                                <c:forEach var="rpy" items="${rpyList}">
                                    <div class="d-flex mb-4">
                                        <!-- Parent comment-->
                                        <div class="flex-shrink-0">

                                        </div>
                                        <div class="ms-3">
                                            <div class="fw-bold">${rpy.artNo}</div>
                                            ${rpy.rpyCon}

                                              <form method="post" action="<%=request.getContextPath()%>/RpyServlet">
                                                            <input type="hidden" name="rpyNo" value="${rpy.rpyNo}">
                                                            <input type="hidden" name="artNo" value="${artVo.artNo}">
                                                            <input type="hidden" name="action" value="delete">
                                                            <button type="submit">删除</button>
                                                        </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            <!-- Add Comment Button -->
                            <form id="insertForm" method="post" action="<%=request.getContextPath()%>/RpyServlet">
                                                        <textarea class="form-control" name="rpyCon" rows="3" placeholder="留言..."value="${rpy.rpyCon}"></textarea>
                                                           <button class="btn btn-primary position-absolute bottom-0 end-0" id="addCommentBtn" type="button" >新增留言</button>
                                                            <input type="hidden" name="memNo" value="1">
                                                            <input type="hidden" name="artNo" value="${artVo.artNo}">
                                                            <input type="hidden" name="action"  value="insert">
                                                        </form>


                        </div>
                    </div>
                </section>
            </div>
            <!-- Side widgets-->
            <div class="col-lg-4">
                <!-- Search widget-->
                <div class="card mb-4">
                    <div class="card-header">搜尋</div>
                    <div class="card-body">
                        <div class="input-group">
                            <input class="form-control" type="text" placeholder="輸入關鍵字..." aria-label="Enter search term..." aria-describedby="button-search" />

                            <button class="btn btn-primary" id="button-search" type="button">查詢</button>
                        </div>
                    </div>
                </div>
                <!-- Categories widget-->
                <div class="card mb-4">
                    <div class="card-header">桌遊類別</div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-6">
                                <ul class="list-unstyled mb-0">
                                    <li><a href="#!">策略類</a></li>
                                    <li><a href="#!">派對類</a></li>
                                    <li><a href="#!">親子類</a></li>
                                </ul>
                            </div>
                            <div class="col-sm-6">
                                <ul class="list-unstyled mb-0">
                                    <li><a href="#!">合作類</a></li>
                                    <li><a href="#!">策略類</a></li>
                                    <li><a href="#!">陣營類</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Side widget-->
                <div class="card mb-4">
                    <div class="card-header">討論區須知</div>
                    <div class="card-body">歡迎來到桌遊討論區！這裡是一個熱愛桌上遊戲的社群，讓我們共同營造友善、有趣的討論環境。請尊重彼此，避免冒犯、不雅言辭與爭執。分享遊戲心得、規則解釋、推薦等話題。請勿洩漏劇透、侵犯版權或做商業宣傳。讓我們一同享受桌遊帶來的樂趣吧！</div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
    </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${pageContext.request.contextPath}/view/forum/innerpage/js/scripts.js"></script>
    <script>
        const submitButton = document.querySelector("#addCommentBtn");
        submitButton.addEventListener("click", function() {
              Swal.fire({
                  title: '確定送出??',
                  text: "確認內容編輯後即可送出!",
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                  confirmButtonText: 'OK'
              }).then((result) => {
                  if (result.isConfirmed) {
                      // 手動觸發表單的提交
                      document.querySelector("#insertForm").submit();
                  }
              });
          });
    </script>

    </body>
    </html>