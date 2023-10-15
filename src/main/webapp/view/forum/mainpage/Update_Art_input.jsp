<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>







<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>貼文資料修改 - update_emp_input.jsp</title>


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
    .error {
        color: red;
    }
</style>
</head>
<body>



	<div align="center">

		<form action="<%=request.getContextPath()%>/Art/Art.do" method="post"  enctype="multipart/form-data">
                <div class="text-box">
			            <label for="post-title">文章編號:</label>
                        <input type="text" id="post-title" name="artNo" placeholder="請輸入文章編號" value="${param.artNo}" readonly>
                        <span id="artNo.errors" class="error">${errorMsgs.artNo}<br/></span>
                    </div>
                <div class="text-box">
			            <label for="post-title">會員編號:</label>
                        <input type="text" id="post-title" name="memNo" placeholder="請輸入會員編號" value="${param.memNo}" readonly>
                        <span id="memNo.errors" class="error">${errorMsgs.memNo}<br/></span>
                    </div>
                <div class="text-box">
			            <label for="post-title">貼文主題:</label>
                        <input type="text" id="post-title" name="artTitle" placeholder="請輸入貼文主題" value="${param.artTitle}">
                        <span id="artTitle.errors" class="error">${errorMsgs.artTitle}<br/></span>
                    </div>
                <div class="text-box">
                        <label for="post-content">貼文內容:</label>
                        <textarea id="post-content"  name="artCon" rows="10" placeholder="請輸入貼文內容" >${param.artCon}</textarea>
                        <span id="artTitle.artCon" class="error">${errorMsgs.artCon}<br/></span>
                    </div>
                 <div class="text-box">
                     <label for="artTime">貼文時間:</label>
                     <input type="text" id="artTime" name="artTime" placeholder="請輸入貼文時間" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>" readonly>
                     <span id="artTime.errors" class="error">${errorMsgs.artTime}<br/></span>
                 </div>
               <div class="text-box">
                   <label for="artState">貼文狀態:</label>
                   <select id="artState" name="artState">
                       <option value="0" ${param.artState == '0' ? 'selected' : ''}>未上架</option>
                       <option value="1" ${param.artState == '1' ? 'selected' : ''}>已上架</option>
                   </select>
                   <span id="artState.errors" class="error">${errorMsgs.artState}<br/></span>
               </div>
                <div class="text-box">
			            <label for="post-title">遊戲類別:</label>
                        <input type="text" id="post-title" name="itemNo" placeholder="請輸入遊戲類別" value="${param.itemNo}">
                        <span id="artGame.errors" class="error">${errorMsgs.itemNo}<br/></span>
                    </div>
                <div class="text-box">
                    	<label for="upFiles">照片:</label>
                    	<input id ="upFiles" name="upFiles" type="file" onclick="previewImage()" multiple="multiple" />
                        <%-- 可以不修改圖片 <span  id ="upFiles.errors" class="error">${errorMsgs.upFiles}</span> --%>
                        <div id="blob_holder"><img src="<%=request.getContextPath()%>/Art/DBGifReader?artNo=${param.artNo}" width="100px"></div>
                    	</div>



			<div>
				<div></div>
				<input  type="hidden" name="action" value="update">
				<button type="submit" id="submit"> 送出修改 </button>
				<div></div>
			</div>
		</form>
	</div>


<!-- JavaScript part -->
<script type="text/javascript">
//清除提示信息
function hideContent(d) {
     document.getElementById(d).style.display = "none";
}

//照片上傳-預覽用
var filereader_support = typeof FileReader != 'undefined';
if (!filereader_support) {
	alert("No FileReader support");
}
acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
};
function previewImage() {
	var upfile1 = document.getElementById("upFiles");
	upfile1.addEventListener("change", function(event) {
		var files = event.target.files || event.dataTransfer.files;
		for (var i = 0; i < files.length; i++) {
			previewfile(files[i])
		}
	}, false);
}
function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = new Image();
			image.src = event.target.result;
			image.width = 100;
			image.height = 75;
			image.border = 2;
			if (blob_holder.hasChildNodes()) {
				blob_holder.removeChild(blob_holder.childNodes[0]);
			}
			blob_holder.appendChild(image);
		};
		reader.readAsDataURL(file);
		document.getElementById('submit').disabled = false;
	} else {
		blob_holder.innerHTML = "<div  style='text-align: left;'>" + "● filename: " + file.name
				+ "<br>" + "● ContentTyp: " + file.type
				+ "<br>" + "● size: " + file.size + "bytes"
				+ "<br>" + "● 上傳ContentType限制: <b> <font color=red>image/png、image/jpeg、image/gif </font></b></div>";
		document.getElementById('submit').disabled = true;
	}
}
</script>

</body>
</html>