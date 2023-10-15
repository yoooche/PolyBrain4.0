
document.addEventListener("DOMContentLoaded", () => {
	const btnCancel = document.querySelector('#cancel');
	const itemPrice = document.querySelector('#itemPrice');
	const itemName = document.querySelector('#itemName');
	const itemQty = document.querySelector('#itemQty');
	const itemState = document.querySelector('#itemState');
	const maxPlayers = document.querySelector('#maxPlayers');
	const minPlayers = document.querySelector('#minPlayers');
	const gameTime = document.querySelector('#gameTime');
	const inputs = document.querySelectorAll('input');
	// const itemClassNoSelect = document.getElementById('itemClassNo');

	// 通過 fetch 取得遊戲類別列表
	fetch('http://localhost:8080/PolyBrain/general/item/ItemClass', {
		method: 'GET'
	})
		.then(response => {
			return response.json();
		})
		.then(itemClasses => {
			categoryMap = {}; // 清空 categoryMap，重新填充数据
			itemClasses.forEach(itemClass => {
				categoryMap[itemClass.itemClassNo] = itemClass.itemClassName;
			});

			// 生成選項並添加到遊戲類別的 select 元素中
			for (const category of itemClasses) {
				const option = document.createElement('option');
				option.value = category.itemClassNo;
				option.textContent = category.itemClassName;
				document.getElementById('itemClassNo').appendChild(option);
			}
		})
		.catch(error => {
			console.error("发生错误：", error);
		});

	//點擊送出按鈕後使用燈箱效果顯示確認取消對話框
	addsubmit.addEventListener('click', () => {
		let errorMsg = '';

		if (itemClassNo.value == 0) {
			errorMsg += '<li>請選擇遊戲類別';
		}
		if (itemName.value.length < 1 || itemName.value.length > 20) {
			errorMsg += '<li>商品名稱須介於1~20字';
		}
		if (itemPrice.value <= 99) {
			errorMsg += '<li>售價有誤請再確認';
		}
		if (itemQty.value < 1 && itemState.value == 2) {
			errorMsg += '<li>庫存量為0，不得上架';
		}
		if (minPlayers.value == 0 || maxPlayers.value == 0) {
			errorMsg += '<li>請選擇遊戲人數';
		}
		if (minPlayers.value > maxPlayers.value) {
			errorMsg += '<li>遊戲人數設定有誤';
		}
		if (gameTime.value == 0) {
			errorMsg += '<li>請選擇遊戲時間';
		}
		if (errorMsg !== '') {
			const errorList = errorMsg.split('<li>').filter(item => item !== '').map((item, index) => {
				return `<li><span style="margin-right: 10px;">${index + 1}.</span>${item}</li>`;
			}).join('');

			// 使用 SweetAlert2 顯示錯誤視窗
			Swal.fire({
				icon: 'error',
				title: '新增商品失敗...',
				html: `<ul style="text-align: left; padding-left: 120px; list-style-position: inside;">${errorList}</ul>`,
			});
			return;
		}
		
		console.log("itemImageList:", itemImageList);

		let Data = {
			item: {
				itemClassNo: itemClassNo.value,
				itemName: itemName.value,
				itemPrice: itemPrice.value,
				itemState: itemState.value,
				itemQty: itemQty.value,
				minPlayer: minPlayers.value,
				maxPlayer: maxPlayers.value,
				gameTime: gameTime.value,
				itemProdDescription: itemProdDescription.value,
			},

			itemImageList: itemImageList,
		}
		console.log(itemImageList);
		console.log(Data);

		fetch('/PolyBrain/general/item/addItem', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
			},
			body: JSON.stringify(Data),
		})
			.then(resp => resp.json())
			.then(body => {
				console.log("Item Name Value:", itemName.value);
				const { success } = body;
				if (success) {
					for (let input of inputs) {
						input.disabled = true;
					}
					addsubmit.disabled = true;
					Swal.fire('新增成功').then(() => {
						window.location.href = "../item/itemConsole.html";
					});
				} else {
					Swal.fire({
						icon: 'error',
						title: '新增失敗...',
						text: '有些地方發生錯誤，請聯繫系統管理員!',
					})
				}
			});
	});


	//使用燈箱效果顯示確認取消對話框
	btnCancel.addEventListener('click', () => {
		Swal.fire({
			title: '確認要離開頁面？',
			text: "點擊確認後將不會儲存任何已輸入的資料。",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '確定',
			cancelButtonText: '取消'
		}).then((result) => {
			if (result.isConfirmed) {
				console.log("ttt");
				// 若使用者確定取消，則關閉燈箱
				document.getElementById('add_lightbox').style.display = 'none';
			}
		});
	});

});

// // //插入圖片的效果
// $(document).ready(function () {
// 	ImgUpload();
// });

// const itemImageList = [];

// function ImgUpload() {
// 	var imgWrap = "";
// 	var imgArray = [];

// 	$('.upload_inputfile').each(function () {
// 		$(this).on('change', function (e) {
// 			imgWrap = $(this).closest('.upload_box').find('.upload_img-wrap');
// 			var maxLength = $(this).attr('data-max_length');

// 			var files = e.target.files;
// 			var filesArr = Array.prototype.slice.call(files);
// 			var iterator = 0;

// 			filesArr.forEach(function (f, index) {

// 				if (!f.type.match('image.*')) {
// 					return;
// 				}

// 				if (imgArray.length > maxLength) {
// 					return false;
// 				} else {
// 					var len = 0;
// 					for (var i = 0; i < imgArray.length; i++) {
// 						if (imgArray[i] !== undefined) {
// 							len++;
// 						}
// 					}
// 					if (len > maxLength) {
// 						return false;
// 					} else {
// 						imgArray.push(f);

// 						var reader = new FileReader();
// 						reader.onload = function (e) {
// 							itemImageList.push(e.target.result); // 把圖片的Base64字串存到陣列
// 							var html = "<div class='upload_img-box'><div style='background-image: url(" + e.target.result + ")' data-number='" + $(".upload_img-close").length + "' data-file='" + f.name + "' class='img-bg add_img-bg'><div class='upload_img-close'></div></div></div>";
// 							imgWrap.append(html);
// 							iterator++;
// 						}
// 						reader.readAsDataURL(f);
// 					}
// 				}
// 			});
// 			console.log(itemImageList);
// 		});
// 	});

// 	$('body').on('click', ".upload_img-close", function (e) {
// 		var file = $(this).parent().data("file");
// 		for (var i = 0; i < imgArray.length; i++) {
// 			if (imgArray[i].name === file) {
// 				imgArray.splice(i, 1);
// 				itemImageList.splice(i, 1); // 移除圖片Base64陣列的內容
// 				break;
// 			}
// 		}
// 		$(this).parent().parent().remove();
// 		console.log(itemImageList);
// 	});
// }

