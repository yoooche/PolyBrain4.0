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
                url: "/PolyBrain/general/BidItemList",
                method: "POST",
                data: function (d) {
                    return {
                        value: "selectAllBidItem"
                    };
                },
                dataSrc: "" 
            },
            columns: [
                { data: 'bidItemVo.bidItemNo', width: '120px' },
                {
                    data: 'bidItemPic', width: '130px',
                    render: function (data, type, row) {

                        if (type === 'display' && data && data.length > 0) { // 檢查data是否存在
                            var imagesHTML = '';
                            var base64Image = data[0];
                            // console.log(base64Image);
                            // var b = arrayBufferToBase64(base64Image);
                            imagesHTML += `<img src="data:image/jpeg;base64,${base64Image}" alt="Image"><br>`;
                            return imagesHTML;
                        }
                        return data;
                    }

                },
                {
                    data: 'bidItemVo.itemClass.itemClassName', width: '60px',
                },
                {
                    data: 'bidItemVo.bidItemName', width: '120px',
                    render: function (data, type, row) {
                        return '<a href="" target="_blank" style="text-decoration: none;">' + data + '</a>' // 這邊是加連結
                    }
                },
                {
                    data: 'bidItemVo.bidItemDescribe', "className": "none"    //加none代表為上面第五種type默認隱藏
                },
                {
                    data: 'bidItemVo.gamePublisher', width: '160px',
                }, 
                {
                    data: null, title: "操作功能", width: '100px',  // 這邊是欄位
                    render: function (data, type, row) {
                        return '<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#edit">編輯</button> ' +
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
                    targets: [0, 1, 2, 3, 5, 6],
                    className: 'text-center'

                }
            ],
            // 其他设置...
            createdRow: function (row, data, dataIndex) {
                $(row).find('td').css('height', '100px'); // 将所有列的高度设置为150像素
            }
        });
    }
    //點擊新增商品
    const addButton = document.getElementById('bt-add_item');
    addButton.addEventListener('click', () => {
        const inputs = document.querySelectorAll('input');

        // 通過 fetch 取得遊戲類別列表
        // fetch('http://localhost:8080/PolyBrain/general/item/ItemClass', {
        //     method: 'GET'
        // })
        //     .then(response => {
        //         return response.json();
        //     })
        //     .then(itemClasses => {
        //         categoryMap = {}; // 清空 categoryMap，重新填充数据
        //         itemClasses.forEach(itemClass => {
        //             categoryMap[itemClass.itemClassNo] = itemClass.itemClassName;
        //         });

        //         // 生成選項並添加到遊戲類別的 select 元素中
        //         for (const category of itemClasses) {
        //             const option = document.createElement('option');
        //             option.value = category.itemClassNo;
        //             option.textContent = category.itemClassName;
        //             document.getElementById('itemClassNo').appendChild(option);
        //         }
        //     })
        //     .catch(error => {
        //         console.error("发生错误：", error);
        //     });

        addsubmit.addEventListener('click', () => {

        let errorMsg = '';

            if ($('#itemClassNo').val() == 0) {
                errorMsg += '<li>請選擇遊戲類別';
            }
            if ($('#bidItemName').val().length < 1 || $('#bidItemName').val().length > 20) {
                errorMsg += '<li>商品名稱須介於1~20字';
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

        console.log("itemImageList=", itemImageList);
        let bidItemName = $('#bidItemName').val();
        let bidItemDescribe = $('#bidItemDescribe').val();
        let gamePublisher = $('#gamePublisher').val();
        let itemClassNo = $('#itemClassNo').val();

        let data = {
            bidItemVo: {
                bidItemName: bidItemName,
                bidItemDescribe: bidItemDescribe,
                gamePublisher: gamePublisher,
                itemClassNo: itemClassNo

            },
            bidItemPic: itemImageList
        }

        console.log(data);
        console.log(bidItemPic);
        fetch("/PolyBrain/general/BidItemAdd", {
            method: 'POST',
            headers: {'content-type': 'application/json;charset=UTF-8'},
            body: JSON.stringify(data)
        })
        .then(resp => resp.json())
        .then(body => {
                    const { success } = body;
                    if (success) {
                        for (let input of inputs) {
                            input.disabled = true;
                        }
                        addsubmit.disabled = true;
                        Swal.fire('新增成功').then(() => {
                            window.location.href = "../bid/BidItemTable.html";
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '新增失敗...',
                            text: '有些地方發生錯誤，請聯繫系統管理員!',
                        })
                    }
        })

        });
    });


    //燈箱效果
    const lightboxClose = document.querySelector('#lightbox-close');
    lightboxClose.addEventListener('click', () => {
        lightbox.style.display = 'none';
    });

    // 初始化 DataTable，首次加载
    initializeDataTable([]);
});


