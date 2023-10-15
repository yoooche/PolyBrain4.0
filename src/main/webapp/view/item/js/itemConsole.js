//不知道是啥的原本套版資料
window.addEventListener('DOMContentLoaded', event => {
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

let categoryMap = {};//宣告categoryMap變數
let dataTable; // 宣告 DataTables 變數
//data tables的資料匯入

$(document).ready(function () {
    // 初始化 DataTable
    function initializeDataTable(data) {
        if (dataTable) {
            dataTable.destroy(); // 銷毀當前的DataTables實例
        }

        dataTable = $('#itemTable').DataTable({
            "lengthMenu": [[5, 10, 15, 20, -1], [5, 10, 15, 20, "全部"]]
            , responsive: {
                breakpoints: [                        //設定五種螢幕尺寸的type
                    { name: 'desktop', width: Infinity },
                    { name: 'tablet-l', width: 1024 },//原本是768~1024不含768
                    { name: 'tablet-p', width: 767 },//
                    { name: 'mobile-l', width: 480 },
                    { name: 'mobile-p', width: 320 },
                    { name: 'none', width: 100 }     //隱藏
                ]
            },
            ajax: {
                url: "http://localhost:8080/PolyBrain/general/selectServlet",
                method: "POST",
                data: function (d) {
                    return {
                        value: "selectAll"
                    };
                },
                dataSrc: "" // 数据源为空，因为数据是直接数组
            },
            columns: [
                { data: 'itemNo', width: '120px' },
                {
                    data: 'itemImg', width: '130px',
                    render: function (data, type, row) {
                        // 如果是显示类型，返回包含图像的HTML
                        if (type === 'display' && data && data.length > 0) { // 添加对 data 是否存在的检查
                            var imagesHTML = '';
                            // 使用循环处理每个Base64编码的图像
                            var base64Image = data[0].itemImg;
                            imagesHTML += `<img src="${base64Image}" alt="Image"><br>`;
                            return imagesHTML;
                        }
                        // 如果是排序或其他类型，返回原始数据
                        return data;
                    }
                },
                {
                    data: 'itemClass.itemClassName', width: '120px',
                },
                {
                    data: 'itemName', width: '180px',
                    render: function (data, type, row) {
                        const itemNo = row.itemNo; // 取得 itemNo 的值
                        const link = `http://localhost:8080/PolyBrain/view/item/itemDetail.html?itemNo=${itemNo}`;
                        return `<a href="${link}" target="_blank">${data}</a>`;
                    }
                },
                { data: 'itemPrice', width: '100px' },
                { data: 'itemSales', width: '100px' },
                { data: 'itemQty', width: '120px' },
                {
                    data: 'itemState', width: '150px',
                    render: function (data, type, row) {
                        switch (data) {
                            case 0:
                                return '<span class="text-success">下架</span>';
                            case 1:
                                return '<span class="text-primary">上架</span>';
                            case 2:
                                return '<span class="text-danger">售罄</span>';
                        }
                    }
                },
                {
                    data: 'itemProdDescription', "className": "none"    //加none代表為上面第五種type默認隱藏
                }, {
                    data: null, title: "操作功能", width: '150px',  // 這邊是欄位
                    render: function (data, type, row) {
                        return '<button type="button" class="btn btn-warning btn-sm btn-edit">編輯</button> ' +
                            '<button type="button" class="btn btn-danger btn-sm btn-remove">刪除</button>'
                    }
                },
            ],
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/zh-HANT.json',  //DATATABLE設定為中文
            },
            columnDefs: [
                {
                    targets: [0], // 第一欄 
                    createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                    },
                },
                {
                    targets: [0, 1, 2, 3, 4, 5, 6, 7, 9],//_all才是全部欄
                    className: 'text-center'       //置中
                }
            ],
            // 其他设置...
            createdRow: function (row, data, dataIndex) {
                // 在此回调函数中，为每个单元格设置高度
                $(row).find('td').css('height', '100px'); // 将所有列的高度设置为150像素
            }
        });
    }


    // 初始化 DataTable，首次加载 ，否則刪除與編輯無法使用
    initializeDataTable([]);


    //點擊新增按鈕後使用燈箱效果顯示各種輸入框
    $('#bt-add_item').click(function () {
        document.getElementById('add_lightbox').style.display = 'block';
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
            // 获取DataTable的数据
            const data = dataTable.rows().data().toArray();

            // 检查是否存在具有相同名称的商品
            if (isDuplicateName = data.some(row => row.itemName === itemName.value)){
                errorMsg += '<li>已有同名遊戲存在';
            }
            if (itemClassNo.value == 0) {
                errorMsg += '<li>請選擇遊戲類別';
            }
            if (itemName.value.length < 1 || itemName.value.length > 20) {
                errorMsg += '<li>商品名稱須介於1~20字';
            }
            if (itemPrice.value <= 99) {
                errorMsg += '<li>售價有誤請再確認';
            }
            if (itemQty.value < 1 && itemState.value == 1) {
                errorMsg += '<li>庫存量為0，不得上架';
            }
            if (minPlayers.value == 0 || maxPlayers.value == 0) {
                errorMsg += '<li>請選擇遊戲人數';
            }
            if (parseInt(minPlayers.value) > parseInt(maxPlayers.value)) {
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
            } else {
                // 如果没有错误消息，将默认图片路径添加到请求中
                if (itemImageList.length === 0) {
                    itemImageList.push('img/nopic.jpg');
                }

                console.log("itemImageList:", itemImageList);

                Data = {
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
            }
        });

        //使用燈箱效果顯示確認取消對話框
        btnCancel.addEventListener('click', () => {
            console.log("取消新增");
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
                    console.log("取消並且清空itemImageList2");
                    // 清空输入框的值
                    $('#editItemNo').val('');
                    $('#editItemclassno').val('');
                    $('#editItemName').val('');
                    $('#editPrice').val('');
                    $('#editState').val('');
                    $('#editQty').val('');
                    $('#editProdDescription').val('');
                    itemImageList2 = [];
                    // 若使用者確定取消，則關閉燈箱

                }
                document.getElementById('add_lightbox').style.display = 'none';
            });
        });


    });

    // 點擊删除觸發燈箱及執行刪除
    $('#itemTable tbody').on('click', '.btn-remove', function () {
        const rowData = dataTable.row($(this).closest('tr')).data();
        itemNo = rowData.itemNo;

        // 使用燈箱效果顯示確認取消對話框
        Swal.fire({
            title: '確認要刪除該項商品？',
            text: "點擊確認後將刪除所有商品資料。",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '確定',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {
                // 若使用者確定取消，則發送刪除請求
                fetch('http://localhost:8080/PolyBrain/general/item/remove', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
                    body: JSON.stringify({ itemNo })
                })
                    .then(resp => resp.json())
                    .then(body => {
                        location.reload();
                    });
            }
        });
    });

    let imgArray2 = []; // 在函数外部声明 imgArray2 变量
    //绑定編輯按钮的點擊事件
    $('#itemTable tbody').on('click', '.btn-edit', function () {
        ImgUpload2();
        const rowData = dataTable.row($(this).closest('tr')).data();
        // 将行数据填充到编辑表单中
        $('#editItemNo').val(rowData.itemNo);
        $('#editItemclassno').val(rowData.itemClass.itemClassNo);
        $('#editItemName').val(rowData.itemName);
        $('#editPrice').val(rowData.itemPrice);
        $('#editState').val(rowData.itemState);
        $('#editQty').val(rowData.itemQty);
        // $('#minPlayers').val(rowData.minPlayer);
        // $('#maxPlayers').val(rowData.maxPlayer);
        // $('#gameTime').val(rowData.gameTime);
        $('#editProdDescription').val(rowData.itemProdDescription);
        var imgWrap2 = $('.upload_img-wrap2');

        // 遍历 itemImg 数组，将每个图片对象转换为新的格式
        rowData.itemImg.forEach(function (imgObj) {
            itemImageList2.push(imgObj.itemImg);
            imgArray2.push(imgObj); // 添加到 imgArray2 中
        });
        // 清空之前的顯示，以免重複顯示
        imgWrap2.empty();

        itemImageList2.forEach(function (imageInfo) {
            // 將現有圖片設置進去
            var html = "<div class='upload_img-box'><div style='background-image: url(" + imageInfo + ")' data-number='" + $(".upload_img-close2").length + "' data-file='" + imageInfo.filename + "' class='img-bg add_img-bg'><div class='upload_img-close2'></div></div></div>";
            imgWrap2.append(html);

        });

        document.getElementById('update_lightbox').style.display = 'block';
    });

    //點擊送出按鈕後使用燈箱效果顯示確認取消對話框
    $('#updatesubmit').on('click', (e) => {
        e.preventDefault(); // 阻止默認行為

        console.log("按下送出");

        Swal.fire({
            title: '確認要更新商品？',
            text: "點擊確認後將更新商品資料。",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '確定',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {
                // 點擊確認發送請求
                let errorMsg = '';

                if (editPrice.value <= 99) {
                    errorMsg += '<li>售價有誤請再確認';
                }
                if (editQty.value < 1 && editState.value == 1) {
                    errorMsg += '<li>庫存量為0，不得上架';
                }
                if (editQty.value > 0 && editState.value == 2) {
                    errorMsg += '<li>仍有庫存量，狀態不該為售完';
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

                console.log("itemImageList1234:", itemImageList2);

                let Data = {
                    item: {
                        itemNo: editItemNo.value,
                        itemPrice: editPrice.value,
                        itemState: editState.value,
                        itemQty: editQty.value,
                        itemProdDescription: editProdDescription.value,
                    },

                    itemImageList: itemImageList2,
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
                        addsubmit.disabled = true;
                        Swal.fire('修改成功').then(() => {
                            // 在 SweetAlert2確認後執行轉跳
                            window.location.href = "../item/itemConsole.html";
                        });

                    }).catch(
                    //     Swal.fire({
                    //     icon: 'error',
                    //     title: '123...',
                    //     text: '有些地方發生錯誤，請聯繫系統管理員!',
                    // })
                );
            }
        });
    });


    //使用燈箱效果顯示確認取消對話框
    $('#updatecancel').on('click', (e) => {
        e.preventDefault();
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
                console.log("取消並且清空itemImageList2");
                // 若使用者確定取消，則關閉燈箱
                itemImageList2 = [];
                document.getElementById('update_lightbox').style.display = 'none';
            }
        });
    });
});

