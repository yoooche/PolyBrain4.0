<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="feature.bookingtast.service.*"%>
<%@page import="feature.bookingtast.vo.*"%>
<%
    TablebookingService tabkSvc = new TablebookingService();
    List<TablebookingVO> list = tabkSvc.getAll();
    if(request.getAttribute("tabListData")==null) pageContext.setAttribute("tabListData",list);
%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>後台管理-預約時段管理</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.9/css/responsive.bootstrap4.min.css">
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.min.css">

<!--以下新增的-->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
    <script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!--以上新增的-->
    <link href="./css/item.css" rel="stylesheet" />
      <style>
   .pagination {
       /* 例如：調整整體的寬度 */
       width: 100%;
   }
   .dataTables_wrapper .dataTables_paginate .paginate_button {
       padding: 0;
   }
      </style>
    <style>
        .lightbox {
            display: none;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 9999;
            display: none;
            align-items: center;
            justify-content: center;
        }
        .lightbox-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
            margin: 5px;
        }

    <!--新增的-->

main {
    flex: 1;
    padding: 20px;
}

.sb-nav-fixed #layoutSidenav #layoutSidenav_content {
    width: 100%;       /* 使元素填充其容器的全部寬度 */
    padding-left: 88px;

     padding-right: 0px;
    top: 56px;
}
    </style>

<!--以下新增的內容-->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"lengthMenu": [12 ,24, 36, 48, 60, 72, 84],
			"searching": true,  //搜尋功能, 預設是開啟
		    "paging": true,     //分頁功能, 預設是開啟
		    "ordering": true,   //排序功能, 預設是開啟
		    "language": {
		        "processing": "處理中...",
		        "loadingRecords": "載入中...",
		        "lengthMenu": "顯示 _MENU_ 筆結果",
		        "zeroRecords": "沒有符合的結果",
		        "info": "顯示第 _START_ 至 _END_ 筆結果，共<font color=red> _TOTAL_ </font>筆",
		        "infoEmpty": "顯示第 0 至 0 筆結果，共 0 筆",
		        "infoFiltered": "(從 _MAX_ 筆結果中過濾)",
		        "infoPostFix": "",
		        "search": "搜尋:",
		        "paginate": {
		            "first": "第一頁",
		            "previous": "上一頁",
		            "next": "下一頁",
		            "last": "最後一頁"
		        },
		        "aria": {
		            "sortAscending":  ": 升冪排列",
		            "sortDescending": ": 降冪排列"
		        }
		    }
		});
	});

</script>
<style type="text/css">
body {
	margin: 1rem 12rem 2rem 12rem;
}
</style>
<!--以上新增的內容-->
</head>

<body class="sb-nav-fixed">
  <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
          <!-- Navbar Brand-->
          <a class="navbar-brand ps-3" href="index.html">Start Bootstrap</a>
          <!-- Sidebar Toggle-->
          <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                  class="fas fa-bars"></i></button>
          <!-- Navbar Search-->
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">

              </div>
          </form>
          <!-- Navbar-->
          <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
              <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                      aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                      <li><a class="dropdown-item" href="#!">登入</a></li>
                      <li><a class="dropdown-item" href="#!">活動日誌</a></li>
                      <li>
                          <hr class="dropdown-divider" />
                      </li>
                      <li><a class="dropdown-item" href="#!">登出</a></li>
                  </ul>
              </li>
          </ul>
    </nav>

    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <div class="sb-sidenav-menu-heading">Core</div>
                        <a class="nav-link" href="index.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                            Dashboard
                        </a>
                        <div class="sb-sidenav-menu-heading">Interface</div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            Layouts
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="layout-static.html">Static Navigation</a>
                                <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                            aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                            Pages
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                    data-bs-target="#pagesCollapseAuth" aria-expanded="false"
                                    aria-controls="pagesCollapseAuth">
                                    Authentication
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne"
                                    data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="login.html">登入</a>
                                        <a class="nav-link" href="register.html">登記</a>
                                        <a class="nav-link" href="password.html">忘記密碼</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                    data-bs-target="#pagesCollapseError" aria-expanded="false"
                                    aria-controls="pagesCollapseError">
                                    Error
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne"
                                    data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="401.html">401 Page</a>
                                        <a class="nav-link" href="404.html">404 Page</a>
                                        <a class="nav-link" href="500.html">500 Page</a>
                                    </nav>
                                </div>
                            </nav>
                        </div>
                        <div class="sb-sidenav-menu-heading">Addons</div>
                        <a class="nav-link" href="charts.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            Charts
                        </a>
                        <a class="nav-link" href="tables.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                            Tables
                        </a>
                    </div>
                </div>
                <div class="sb-sidenav-footer">
                    <div class="small">Logged in as:</div>
                    Start Bootstrap
                </div>
            </nav>
        </div>

