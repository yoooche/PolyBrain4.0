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
const ClassName = document.querySelector('#ClassName');

//data tables的資料匯入
$(document).ready(function () {
    let dataTable; // 宣告 DataTables 變數

    // 初始化 DataTable
    function initializeDataTable(data) {
        if (dataTable) {
            dataTable.destroy(); // 銷毀當前的DataTables實例
        }

        //從資料庫獲取遊戲類別及對應的編號
        fetch('http://localhost:8080/PolyBrain/general/item/ItemClass', {
            method: 'GET'
        })
            .then(response => {
                return response.json();
            })
            .then(itemClasses => {
                // const categoryMap = {}; // 清空 categoryMap，重新填充数据
                // itemClasses.forEach(itemClass => {
                //     categoryMap[itemClass.itemClassNo] = itemClass.itemClassName;
                // });
                // const entriesArray = Object.entries(categoryMap).map(item => {
                //     return {
                //         itemClassNo: item[0],
                //         itemClassName: item[1]
                //     };
                // });
                let entriesArray = [];
                for (let e of itemClasses) {
                    let itemClassNo = e.itemClassNo.toString();
                    let itemClassName = e.itemClassName;
                    entriesArray.push({ itemClassNo: itemClassNo, itemClassName: itemClassName });
                }

                // 在回调函数内部定义了 entriesArray 后，初始化 DataTable
                dataTable = $('#itemTable').DataTable({
                    "searching": false,
                    "lengthMenu": [[3, 6, 9, -1], [3, 6, 9, "全部"]],
                    data: entriesArray,
                    columns: [
                        { data: 'itemClassNo' },
                        { data: 'itemClassName' },
                        {
                            data: null, title: "操作功能",  // 這邊是欄位
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
                            targets: [0, 1, 2],//_all才是全部欄
                            className: 'text-center'       //置中
                        }
                    ],
                    // 其他设置...
                });
            })
            .catch(error => {
                console.error("发生错误：", error);

            });

    }
    // 初始化 DataTable，首次加载
    initializeDataTable([]);

    //點擊送出新增按鈕後使用燈箱效果顯示輸入框
    $('#bt-add_class').click( function () {
        // 打開燈箱並填充資料
        Swal.fire({
            title: '新增類別',
            input: 'text',
            inputValue:'',
            showCancelButton: true,
            inputValidator: (value) => {
                if (!value) {
                    return '請輸入類別名稱';
                }
            },
            preConfirm: (value) => {
                fetch('http://localhost:8080/PolyBrain/general/item/addItemClass', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                    body: JSON.stringify({
                        itemClassName: value
                    }),
                })
                    .then(resp => resp.json())
                    .then(body => {
                        if (body.success) {
                            location.reload();// 成功后刷新页面
                        }
                    });
            }
        });
    });


    $('#itemTable').on('click', '.btn-edit', function () {
        const rowData = dataTable.row($(this).closest('tr')).data();
        // 將需要編輯的資料存儲到 localStorage 中
        localStorage.setItem('rowDataToEdit', JSON.stringify(rowData));
        // 打開燈箱並填充資料
        Swal.fire({
            title: '編輯類別',
            input: 'text',
            inputValue: rowData.itemClassName,
            showCancelButton: true,
            inputValidator: (value) => {
                if (!value) {
                    return '請輸入類別名稱';
                }
            },
            preConfirm: (value) => {
                fetch('http://localhost:8080/PolyBrain/general/item/addItemClass', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                    body: JSON.stringify({
                        itemClassNo: rowData.itemClassNo,
                        itemClassName:value
                    }),
                })
                    .then(resp => resp.json())
                    .then(body => {
                        if (body.success) {
                            location.reload();// 成功后刷新页面
                        }
                    });
            }
        });
    });

    //點擊刪除按鈕 刪除該項
    $('#itemTable').on('click', '.btn-remove', function () {
        const rowData = dataTable.row($(this).closest('tr')).data();
        onRemoveClick(rowData.itemClassNo);
    });

    //刪除方法
    function onRemoveClick(itemClassNo) {
        console.log(itemClassNo);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success',
          cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
      })
      
      swalWithBootstrapButtons.fire({
        title: '確定要刪除?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '是, 確定刪除該項!',
        cancelButtonText: '否, 返回上一步!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
            // 在確認按鈕點擊後觸發刪除請求
            fetch('http://localhost:8080/PolyBrain/general/item/ClassRemove', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
                body: JSON.stringify({
                    itemClassNo:itemClassNo,
                }),
            })
            .then(resp => resp.json())
            .then(body => {
                if (body.success) {
                    swalWithBootstrapButtons.fire(
                        '刪除!',
                        '該類別已從資料庫移除',
                        'success'
                    ).then(() =>{
                        location.reload();// 成功后刷新页面
                    })
                }
            });
        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
          )
        }
      })
    }
});


// $(document).ready(function () {


    // //點擊送出按鈕後使用燈箱效果顯示確認取消對話框  
    // addsubmit.addEventListener('click', () => {
    //     if (!confirm('確定新增「' + ClassName.value + '」為新的類別嗎?')) {
    //         return;
    //     }

    //     fetch('http://localhost:8080/PolyBrain/item/addItemClass', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json;charset=UTF-8',
    //         },
    //         body: JSON.stringify({
    //             itemClassName: ClassName.value
    //         }),
    //     })
    //         .then(resp => resp.json())
    //         .then(body => {
    //             if (body.success) {
    //                 location.reload();// 成功后刷新页面
    //             }
    //         });
    // });


    // // 获取按钮和燈箱元素
    // const lightbox = document.getElementById("lightbox");

    // // 点击新增類別按钮时显示燈箱
    // document.getElementById("bt-add_class").addEventListener("click", () => {
    //     lightbox.style.display = "flex";
    // });

    // // 点击燈箱外部时关闭燈箱
    // lightbox.addEventListener("click", (event) => {
    //     if (event.target === lightbox) {
    //         if (!confirm('確定關閉新增視窗?')) {
    //             return;
    //         }
    //         lightbox.style.display = "none";
    //     }
    // });

    // // 關閉燈箱
    // document.getElementById("lightbox-close").addEventListener("click", () => {
    //     lightbox.style.display = "none";
    // });

    // //點擊關閉燈箱按鈕
    // document.getElementById("cancel").addEventListener("click", () => {
    //     if (!confirm('確定關閉新增視窗?')) {
    //         return;
    //     }
    //     lightbox.style.display = "none";
    // });




    // //绑定編輯按钮的點擊事件
    // $('#itemTable tbody').on('click', '.btn-edit', function () {
    //     const rowData = dataTable.row($(this).closest('tr')).data();
    //     // 将行数据填充到编辑表单中
    //     localStorage.setItem('editedRowData', JSON.stringify(rowData));
    //     // 轉跳到 updateItem.html 頁面
    //     window.location.href = "updateItem.html";
    // });

// });

