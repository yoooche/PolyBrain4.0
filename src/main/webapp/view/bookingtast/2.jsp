<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>預約狀態修改 - update_emp_input.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


<style type="text/css">

	button {
		padding: 5px;
	}
	form {
		display: table;
	}
	form div {
		display: table-row;
	}
	label, input, span, select {
		display: table-cell;
		margin: 5px;
		text-align: left;
	}
	input[type=text], input[type=password], select, textarea {
		width: 200px;
		margin: 5px;
	}
	form div div {
		display: table-cell;
	}
	.center {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<style>
   .radio-item {
       display: inline-flex;  /* 使用 flex 使子項目在同一行 */
       align-items: center;  /* 垂直居中 */
       margin-right: 20px;   /* 提供右邊的間距，使兩個 radio-item 之間有一些距離 */
   }

   .radio-buttons {
       display: inline-flex;  /* 使其子項目在同一行 */
       align-items: center;  /* 垂直居中 */
   }


.boxed-content {   /* 包住的框  */
 max-width: 600px; /* 設定框的最大寬度為600像素 */
 margin: 0 auto;   /* 使框在頁面上居中 */
    width: 27%;
    border: 1px solid #ccc; /* 添加灰色邊框 */
    padding: 20px;          /* 添加內邊距，使內容與邊框有一些間距 */
    border-radius: 5px;     /* 圓角 */
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);  /* 添加陰影 */
     display: flex;
 justify-content: space-between; /* 使用此屬性將按鈕推到兩側 */
        align-items: center;
        flex-direction: row; /* 這會使按鈕水平排列 */
        gap: 10px; /* 這會在按鈕之間留有10像素的空隙 */
}
.navbar {
    padding: 35px 0; /* 增加上下的 padding 值，原本是 0 */
    justify-content: center;
}

.btn-space {
    margin-left: 20px; /* 調整這個值以達到所需的間距效果 */
}
</style>
</head>
<body>

<nav class="navbar justify-content-center">
		 <div align="center"> <h2>時段狀態修改</h2>
</nav>

