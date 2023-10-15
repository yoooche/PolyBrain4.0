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
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>所有員工資料 - listAllEmp.jsp</title>


<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->

<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({   //對應下面設定表格的內容#內隨意取但是要一致
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
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-success fixed-top justify-content-center">
		 <div align="center"> <h2>所有預約桌子資料 - 1.jsp</h2>
		 <h3><a class="navbar-brand" href="<%=request.getContextPath()%>/view/bookingtast/2.jsp"><img src="<%=request.getContextPath()%>/view/bookingtast/images/back1.gif">回查詢頁${success}</a></h3></div>
</nav>

<table id="example" class="display" style="width: 100%"> ////對應上面設定表格的內容#內隨意取但是要一致
  <thead >
	<tr style="background-color:#CCCCFF">
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
                <input type="checkbox" name="${TablebookingVO.TABLE_BOOK_NO}-morning" value="${TablebookingVO.TABLE_MOR}" >
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
                            <input type="checkbox" name="${TablebookingVO.TABLE_BOOK_NO}-eve" value="${TablebookingVO.TABLE_EVE}" >
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
                                        <input type="checkbox" name="${TablebookingVO.TABLE_BOOK_NO}-night" value="${TablebookingVO.TABLE_EVE}" >
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
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bookingtast/inserservlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="TABLE_NO" value="${TablebookingVO.TABLE_BOOK_NO}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
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





        //------------------------------script-----------------------------------------------//
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