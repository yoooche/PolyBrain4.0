// 獲取 sessionStorage 中存儲的數據
const editedRowData = JSON.parse(sessionStorage.getItem('editedRowData'));
var img_list = document.getElementsByClassName("upload_img-wrap")[0];
const itemImageList = [];
// 將數據填充到編輯表單中
$('#editItemNo').val(editedRowData.itemNo);
$('#editItemclassno').val(editedRowData.itemClass.itemClassName);
$('#editItemName').val(editedRowData.itemName);
$('#editPrice').val(editedRowData.itemPrice);
$('#editState').val(editedRowData.itemState);
$('#editQty').val(editedRowData.itemQty);
$('#minPlayers').val(editedRowData.minPlayer);
$('#maxPlayers').val(editedRowData.maxPlayer);
$('#gameTime').val(editedRowData.gameTime);
$('#editProdDescription').val(editedRowData.itemProdDescription);
var imgWrap = $('.upload_img-wrap');
var file_el = document.getElementById("editFiles");

//拿取原有的圖片資料預覽
function previewExistingImages() {
    // var imgWrap = $('.upload_img-wrap'); // 获取图片容器

    // 將已存在的圖片資料 存到一個陣列內

    var edititemImg = [];
    // 遍历 itemImg 数组，将每个图片对象转换为新的格式
    editedRowData.itemImg.forEach(function (imgObj) {
        edititemImg.push({ src: imgObj.itemImg });
    });

    edititemImg.forEach(function (imageInfo) {
        // 將現有圖片設置進去
        var html = "<div class='upload_img-box'><div style='background-image: url(" + imageInfo.src + ")' data-number='" + $(".upload_img-close").length + "' data-file='" + imageInfo.filename + "' class='img-bg add_img-bg'><div class='upload_img-close'></div></div></div>";
        imgWrap.append(html);
    });

    // 執行刪除照片的邏輯
    $('body').on('click', ".upload_img-close", function (e) {
        var file = $(this).parent().data("file");
        //刪除圖片
        $(this).parent().parent().remove();
    });
}

// 頁面加載後 將該產品的預覽圖產出
$(document).ready(function () {
    previewExistingImages();
});


updt_submit.addEventListener('click', async () => {
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
    // 获取包含图像URL的所有 div 元素
    var divElements = document.querySelectorAll('.upload_img-box div');
    var imageUrls = [];

    // 遍历所有 div 元素
    divElements.forEach(function (divElement) {
        // 获取style属性中的background-image属性值
        var style = divElement.getAttribute('style');

        // 检查style是否存在
        if (style) {
            // 使用正则表达式从style属性值中提取所有URL
            var matches = style.match(/url\([^)]+\)/g);

            // 检查是否有匹配到URL
            if (matches) {
                matches.forEach(function (match) {
                    // 提取的URL将在match字符串中
                    var imageUrl = match.replace(/url\(['"]?(.*?)['"]?\)/, '$1');
                    imageUrls.push(imageUrl);
                });
            } 
        };
    });

    // 输出所有图像的URL
    let Data = {
        item: {
            itemNo: editItemNo.value,
            itemPrice: editPrice.value,
            itemState: editState.value,
            itemQty: editQty.value,
            itemProdDescription: editProdDescription.value,
        }
    }

    if (imageUrls) {
        Data.itemImageList = imageUrls;
    }

    const inputs = document.querySelectorAll('input');
    fetch('/PolyBrain/item/addItem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
        },
        body: JSON.stringify(Data),
    })
        .then(resp => resp.json())
        .then(body => {
            // console.log("Item Name Value:", itemName.value);
            const { success } = body;
            if (success) {
                for (let input of inputs) {
                    input.disabled = true;
                }
                updt_submit.disabled = true;
                Swal.fire('修改成功').then(() => {
                    window.location.href = "../item/itemConsole.html";
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: '修改失敗..',
                    text: '有些地方發生錯誤，請聯繫系統管理員除錯!',
                })
            }
        });
});


//插入圖片的效果
$(document).ready(function () {
    ImgUpload();
});

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