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


$(document).ready(function () {
    let dataTable; // 宣告 DataTables 變數
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
                url: "/selectAllServlet",
                method: "POST",
                dataSrc: "" // 数据源为空，因为数据是直接数组
            },
            columns: [
                { data: 'itemNo' },
                {
                    data: 'itemClassNo',
                    render: data => categoryMap[data]
                },
                {
                    data: 'itemName',
                    render: function (data, type, row) {
                        return '<a href="" target="_blank">' + data + '</a>' // 這邊是加連結
                    }
                },
                { data: 'itemPrice' },
                {
                    data: 'itemState',
                    render: data => data ? '上架' : '下架'
                },
                { data: 'itemQty' },
                {
                    data: 'itemProdDescription', "className": "none"    //加none代表為上面第五種type默認隱藏
                }, {
                    data: null, title: "操作功能",  // 這邊是欄位
                    render: function (data, type, row) {
                        return '<button type="button" class="btn btn-warning btn-sm">編輯</button> ' +
                            '<button type="button" class="btn btn-danger btn-sm">刪除</button>'
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
                    targets: [0, 1, 2, 3, 4, 5, 7],//_all才是全部欄
                    className: 'text-center'       //置中
                }
            ],
            // 其他设置...
        });
    }

    // 添加一个事件监听器，当鼠标悬停在省略的内容上时显示完整描述
    $('#itemTable tbody').on('mouseenter', '.ellipsis', function () {
        var content = $(this).attr('title');
        $(this).popover({
            trigger: 'manual',
            placement: 'top',
            content: content
        }).popover('show');
    }).on('mouseleave', '.ellipsis', function () {
        $(this).popover('hide');
    });

    const categoryMap = {
        1: '策略',
        2: '派對',
        3: '親子',
        4: '合作',
        5: '陣營',
        6: '派對'
    };

    // 初始化 DataTable，首次加载
    initializeDataTable([]);
});