<div class="boxed-content">
	<div align="center">
                                                                                           <!--enctype="multipart/form-data" 資料上傳接口沒給入口網頁會跑不動 -->
		<form action="<%=request.getContextPath()%>/bookingtast/inserservlet" method="post"  enctype="multipart/form-data">

            <div>
			<label for="TABLE_BOOK_NO">編號:</label>
			<input id ="TABLE_BOOK_NO" name="TABLE_BOOK_NO" type="text" value="${param.TABLE_BOOK_NO}" style="border:0px ; font-weight: bold;" readonly />
			</div>

            <div>
			<label for="TABLE_DATE">日期:</label>
			<input id ="TABLE_DATE" name="TABLE_DATE" type="date" value="${param.TABLE_DATE}" onclick="hideContent('TABLE_DATE.errors');" />
			<span  id ="TABLE_DATE.errors" class="error">${errorMsgs.TABLE_DATE}</span>
			</div>

			<div>
			<label for="TABLE_NO">桌號:</label>
			<input id ="TABLE_NO" name="TABLE_NO" type="text" value="${param.TABLE_NO}" onclick="hideContent('TABLE_NO.errors');" />
			<span  id ="TABLE_NO.errors" class="error">${errorMsgs.TABLE_NO}<br/></span>
			</div>


			                            <div class="radio-group">
                                       <div class="radio-group-label">
                                           <label>上午(09-12):</label>
                                       </div>
                                       <div class="radio-buttons">
                                           <div class="radio-item">
                                               <input type="radio" id="TABLE_MOR_available" name="TABLE_MOR" value="0"
                                               ${param.TABLE_MOR == '0' ? 'checked' : ''}
                                               onclick="hideContent('TABLE_MOR.errors');" />
                                               <label for="TABLE_MOR_available">可預約</label>
                                           </div>
                                           <div class="radio-item">
                                               <input type="radio" id="TABLE_MOR_unavailable" name="TABLE_MOR" value="2"
                                               ${param.TABLE_MOR == '2' ? 'checked' : ''}
                                               onclick="hideContent('TABLE_MOR.errors');" />

                                               <label for="TABLE_MOR_unavailable">不開放</label>
                                           </div>
                                           <input type="hidden" id="hidden_TABLE_MOR" name="TABLE_MOR" value="${param.TABLE_MOR}">
                                       </div>
                                   </div>
                                   <span id="TABLE_MOR.errors" class="error">${errorMsgs.TABLE_MOR}</span>

                       <div class="radio-group">
                           <div class="radio-group-label">
                               <label>下午(13-16):</label>
                           </div>
                           <div class="radio-buttons">
                               <div class="radio-item">
                                   <input type="radio" id="TABLE_EVE_available" name="TABLE_EVE" value="0"
                                   ${param.TABLE_EVE == '0' ? 'checked' : ''}
                                   onclick="hideContent('TABLE_EVE.errors');" />
                                   <label for="TABLE_EVE_available">可預約</label>
                               </div>
                               <div class="radio-item">
                                   <input type="radio" id="TABLE_EVE_unavailable" name="TABLE_EVE" value="2"
                                   ${param.TABLE_EVE == '2' ? 'checked' : ''}
                                   onclick="hideContent('TABLE_EVE.errors');" />
                                   <label for="TABLE_EVE_unavailable">不開放</label>
                               </div>
                               <input type="hidden" id="hidden_TABLE_EVE" name="TABLE_EVE" value="${param.TABLE_EVE}">
                           </div>
                       </div>
                       <span id="TABLE_EVE.errors" class="error">${errorMsgs.TABLE_EVE}</span>

                        <div class="radio-group">
                            <div class="radio-group-label">
                                <label>晚上(17-20):</label>
                            </div>
                            <div class="radio-buttons">
                                <div class="radio-item">
                                    <input type="radio" id="TABLE_NIGHT_available" name="TABLE_NIGHT" value="0"
                                    ${param.TABLE_NIGHT == '0' ? 'checked' : ''}
                                    onclick="hideContent('TABLE_NIGHT.errors');" />
                                    <label for="TABLE_NIGHT_available">可預約</label>
                                </div>
                                <div class="radio-item">
                                    <input type="radio" id="TABLE_NIGHT_unavailable" name="TABLE_NIGHT" value="2"
                                    ${param.TABLE_NIGHT == '2' ? 'checked' : ''}
                                    onclick="hideContent('TABLE_NIGHT.errors');" />
                                    <label for="TABLE_NIGHT_unavailable">不開放</label>
                                </div>
                                 <input type="hidden" id="hidden_TABLE_NIGHT" name="TABLE_NIGHT" value="${param.TABLE_NIGHT}">
                            </div>
                        </div>

                        <span id="TABLE_NIGHT.errors" class="error">${errorMsgs.TABLE_NIGHT}</span>




			<div>
				<div></div>
				<input  type="hidden" name="action" value="updatetab">
				<button type="submit" id="submit" class="btn btn-primary "> 修改 </button>
				<a href="<%=request.getContextPath()%>/view/bookingtast/Modifytimeperiod.jsp" class="btn btn-secondary btn-space">返回</a>
				<div></div>
			</div>
		</form>
	</div>
</div>

<!-- JavaScript part -->
<script>
window.onload = function() {
    var tableNightValue = "${param.TABLE_NIGHT}";
    var tableEveValue = "${param.TABLE_EVE}";
    var tableMorValue = "${param.TABLE_MOR}";

    if (tableNightValue == '1') {
        document.getElementById('TABLE_NIGHT_available').disabled = true;
        document.getElementById('TABLE_NIGHT_unavailable').disabled = true;
    // 設置隱藏input的值
    document.getElementById('hidden_TABLE_NIGHT').value = tableNightValue;
    }
    if (tableEveValue == '1') {
        document.getElementById('TABLE_EVE_available').disabled = true;
        document.getElementById('TABLE_EVE_unavailable').disabled = true;
        // 設置隱藏input的值
       document.getElementById('hidden_TABLE_EVE').value = tableEveValue;
    }
    if (tableMorValue == '1') {
        document.getElementById('TABLE_MOR_available').disabled = true;
        document.getElementById('TABLE_MOR_unavailable').disabled = true;
        // 設置隱藏input的值
        document.getElementById('hidden_TABLE_MOR').value = tableMorValue;
    }
}
</script>



<script type="text/javascript">
//清除提示信息
function hideContent(d) {
     document.getElementById(d).style.display = "none";
}


</script>

</body>
</html>