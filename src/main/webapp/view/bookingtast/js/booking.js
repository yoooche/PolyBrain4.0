$(document).ready(function () {
    let a = document.querySelector("#order1");
    a.addEventListener('click', function () {
        // 初始化
        let bookingStatus = $("#bookingStatus").val();
        let startDate = $("#startDate").val();
        let endDate = $("#endDate").val();
        // 建立 AJAX 請求的對象(因為有不同條件)
        let requestData = { state: bookingStatus };
        // 只有在选择了日期时才添加日期条件
        if (startDate && endDate) {
            requestData.startDate = startDate;
            requestData.endDate = endDate;
        }
        //查全部-為表面列出
        $.ajax({
            type: 'GET',
            //BookingoneServlet
            url: 'http://localhost:8080/PolyBrain/booking/one',
            //請求的參數(servlet裡的getParameter) : html裡<select>的id
            //這邊是用requestData包起來
            data: requestData,
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            success: function (response) {
                console.log(response);
                //表格產生要放在哪(table id)
                const tableBody = document.getElementById('tableBody');
                tableBody.classList.add("btn-test");
                tableBody.innerHTML = ''; // 清空表格内容
                //設定對應的key , value
                const statusMap = {
                    0: '未預約',
                    1: '已預約'
                };
                response.forEach((booking) => {
                    let row = tableBody.insertRow();
                    let bookingnoCell = row.insertCell();
                    let memnoCell = row.insertCell();
                    let bookingstateCell = row.insertCell();
                    let bookingdateCell = row.insertCell();

                    //8月 12, 2023
                    let dateCell = booking.bookingdate;
                    var modifiedDateString = dateCell.replace(/月/g, "");

                    bookingnoCell.innerText = booking.bookingno;
                    memnoCell.innerText = booking.memno;
                    //迭帶statusMap取出他的值
                    bookingstateCell.innerText = statusMap[booking.bookingstate];
                    var Time1 = new Date(modifiedDateString);
                    var year = Time1.getFullYear();

                    var month = ('0' + (Time1.getMonth() + 1)).slice(-2);
                    var day = ('0' + Time1.getDate()).slice(-2);
                    var bookingdate = year + '-' + month + '-' + day;
                    console.log(bookingdate);
                    bookingdateCell.innerText = bookingdate;

                    //console.log(modifiedDateString); // 输出 "8 12, 2023"
                    //console.log(booking.bookingdate);
                    //console.log(bookingdateCell);
                    //燈箱效果明細
                    row.addEventListener("click", function () {
                        $.ajax({
                            type: 'GET',
                            url: 'http://localhost:8080/PolyBrain/booking/list',
                            data: null,
                            dataType: 'json',
                            contentType: 'application/json;charset=UTF-8',
                            success: function (response) {
                                let matchFound = false;
                                response.forEach((booklist) => {

                                    // 选择要显示的预约
                                    console.log(booklist.bookingno);
                                    console.log(booking.bookingno);
                                    const statusMap = { 0: '已報到', 1: '未報到', 2: '待報到' };
                                    const statusMap2 = { 0: '上午', 1: '下午', 2: '晚上' };
                                    console.log(matchFound);
                                    if (!matchFound && booklist.bookingno === booking.bookingno) {
                                        // 构建预约详细信息的HTML
                                        matchFound = true;
                                        const bookingDetailsHTML = `
                                            <p>預約編號: ${booklist.bookingno}</p>
                                            <p>桌號: ${booklist.tableno}</p>
                                            <p>報到狀態: ${statusMap[booklist.bookingcheckstate]}</p>
                                            <p>備註: ${String(booklist.noteother)}</p>
                                            <p>預約時段: ${statusMap2[booklist.periodtime]}</p>
                                        `;

                                        // 显示预约详细信息
                                        Swal.fire({
                                            title: "預約詳情",
                                            html: bookingDetailsHTML,
                                            button: "關閉!"
                                        });
                                    }
                                });
                                if (!matchFound) {
                                    Swal.fire("查無此匹配單號", "", "error");
                                }
                            },
                            error: function (error) {
                                alert(error.status);
                            }
                        });
                    });

                        // 添加取消預約按钮(還要加退回時段)
                        if (booking.bookingstate == 1) {
                        console.log(booking.bookingstate);
                            let actionCell = row.insertCell();
                            let cancelButton = document.createElement("button");
                            cancelButton.textContent = "取消預訂";
                            cancelButton.classList.add("btn", "btn-test", "btn-sm");
                            cancelButton.addEventListener("click", function (event) {
                                event.stopPropagation(); // 阻止点击事件传播，不触发行的点击事件
                                // 显示确认对话框并处理取消逻辑
                                let url = 'http://localhost:8080/PolyBrain/booking/cancel'
                                url += "?bookingNo=" + booking.bookingno
                                $.ajax({
                                    type: 'GET',
                                    url: url,
                                    success: function (response) {

                                            Swal.fire("預訂已取消", "您的預訂已被取消！", "success")
                                                .then((result) => {
                                                    if (result.isConfirmed) {
                                                        // SweetAlert确认后执行的操作
                                                        bookingstateCell.innerText = "未預約";
                                                        bookingstateCell.classList.add("hidden-element"); // 添加隐藏类名
                                                        // 在取消预约后触发查询按钮的点击事件
                                                        $("#order1").trigger("click");

                                                    }
//
                                                });

                                    },
                                    error: function (error) {
                                        Swal.fire("取消預訂失敗", "請稍後再試！", "error");
                                    }
                                });
                            });

                            actionCell.appendChild(cancelButton); // 添加按钮到表格中
//                            bookingstateCell.id = "bookingstate"; // 添加 id
                            bookingstateCell.classList.add("hidden-element"); // 添加隐藏类名
                        }else{
                            actionCell = row.insertCell();
                        };

                });
            },
            error: function (error) {
                   alert(error.status);
                }
    });
   });
});

$(document).ready(function () {
    // ...

    let cleanButton = document.querySelector("#clean");
    cleanButton.addEventListener("click", function () {
        const tableBody = document.getElementById('tableBody');
        tableBody.innerHTML = ''; // 清空表格内容
    });
});
