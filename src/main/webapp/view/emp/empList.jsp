<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="feature.emp.vo.*"%>
<% EmpVo empVo = (EmpVo) request.getAttribute("empVo");%>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>PolyBrain後台主控頁</title>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="./css/style_PB.css"/>
        <link rel="stylesheet" href="./css/styles.css"/>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav  class="sb-topnav navbar navbar-expand bg-primary">
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars" style="color: white;"></i></button>
            <!-- Navbar Brand-->
            <span class="brand_name">
                <img class="brand_img" src="images/PolyBrain_Logo.png">
            </span>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search" ></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">設定</a></li>
                        <li><a class="dropdown-item" href="#!">主題</a></li>
                        <li><hr class="dropdown-divider" /></li>
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
                            <div class="sb-sidenav-menu-heading">名單總覽</div>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                管理員資料
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                會員資料
                            </a>
                            <div class="sb-sidenav-menu-heading">商品 & 訂單</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                商城管理
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="layout-static.html">商品上架</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">訂單總覽</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                競標管理
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        商品上架
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login.html">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        訂單總覽
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">討論區 & 預約管理</div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                討論區管理
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                預約管理
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Yang Che
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="main_area_header">管理員資料</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-header">
                                <div>
                                    <form method="post" action="EmpListController" style="display:flex; align-items:center;">
                                    <p>輸入員工編號</p>
                                    <input type="text" name="empNo"><br>
                                    <input type="hidden" name="test" value="getOneEmp">
                                    <input id="btn_submit" type="submit" value="送出">
                                    </form>
                                    </div>
                                <div>
                                    <form method="post" action="EmpListController">
                                    <select>
                                    <option>全部員工</option>
                                    <input type="hidden" name="getAllEmp" value="getAll">
                                    <input type="submit" value="送出">
                                    </select>
                                    </form>
                                    </div>
                            <div class="card-body">

                                <table id="datatablesSimple">
                                    <thead style="border: 1px solid black">
                                    <tr>
                                    <th>員工編號</th>
                                    <th>員工姓名</th>
                                    <th>員工性別</th>
                                    <th>員工信箱</th>
                                    <th>員工密碼</th>
                                    <th>員工電話</th>
                                    <th>員工照片</th>
                                    <th>在職狀態</th>
                                    </tr>
                                    </thead>
                                    <tfoot style="border: 1px solid black">
                                    <tr>
                                    <th>員工編號</th>
                                    <th>員工姓名</th>
                                    <th>員工性別</th>
                                    <th>員工信箱</th>
                                    <th>員工密碼</th>
                                    <th>員工電話</th>
                                    <th>員工照片</th>
                                    <th>在職狀態</th>
                                    </tr>
                                    </tfoot>
                                    <tbody style="border: 1px solid black">
                                        <tr>
                                        <td><%=empVo.getEmpNo()%></td>
                                        <td><%=empVo.getEmpName()%></td>
                                        <td><%=empVo.getEmpGender()%></td>
                                        <td><%=empVo.getEmpEmail()%></td>
                                        <td><%=empVo.getEmpPwd()%></td>
                                        <td><%=empVo.getEmpPh()%></td>
                                        <td><%=empVo.getEmpPic()%></td>
                                        <td><%=empVo.getEmpState()%></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <!-- Button trigger modal -->

                                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="float: right; margin-right:23px">
                                                      新增管理員
                                                    </button>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                      <div class="modal-dialog">
                                                        <div class="modal-content">
                                                          <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">新增管理員</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                          </div>
                                                          <div class="modal-body">
                                                            <form>
                                                            <input type="text" name="empName" placeholder="姓名">
                                                            <select>
                                                                <option disabled selected>性別</option>
                                                                <option value="1">男</option>
                                                                <option value="0">女</option>
                                                            </select><br>
                                                            <input type="text" name="empEmail" placeholder="電子信箱" style="width: 250px; margin: 5px 0;"><br>
                                                            <input type="text" name="empPh" placeholder="手機號碼" style="width: 250px; margin: 5px 0;"><br>
                                                            <select style="margin: 10px 0;">
                                                                <option disabled selected>職權範圍</option>
                                                                <option value="F001">後台管理</option>
                                                                <option value="F002">會員管理</option>
                                                                <option value="F003">商城管理</option>
                                                                <option value="F004">競標管理</option>
                                                                <option value="F005">貨品管理</option>
                                                                <option value="F006">客服管理</option>
                                                                <option value="F007">預約管理</option>
                                                                <option value="F008">討論區管理</option>
                                                            </select><br>
                                                            <input type="hidden" name="action" value="addNewEmp">
                                                            <div>
                                                            <input type="submit" value="確認新增" style="float: right">
                                                            </div>
                                                            </form>
                                                          </div>

                                                        </div>
                                                      </div>
                                                    </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; PolyBrain 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <!--    <script src="./js/datatables-simple-demo.js"></script>
            <script src="./js/bootstrap.bundle.min.js"></script>
            <script src="./js/jquery.easing.min.js"></script>
            <script src="./js/sb-admin-2.min.js"></script>
            <script src="./js/chart.js/Chart.min.js"></script>
            <script src="./js/chart-area-demo.js"></script>
            <script src="./js/chart-pie-demo.js"></script>
            <script src="./js/jquery.dataTables.min.js"></script>
            <script src="./js/dataTables.bootstrap4.min.js"></script>
            <script src="./js/datatables-demo.js"></script> -->
    </body>
</html>
