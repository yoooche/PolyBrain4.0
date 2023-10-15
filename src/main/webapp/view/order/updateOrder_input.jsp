<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="feature.order.vo.*"%>
<%@ page import="feature.order.dao.impl.*"%>
<%@ page import="feature.order.dao.*"%>
<%@ page import="feature.order.controller.*"%>
<%@ page import="feature.order.service.*"%>

<%ItemOrderVO itemordervo = (ItemOrderVO) request.getAttribute("itemorder");%>

<html>
<head>
<title>訂單資料修改 - updateOrder_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='pink'>

<table id="table-1">
	<tr><td>
		 <h3>訂單資料修改 - updateOrder_input.jsp</h3>
		 <h4><a href="select.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="/PolyBrain/view/order/order.tw" name="test1">
<table>
	<tr>
		<td>會員編號:<font color=red><b></b></font></td>
		<td><%=itemordervo.getMemNo()%></td>
		<input type="hidden" name="memNo" value="<%=itemordervo.getMemNo()%>">
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><input type="TEXT" name="receiverName" value="<%=itemordervo.getReceiverName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>收件地址:</td>
		<td><input type="TEXT" name="receiverAddress" value="<%=itemordervo.getReceiverAddress()%>" size="45"/></td>
	</tr>
	<tr>
		<td>收件人電話 :</td>
		<td><input type="text" name="receiverPhone" value="<%=itemordervo.getReceiverPhone()%>" ></td>
	</tr>
</table>
<br>

<input type="hidden" name="test1" value="update">
<input type="hidden" name="orderNo" value="<%=itemordervo.getOrderNo()%>">
<input type="submit" value="送出修改">
</FORM>
</body>



</html>