// //插入圖片的效果
$(document).ready(function () {
    ImgUpload();
});

itemImageList = [];
itemImageList2 = [];
itemIndexMap = {};
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
                            itemImageList.push(e.target.result); // 把圖片的Base64字串存到陣列
                            var html = "<div class='upload_img-box'><div style='background-image: url(" + e.target.result + ")' data-number='" + $(".upload_img-close").length + "' data-file='" + f.name + "' class='img-bg add_img-bg'><div class='upload_img-close'></div></div></div>";
                            imgWrap.append(html);
                            iterator++;
                        }
                        reader.readAsDataURL(f);
                    }
                }
            });
            console.log(itemImageList);
        });
    });

    $('body').on('click', ".upload_img-close", function (e) {
        var file = $(this).parent().data("file");
        for (var i = 0; i < imgArray.length; i++) {
            if (imgArray[i].name === file) {
                imgArray.splice(i, 1);
                itemImageList.splice(i, 1); // 移除圖片Base64陣列的內容
                break;
            }
        }
        $(this).parent().parent().remove();
        console.log(itemImageList);
    });
}

function ImgUpload2() {
    itemImageList2 = [];
    var imgWrap2 = "";
    imgArray2 = [];
    $('.upload_inputfile').each(function () {
        $(this).on('change', function (e) {
            imgWrap2 = $(this).closest('.upload_box').find('.upload_img-wrap2');
            var maxLength = $(this).attr('data-max_length');

            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
            var iterator = 0;

            filesArr.forEach(function (f, index) {

                if (!f.type.match('image.*')) {
                    return;
                }

                if (imgArray2.length > maxLength) {
                    return false;
                } else {
                    var len = 0;
                    for (var i = 0; i < imgArray2.length; i++) {
                        if (imgArray2[i] !== undefined) {
                            len++;
                        }
                    }
                    if (len > maxLength) {
                        return false;
                    } else {
                        imgArray2.push(f);

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            const index = $(".upload_img-close2").length
                            itemImageList2.push(e.target.result); // 把圖片的Base64字串存到陣列
                            itemIndexMap[index] = e.target.result
                            var html = "<div class='upload_img-box'><div style='background-image: url(" + e.target.result + ")' data-number='" + $(".upload_img-close2").length + "' data-file='" + f.name + "' class='img-bg add_img-bg'><div class='upload_img-close2'></div></div></div>";
                            imgWrap2.append(html);
                            iterator++;
                        }
                        reader.readAsDataURL(f);
                    }
                }
            });
            console.log(itemImageList2);
        });
    });

    $('body').on('click', ".upload_img-close2", function (e) {
        var index = $(this).parent().data("number");
        if (index >= 0 && index < itemImageList2.length) {
            itemImageList2.splice(index, 1); // 根据索引删除图像数据
            delete itemIndexMap[index];
        }
        $(this).parent().parent().remove();
        console.log(itemImageList2);
        console.log(itemIndexMap);
    });

}