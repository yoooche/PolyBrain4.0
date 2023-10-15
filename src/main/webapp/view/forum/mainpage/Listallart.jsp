<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="feature.forum.service.ArtService"%>
<%@page import="feature.forum.vo.ArtVo"%>

<%
    ArtService artSvc = new ArtService();
    List<ArtVo> list = artSvc.getAll();
    if(request.getAttribute("ArtListData")==null) pageContext.setAttribute("ArtListData",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>所有討論區資料 - listAll.jsp</title>


<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->

<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"lengthMenu": [3 ,5, 10, 20, 50, 100],
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



<table id="example" class="display" style="width: 100%">
  <thead >
	<tr style="background-color:#CCCCFF">
	    <th>計數</th>
		<th>文章編號</th>
		<th>會員編號</th>
		<th>貼文主題</th>
		<th>貼文內容</th>
		<th>發布時間</th>
		<th>貼文狀態</th>
		<th>遊戲類別</th>
		<th>圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
  </thead>

 <tbody>
	<c:forEach var="ArtVo" items="${ArtListData}" varStatus="s">
		<tr>
            <td>${s.count}</td>
			<td>${ArtVo.artNo}</td>
			<td>${ArtVo.memNo}</td>
			<td>${ArtVo.artTitle}</td>
			<td>${ArtVo.artCon}</td>
			<td>
                <fmt:formatDate value="${ArtVo.artTime}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
			<td>
                        <c:choose>
                            <c:when test="${ArtVo.artState == 0}">
                                未上架
                            </c:when>
                            <c:when test="${ArtVo.artState == 1}">
                                已上架
                            </c:when>
                            <c:otherwise>
                                未知狀態
                            </c:otherwise>
                        </c:choose>
                    </td>
			<td>${ArtVo.itemNo}</td>
			<td><img src="<%=request.getContextPath()%>/Art/DBGifReader?artNo=${ArtVo.artNo}" width="100px"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Art/Art.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="artNo" value="${ArtVo.artNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Art/Art.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="artNo" value="${ArtVo.artNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
  </tbody>
</table>

</body>
</html>