<div id="layoutSidenav_content">
            <main>

<!--以下新增的-->
<table id="example" class="display" style="width: 115%"> <!--////對應上面設定表格的內容#內隨意取但是要一致 -->
  <thead >
	<tr style="background-color:#0eebb7">
        <th>編號</th>
		<th>日期</th>
		<th>桌號</th>
		<th>上午(9-12)</th>
		<th>下午(13-16)</th>
		<th>晚上(17-20)</th>
		<th>修改</th>

	</tr>
  </thead>

 <tbody>
	<c:forEach var="TablebookingVO" items="${tabListData}" >
		<tr>
            <td>${TablebookingVO.TABLE_BOOK_NO}</td>
			<td>${TablebookingVO.TABLE_DATE}</td>
			<td>${TablebookingVO.TABLE_NO}</td>
			<td>
                 <c:choose>
                                   <c:when test="${TablebookingVO.TABLE_MOR == 0}">
                                       <span style="color:black;">可預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_MOR == 1}">
                                      <span style="color:blue;">已預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_MOR == 2}">
                                      <span style="color:red;">不開放</span>
                                   </c:when>
                               </c:choose>
                           </td>
               			<td>
                               <c:choose>
                                   <c:when test="${TablebookingVO.TABLE_EVE == 0}">
                                       <span style="color:black;">可預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_EVE == 1}">
                                       <span style="color:blue;">已預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_EVE == 2}">
                                       <span style="color:red;">不開放</span>
                                   </c:when>
                               </c:choose>
                           </td>
               			<td>
                               <c:choose>
                                   <c:when test="${TablebookingVO.TABLE_NIGHT == 0}">
                                       <span style="color:black;">可預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_NIGHT == 1}">
                                       <span style="color:blue;">已預約</span>
                                   </c:when>
                                   <c:when test="${TablebookingVO.TABLE_NIGHT == 2}">
                                       <span style="color:red;">不開放</span>
                                   </c:when>
                               </c:choose>
                           </td>
               			<td>
               			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bookingtast/inserservlet" style="margin-bottom:0px;">
               			     <button type="submit" class="btn btn-primary">修改</button>
               			     <input type="hidden" name="TABLE_NO" value="${TablebookingVO.TABLE_BOOK_NO}">
               			     <input type="hidden" name="action"	value="getOne_For_Update">
               			  </FORM>
               			</td>
               		</tr>
               	</c:forEach>
                 </tbody>
               </table>

               <c:if test="${not empty message}">
                   <script>
                       alert('${message}');
                   </script>
               </c:if>

<!--以上新增的-->
            </main>

 <footer class="py-4 bg-light ">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2023</div>
                        <div>
                            <a href="#">Privacy Policy</a>
                            &middot;
                            <a href="#">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>

    </div>

    <div id="lightbox" class="lightbox">
        <div class="lightbox-content">
            <p>輸入要新增的類別</p>
            <input type="text" id="ClassName" placeholder="輸入要新增的名稱">
            <br>
            <button type="submit" id="addsubmit"  class="btn btn-warning btn-sm btn-edit"  style="margin-top: 15px"> 送出資料 </button>
            <button type="button" id="cancel"  class="btn btn-success btn-sm btn-edit" style="margin-top: 15px">取消新增</button>
            <span id="lightbox-close" class="lightbox-close"></span>
            <div id="lightbox-message" class="lightbox-message"></div>
        </div>
    </div>
    </div>
<script src="./js/jquery-3.7.0.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
 <script src="./js/itemClassConsole.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.9/js/responsive.bootstrap4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>

     <!-- //------------------------------以下新增的script-----------------------------------------------// -->
            <script>
                  $(document).ready(function() {
                      // 監聽 checkbox 的 change 事件
                      $('input[type="checkbox"]').change(function() {
                          if ($(this).is(":checked")) {
                              // 取得資料
                              let nameParts = $(this).attr('name').split('-');
                              let TABLE_BOOK_NO = nameParts[0];
                              let slot = nameParts[1];
                              let checkboxValue = $(this).val();

                              let dataToSend = {
                                  TABLE_BOOK_NO: TABLE_BOOK_NO,
                                  slot: slot,
                                  value: 2, // 不開放的值
                                  checkboxValue: checkboxValue
                              };

                              // AJAX 更新
                              $.ajax({
                                  url: "/bookingtast/inserservlet",
                                  type: "POST",
                                  data: JSON.stringify(dataToSend),
                                  contentType: "application/json",
                                  success: function(response) {
                                      if (response.success) {
                                          alert("更新成功！");
                                      } else {
                                          alert("更新失敗！");
                                      }
                                  },
                                  error: function() {
                                      alert("伺服器錯誤，請稍後再試！");
                                  }
                              });
                          }
                      });
                  });
            </script>
</body>

</html>