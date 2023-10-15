<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="feature.bid.vo.*" %>
<%@ page import="feature.bid.service.*" %>
<%@ page import="feature.bid.dao.*" %>
<%@ page import="feature.bid.dto.*" %>

<% 
BiddingService biddingService = new BiddingServiceImpl(); 
List<BidItemDto> list = biddingService.getTableData();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Tables - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        .upload_inputfile {
            width: .1px;
            height: .1px;
            opacity: 0;
            overflow: hidden;
            position: absolute;
            z-index: -1;
        }

        .upload_btn {
            display: inline-block;
            font-weight: 600;
            color: #fff;
            text-align: center;
            min-width: 116px;
            transition: all .3s ease;
            cursor: pointer;
            border: 2px solid;
            background-color: #00a0e9;
            border-color: #00a0e9;
            border-radius: 10px;
            margin-left: 100px;
        }

        .upload_btn:hover {
            background-color: unset;
            color: #00a0e9;
            transition: all .3s ease;
        }

        .upload_img-wrap {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -10px;
        }

        .upload_img-box {
            width: 200px;
            padding: 0 10px;
            margin-bottom: 12px;
        }

        .upload_img-close {
            width: 24px;
            height: 24px;
            border-radius: 50%;
            background-color: rgba(0, 0, 0, 0.5);
            position: absolute;
            top: 10px;
            right: 10px;
            text-align: center;
            line-height: 24px;
            z-index: 1;
            cursor: pointer;
        }

        .upload_img-close:after {
            content: '\2716';
            font-size: 14px;
            color: white;
        }

        .img-bg {
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            position: relative;
            padding-bottom: 100%;
        }
    </style>
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
            <div class="input-group">
                <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..."
                    aria-describedby="btnNavbarSearch" />
                <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i
                        class="fas fa-search"></i></button>
            </div>
        </form>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#!">Settings</a></li>
                    <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                    <li>
                        <hr class="dropdown-divider" />
                    </li>
                    <li><a class="dropdown-item" href="#!">Logout</a></li>
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
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i>
                            </div>
                            Layouts
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="layout-static.html">Static
                                    Navigation</a>
                                <a class="nav-link" href="layout-sidenav-light.html">Light
                                    Sidenav</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                            aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i>
                            </div>
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
                                        <a class="nav-link" href="login.html">Login</a>
                                        <a class="nav-link" href="register.html">Register</a>
                                        <a class="nav-link" href="password.html">Forgot
                                            Password</a>
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
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i>
                            </div>
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
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Tables</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                        <li class="breadcrumb-item active">Tables</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-body">
                            DataTables is a third party plugin that is used to generate the
                            demo table below. For more information about DataTables, please
                            visit the
                            <a target="_blank" href="https://datatables.net/">official
                                DataTables documentation</a>
                            .
                        </div>
                    </div>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            DataTable Example
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>編號</th>
                                        <th>圖片</th>
                                        <th>名稱</th>
                                        <th>描述</th>
                                        <th>類別</th>
                                        <th>發行商</th>
                                        <th>編輯</th>
                                        <th>刪除</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <% List<BidItemDto> listAll = (List<BidItemDto>)pageContext.getAttribute("list"); %>
                                            <% if(listAll !=null) { %>
                                                <% for(BidItemDto bidItemDto : listAll) {%>
                                                    <tr>
                                                        <td><%= bidItemDto.getBidItemVo().getBidItemNo() %></td>
                                                        <td class="bidItemPics"><img src="data:image/jpeg;base64,<%= bidItemDto.getBidItemPic() %>" width="100px" height="100px"/></td>
                                                        <td><%= bidItemDto.getBidItemVo().getBidItemName() %></td>
                                                        <td>
                                                            <!-- Button trigger modal -->
                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#bid_item_describe_<%= bidItemDto.getBidItemVo().getBidItemNo() %>">
                                                                商品說明
                                                            </button>
                                                            
                                                            <!-- Modal -->
                                                             <div class="modal fade" id="bid_item_describe_<%= bidItemDto.getBidItemVo().getBidItemNo() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content"> 
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <%= bidItemDto.getBidItemVo().getBidItemDescribe() %>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            
                                                        </td>
                                                        <td><%= bidItemDto.getBidItemVo().getItemClass().getItemClassName() %></td>
                                                        <td><%= bidItemDto.getBidItemVo().getGamePublisher() %></td>
                                                        <td>
                                                            <!-- 新增一筆競標商品資料 -->
                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#bid_item_edit_<%= bidItemDto.getBidItemVo().getBidItemNo() %>"style="float: right; margin-bottom: 1px;">
                                                                編輯
                                                            </button>
                                                            <!-- Modal -->
                                                            <div class="modal fade" id="bid_item_edit_<%= bidItemDto.getBidItemVo().getBidItemNo() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title">競標商品修改</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <form action="<%=request.getContextPath()%>/bid/BidItemList" method="post" enctype="multipart/form-data">
                                                                                <div style="margin-bottom: 10px; display: inline-block;">

                                                                                    <label for="bidItemName_edit">競標品名稱:</label>
                                                                                    <input type="text" id="bidItemName_edit" name="bidItemName" value="<%= bidItemDto.getBidItemVo().getBidItemName() %>">

                                                                                    <select id="itemClassNo_edit" name="itemClassNo">
                                                                                        <option disabled selected>--類別--</option>
                                                                                    </select>
                                                                                </div>
                                                                                <div>
                                                                                    <label for="gamePublisher_edit">遊戲發行商:</label>
                                                                                    <input type="text" id="gamePublisher_edit" name="gamePublisher" value="<%= bidItemDto.getBidItemVo().getGamePublisher()%>">
                                                                                </div>
                                                                                <div style="border: 1px solid white; margin: 10px 0;"></div>
                                                                                <div>
                                                                                    <label for="bidItemPic_edit">商品圖片:</label>
                                                                                    <input type="file" id="bidItemPic_edit" name="bidItemPic" onclick="previewEdit()" multiple="multiple">
                                                                                    <div id="blob_holder_edit"><img src="data:image/jpeg;base64,<%=bidItemDto.getBidItemPic()%>" width="100px" height="100px"/></div>
                                                                                </div>
                                                                                <div>
                                                                                    <label for="bidItemDescribe_edit">商品描述:</label><br>
                                                                                    <textarea id="bidItemDescribe_edit" name="bidItemDescribe" cols="30" rows="10"
                                                                                        style="width: 100%; height: 150px; resize: none;" value="<%= bidItemDto.getBidItemVo().getBidItemDescribe() %>" ></textarea>
                                                                                        <input type="text" style="width: 100%; height: 200px; white-space: pre;" value=" <%= bidItemDto.getBidItemVo().getBidItemDescribe() %>"> 
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                                                    <input type="hidden" name="action" value="edit">
                                                                                    <button type="submit" id="submit_edit" class="btn btn-primary">儲存變更</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                        <!-- Button trigger modal -->
                                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#bid_item_delete_<%= bidItemDto.getBidItemVo().getBidItemNo() %>">
                                                            刪除
                                                        </button>
                                                        
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="bid_item_delete_<%= bidItemDto.getBidItemVo().getBidItemNo() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        確定刪除嗎?
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                                        <form action="<%=request.getContextPath()%>/bid/BidItemList" method="get">
                                                                        <button type="submit" class="btn btn-primary">確認刪除</button>
                                                                        <input type="hidden" name="bidItemNo" value="<%= bidItemDto.getBidItemVo().getBidItemNo() %>">
                                                                        <input type="hidden" name="action" value="delete">
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </td>
                                                    </tr>
                                                    <% } %>
                                                    <% } %>
                                </tbody>
                            </table>
                            <!-- 新增一筆競標商品資料 -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal" style="float: right; margin-bottom: 1px;">
                                新增
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                                aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">競標商品新增</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="<%=request.getContextPath()%>/general/BidItemList" method="post" enctype="multipart/form-data">
                                                <div style="margin-bottom: 10px; display: inline-block;">
                                                        <label for="bidItemName">競標品名稱:</label>
                                                        <input type="text" id="bidItemName" name="bidItemName">
                                                        <select id="itemClassNo" name="itemClassNo">
                                                            <option disabled selected>--類別--</option>
                                                        </select>
                                                </div>
                                                <div>
                                                    <label for="gamePublisher">遊戲發行商:</label>
                                                    <input type="text" id="gamePublisher" name="gamePublisher">
                                                </div>
                                                <div style="border: 1px solid white; margin: 10px 0;"></div>
                                                <!-- <div>
                                                    <label for="bidItemPic">商品圖片:</label>
                                                    <input type="file" id="bidItemPic" name="bidItemPic" onclick="preview()" multiple="multiple">
                                                    <div id="blob_holder"></div>
                                                </div> -->
                                                <div class="div_addOption">
                                                    <div class="upload_box">
                                                        <div class="upload_btn-box">
                                                            <label class="upload_btn">
                                                                <p style="margin: 0;">上傳商品圖片</p>
                                                                <input type="file" id="bidItemPic" name="bidItemPic" class="upload_inputfile"
                                                                    accept="image/jpeg, image/png, image/jpg, image/gif" multiple>
                                                            </label>
                                                        </div>
                                                        <div class="upload_img-wrap"></div>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label for="bidItemDescribe">商品描述:</label><br>
                                                    <textarea id="bidItemDescribe" name="bidItemDescribe" cols="30" rows="10" style="width: 100%; height: 150px; resize: none;"></textarea>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">取消</button>
                                                    <input type="hidden" name="action" value="insert">
                                                    <button type="submit" id="submit" class="btn btn-primary">儲存</button>
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
    </div>
    <script>

        $(document).ready(function(){
            selectClass();
            ImgUpload();
            // viewAll();
        });

        function viewAll(){
            fetch('/PolyBrain/general/BidItemList', {
                method: 'POST',
                headers: {'content-type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams({
                    action: 'selectAllPics'
                })
            })
                .then(resp => resp.json())
                .then(data => {
                    console.log(data);
                    for(let i = 1; i < data.length; i++){
                        let img = `<img src="data:image/jpeg;base64,${data[i].bidItemPic}">`;
                        console.log(data[i].bidItemPic);
                        let tr = document.querySelector(`tr[data-index="${i}"]`);
                        let td = tr.querySelector('td:nth-child(2)');
                        console.log(tr);
                        console.log(td);
                        $(td).html(img);
                    }
                    
                })
            }
        

        function ImgUpload() {
            var imgWrap = "";
            var imgArray = [];

            $('.upload_inputfile').each(function () {
                $(this).on('change', function (e) {
                    imgWrap = $(this).closest('.upload_box').find('.upload_img-wrap');
                    var maxLength = $(this).attr('data-max_length');

                    var files = e.target.files;
                    var filesArr = Array.prototype.slice.call(files);
                    var iterator = 0;
                    filesArr.forEach(function (f, index) {

                        if (!f.type.match('image.*')) {
                            return;
                        }

                        if (imgArray.length > maxLength) {
                            return false;
                        } else {
                            var len = 0;
                            for (var i = 0; i < imgArray.length; i++) {
                                if (imgArray[i] !== undefined) {
                                    len++;
                                }
                            }
                            if (len > maxLength) {
                                return false;
                            } else {
                                imgArray.push(f);

                                var reader = new FileReader();
                                reader.onload = function (e) {
                                    var html = "<div class='upload_img-box'><div style='background-image: url(" + e.target.result + ")' data-number='" + $(".upload_img-close").length + "' data-file='" + f.name + "' class='img-bg add_img-bg'><div class='upload_img-close'></div></div></div>";
                                    imgWrap.append(html);
                                    iterator++;
                                }
                                reader.readAsDataURL(f);
                            }
                        }
                    });
                });
            });

            $('body').on('click', ".upload_img-close", function (e) {
                var file = $(this).parent().data("file");
                for (var i = 0; i < imgArray.length; i++) {
                    if (imgArray[i].name === file) {
                        imgArray.splice(i, 1);
                        break;
                    }
                }
                $(this).parent().parent().remove();
            });
        }

        function selectClass(){
            fetch('/PolyBrain/general/bidding',{
                method: 'POST',
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    message: 'selectClass'
                })
            })
            .then(resp => resp.json())
            .then(data => {
                console.log('yyy')
                for(let itemClass of data){
                    let option = document.createElement('option');
                    option.value = itemClass.itemClassNo;
                    option.textContent = itemClass.itemClassName;
                    console.log(option.value);
                    console.log(option.textContent);
                    let itemClassSelect = document.getElementById('itemClassNo');
                    itemClassSelect.appendChild(option);
                }
            })
            .catch(error => {
                console.log("error", error);
            });
        }

        let filereader_support = typeof FileReader != 'undefined';
        if(!filereader_support){
            alert("No FileReader Support");
        }
        acceptedTypes = {
            'image/png' : true,
            'image/jpeg' : true,
            'image/gif' : true
        }
        // 修改圖片
        function previewEdit(){
            let bidItemPic_edit = document.getElementById('bidItemPic_edit');
            bidItemPic_edit.addEventListener("change", function(event){
                let files = event.target.files || event.dataTransfer.files;
                for(let i = 0; i < files.length; i++){
                    previewfileEdit(files[i])
                }
            }, false);
        }
        function previewfileEdit(file){
            if(filereader_support === true && acceptedTypes[file.type] === true){
                let reader = new FileReader();
                reader.onload = function(event){
                    let image = new Image();
                    image.src = event.target.result;
                    image.width = 100;
                    image.height = 75;
                    image.border = 2;
                    if(blob_holder_edit.hasChildNodes()){
                        blob_holder_edit.removeChild(blob_holder_edit.childNodes[0]);
                    }
                    blob_holder_edit.appendChild(image);
                };
                reader.readAsDataURL(file);
                document.getElementById('submit_edit').disabled = false;
            }else{
                blob_holder_edit.innerHTML = "<div style='text-align: left;'>" + "filename" + file.name;
                document.getElementById('submit_edit').disabled = true;
            }
        }
        // 新增圖片
        function preview(){
            let bidItemPic = document.getElementById('bidItemPic');
            bidItemPic.addEventListener("change", function(event){
                let files = event.target.files || event.dataTransfer.files;
                for(let i = 0; i < files.length; i++){
                    previewfile(files[i])
                }
            }, false);
        }
        function previewfile(file){
            if(filereader_support === true && acceptedTypes[file.type] === true){
                let reader = new FileReader();
                reader.onload = function(event){
                    let image = new Image();
                    image.src = event.target.result;
                    image.width = 100;
                    image.height = 75;
                    image.border = 2;
                    if(blob_holder.hasChildNodes()){
                        blob_holder.removeChild(blob_holder.childNodes[0]);
                    }
                    blob_holder.appendChild(image);
                };
                reader.readAsDataURL(file);
                document.getElementById('submit').disabled = false;
            } else{
                blob_holder.innerHTML = "<div style='text-align: left;'>" + "filename" + file.name;
                    document.getElementById('submit').disabled = true;
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>

</html>