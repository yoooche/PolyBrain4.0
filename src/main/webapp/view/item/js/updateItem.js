var imgWrap = $('.upload_img-wrap');

document.addEventListener("DOMContentLoaded", () => {
	const btnCancel = document.querySelector('#cancel');
	const itemName = document.querySelector('#itemName');
	const inputs = document.querySelectorAll('input');
	const updatesubmit = document.querySelector('#updatesubmit');

	//點擊送出按鈕後使用燈箱效果顯示確認取消對話框
	updatesubmit.addEventListener('click', () => {
		let errorMsg = '';

		if (editPrice.value <= 99) {
			errorMsg += '<li>售價有誤請再確認';
		}
		if (editQty.value < 1 && editState.value == 2) {
			errorMsg += '<li>庫存量為0，不得上架';
		}
		if (errorMsg !== '') {
			const errorList = errorMsg.split('<li>').filter(item => item !== '').map((item, index) => {
				return `<li><span style="margin-right: 10px;">${index + 1}.</span>${item}</li>`;
			}).join('');

			// 使用 SweetAlert2 顯示錯誤視窗
			Swal.fire({
				icon: 'error',
				title: '修改商品失敗...',
				html: `<ul style="text-align: left; padding-left: 120px; list-style-position: inside;">${errorList}</ul>`,
			});
			return;
		}
		
		console.log("itemImageList:", itemImageList);

		let Data = {
			item: {
				itemNo: editItemNo.value,
                itemPrice: editPrice.value,
                itemState: editState.value,
                itemQty: editQty.value,
                itemProdDescription: editProdDescription.value,
			},

			itemImageList: itemImageList,
		}
		console.log(itemImageList);
		console.log(Data);

		fetch('/PolyBrain/item/addItem', {
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
					Swal.fire('修改成功').then(() => {
						window.location.href = "../item/itemConsole.html";
					});
				} else {
					Swal.fire({
						icon: 'error',
						title: '修改失敗...',
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
				// 若使用者確定取消，則關閉燈箱
				document.getElementById('update_lightbox').style.display = 'none';
			}
		});
	});

});