$(document).ready(function () {

    ImgUpload();
    selectClass();

    // 绑定删除按钮的點擊事件
    $('#itemTable tbody').on('click', '.btn-remove', function () {
        const rowData = dataTable.row($(this).closest('tr')).data();
        bidItemNo = rowData.bidItemVo.bidItemNo;
        console.log(bidItemNo);

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
                fetch('/PolyBrain/general/bidItemRemove', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
                    body: JSON.stringify({ bidItemNo })
                })
                    .then(resp => resp.json())
                    .then(body => {
                        location.reload();
                    });
            }
        });
    });

    let imgArray2 = []; // 宣告圖片陣列
    //绑定編輯按钮的點擊事件
    $('#itemTable tbody').on('click', '.btn-edit', function () {
        ImgUpload2();
        const rowData = dataTable.row($(this).closest('tr')).data();
        console.log(rowData);
        $('#editBidItemNo').val(rowData.bidItemVo.bidItemNo);
        $('#editBidItemclassno').val(rowData.bidItemVo.itemClass.itemClassNo);
        $('#editBidItemName').val(rowData.bidItemVo.bidItemName);
        $('#editGamePublisher').val(rowData.bidItemVo.gamePublisher);
        $('#editBidItemDescribe').val(rowData.bidItemVo.bidItemDescribe);
        var imgWrap2 = $('.upload_img-wrap2');
        itemImageList2 = [];
        // 迭代圖片集合
        rowData.bidItemPic.forEach(function (imgObj) {
            itemImageList2.push( "data:image/jpeg;base64," +imgObj);
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

                if (errorMsg !== '') {
                    const errorList = errorMsg.split('<li>').filter(bidItemVo => bidItemVo !== '').map((bidItemVo, index) => {
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
                const data=new FormData;
                // data.append('bidItemNo',editBidItemNo.value);
                let Data = {
                    bidItemVo: {
                        bidItemNo: editBidItemNo.value,
                        bidItemDescribe: editBidItemDescribe.value,
                    },

                    bidItemPic: itemImageList2,
                }
                // console.log(bidItemPic);
                console.log(Data);

                fetch('/PolyBrain/general/BidItemAdd', {
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
                            window.location.href = "../bid/BidItemTable.html";
                        });

                    }).catch(Swal.fire({
                        // icon: 'error',
                        title: '修改成功...',
                        // text: '有些地方發生錯誤，請聯繫系統管理員!',
                        
                    })
                    .then(() => {
                        window.location.href = "../bid/BidItemTable.html";
                    })
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

itemImageList = [];
itemImageList2 = [];

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
    });

    
}
function selectClass(){
    // $('.bidItemNo').html('<option disabled selected>--請選擇--</option>');
    console.log("111");
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
        console.log("有嗎");
        for(let itemClass of data){
            var select = document.querySelectorAll('.itemClass');
            let option = document.createElement('option');
            option.value = itemClass.itemClassNo;
            option.textContent = itemClass.itemClassName;
            for(let i = 0; i < select.length; i++){
                select[i].appendChild(option.cloneNode(true));
            }
        }
    })
    .catch(error => {
        console.log("error", error);
    });
}
// function selectClass(){ //下拉式選單動態生成

//     fetch('/PolyBrain/general/bidding',{
//         method: 'POST',
//         headers: {
//             'content-type': 'application/x-www-form-urlencoded'
//         },
//         body: new URLSearchParams({
//             message: 'selectClass'
//         })
//     })
//     .then(resp => resp.json())
//     .then(data => {
//         // console.log('yyy')
//         for(let itemClass of data){
//             let option = document.createElement('option');
//             option.value = itemClass.itemClassNo;
//             option.textContent = itemClass.itemClassName;
//             // console.log(option.value);
//             // console.log(option.textContent);
//             let itemClassSelect = document.getElementById('itemClassNo');
//             itemClassSelect.appendChild(option);
//         }
//     })
//     .catch(error => {
//         console.log("error", error);
//     });
// }
function ImgUpload2() {
    var imgWrap2 = "";
    imgArray2 = [];
    itemImageList2 = [];

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
                            itemImageList2.push(e.target.result); // 把圖片的Base64字串存到陣列
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
        }   
        $(this).parent().parent().remove();
        console.log(itemImageList2);
    });
    
}



