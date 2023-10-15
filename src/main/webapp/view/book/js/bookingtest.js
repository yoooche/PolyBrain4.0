$(document).ready(async function () {
    //會員登入判斷
         const memDetail = await validateMemStatus();
         if(memDetail == undefined){
           Swal.fire({
              title: '帳號未登入',
              text: '將跳轉至登入頁面',
              icon: 'warning',
              showCancelButton: false, // 這裡取消了取消按鈕
              confirmButtonText: '確定'
              }).then((result) => {
              if (result.isConfirmed) {
               window.location.href = 'http://localhost:8080/PolyBrain/view/member/login.html';
               }
           });
         }

         // 在這裡你可以使用 memDetail 中的 memNo
             console.log('memNo:', memDetail[0]);

        // 输入框
        let bookingnoInput = $("#bookingNumber");
        // 監聽输入事件
        bookingnoInput.on('input', function () {
            // 獲取输入的值
            let inputValue = bookingnoInput.val();
            console.log(inputValue);

            // 使用正則表達式檢查是否為數字
            if (!/^[0-9]*$/.test(inputValue)) {
                // 如果不是數字，將输入的值替換為只包含數字
                bookingnoInput.val(inputValue.replace(/[^0-9]/g, ''));
            }
        });

        // 訂購按鈕點擊事件
        let orderButton = document.querySelector("#order1");
        orderButton.addEventListener('click', function () {
            // 移除包含隐藏類的<tr>元素的隐藏
            const hiddenRows = document.querySelectorAll('.hidden');

            // 顯示 #content1
             $("#content1").css({
                  'border': '1px solid #4e4a43', /* 設置邊框樣式 */
                  'border-radius': '10px', /* 設置圓角 */
                  'padding': '0', /* 設置內邊距 */
             });
            // 初始化
            let bookingStatus = $("#bookingStatus").val();
            let startDate = $("#startDate").val();
            let endDate = $("#endDate").val();
            let bookingno = bookingnoInput.val();

            // 構建 AJAX 請求對象
            let requestData = {
            state: bookingStatus,
            memNo: memDetail[0]
             };

            //檢查是否選擇了起始日期和结束日期
            if ((!startDate && endDate) || (startDate && !endDate)) {
                Swal.fire({
                    title: "請選擇起/迄日期",
                    text: "要記得起迄日都要選!",
                    button: "關閉"
                });
                return;
            } else {
                // 只有在選擇了日期時才添加日期條件
                if (startDate && endDate) {
                    requestData.startDate = startDate;
                    requestData.endDate = endDate;
                }

                // 如果输入了訂單號，將其添加到请求數據中
                if (bookingno) {
                    requestData.bookingno = bookingno;
                }
            }

            // 發送AJAX請求以獲取數據
            $.ajax({
                type: 'GET',
                url: '/PolyBrain/loginRequired/booking/one',
                data: requestData,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                success: function (response) {
                    console.log(response.length);
                    if (response.length === 0) {
                        Swal.fire({
                            title: "查無訂單",
                            text: "請重新輸入",
                            button: "關閉"
                        });
                        return;
                    }

                    // 顯示隐藏的行
                    hiddenRows.forEach(function (row) {
                        row.classList.remove('hidden');
                    });

                    // 表格生成位置（table id）
                    const tableBody = document.getElementById('tableBody');
                    tableBody.classList.add("btn-test");
                    tableBody.innerHTML = ''; // 清空表格内容

                    // 设置对应的键值映射
                    const statusMap = {
                        0: '已預約',
                        1: '取消'
                    };
                    const checkStateMap = {
                        0: '已報到',
                        1: '未報到',
                        2: '待報到'
                    };

                    const periodTimeMap = {
                        0: '上午',
                        1: '下午',
                        2: '晚上'
                    };

                    // 分頁相關配置
                    const itemsPerPage = 10; // 每頁顯示的數據項數
                    const totalPages = Math.ceil(response.length / itemsPerPage);
                    console.log(totalPages);
                    let currentPage = 1;
                    // 更新分頁控件
                    $('#pagination-container').pagination({
                        dataSource: function (done) {
                            //將response的個數分成
                            done(response.slice((currentPage - 1) * itemsPerPage, totalPages * itemsPerPage));
                        },
                        showPageNumbers: true,
                        pageSize: 10,
                        showPrevious: true,
                        showNext: true,
                        callback: function (data) {
                            // 渲染data到頁面中
                            tableBody.innerHTML = ''; // 清空表格内容
                            data.forEach((booking) => {
                                // 創建表格行和單元格並把數據填入
                                let row = tableBody.insertRow();
                                let bookingnoCell = row.insertCell();
                                let bookingstateCell = row.insertCell();
                                let bookingcheckstateCell = row.insertCell();
                                let periodtimeCell = row.insertCell();
                                let bookingdateCell = row.insertCell();
                                //
                                let memnoCell;
                                // 跌代statusMap取出他的值
                                bookingnoCell.innerText = booking.bookingno;
                                bookingstateCell.innerText = statusMap[booking.bookingstate];
                                bookingcheckstateCell.innerText = checkStateMap[booking.bookingcheckstate];
                                periodtimeCell.innerText = periodTimeMap[booking.periodtime];

                                // 處理日期格式
                                let dateCell = booking.tabledate;
                                var Time1 = new Date(dateCell);
                                var year = Time1.getFullYear();
                                var month = ('0' + (Time1.getMonth() + 1)).slice(-2);
                                var day = ('0' + Time1.getDate()).slice(-2);
                                var bookingdate = year + '-' + month + '-' + day;
                                bookingdateCell.innerText = dateCell;
                                memnoCell = booking.memno;
                                // 燈箱效果明细
                                row.addEventListener("click", function () {
                                    $.ajax({
                                        type: 'GET',
                                        url: '/PolyBrain/loginRequired/booking/list',
                                        data: null,
                                        dataType: 'json',
                                        contentType: 'application/json;charset=UTF-8',
                                        success: function (response) {
                                            let matchFound = false;
                                            response.forEach((booklist) => {
                                                console.log(booklist.bookingno);
                                                console.log(booking.bookingno);
                                                const statusMap = { 0: '已報到', 1: '未報到', 2: '待報到' };
                                                const statusMap2 = { 0: '上午', 1: '下午', 2: '晚上' };
                                                console.log(matchFound);
                                                if (!matchFound && booklist.bookingno === booking.bookingno) {
                                                    // sweetalert裡面的內容
                                                    matchFound = true;
                                                    const bookingDetailsHTML = `
                                                        <p>預約編號: ${booklist.bookingno}</p>
                                                        <p>桌號: ${booklist.tableno}</p>
                                                        <p>報到狀態: ${statusMap[booklist.bookingcheckstate]}</p>
                                                        <p>預約時段: ${statusMap2[booklist.periodtime]}</p>
                                                    `;

                                                    // 詳情顯示
                                                    Swal.fire({
                                                        title: "預約詳情",
                                                        html: bookingDetailsHTML,
                                                        button: "關閉!"
                                                    });
                                                }
                                            });
                                            if (!matchFound) {
                                                Swal.fire("查無批配單號", "", "error");
                                            }
                                        },
                                        error: function (error) {
                                            alert(error.status);
                                        }
                                    });
                                });

                                // 添加取消預約按鈕
                                if (booking.bookingstate == 0) {
                                    let actionCell = row.insertCell();
                                    let cancelButton = document.createElement("button");
                                    cancelButton.textContent = "取消預約";
                                    cancelButton.classList.add("btn", "btn-test", "btn-sm");
                                    cancelButton.addEventListener("click", function (event) {
                                        event.stopPropagation(); // 阻止点击事件传播，不触发行的点击事件
                                        Swal.fire({
                                            title: '取消預約',
                                            text: '確定要取消此預約嗎？',
                                            icon: 'warning',
                                            showCancelButton: true,
                                            confirmButtonText: '確定',
                                            cancelButtonText: '取消'
                                        }).then(function (result) {
                                            if (result.isConfirmed) {
                                                // 按下確認後取消
                                                let url = '/PolyBrain/loginRequired/booking/cancel'
                                                url += "?bookingNo=" + booking.bookingno
                                                $.ajax({
                                                    type: 'GET',
                                                    url: url,
                                                    success: function (response) {
                                                        Swal.fire("預定已取消", "您的預約已取消！", "success")
                                                            .then(function (result) {
                                                                if (result.isConfirmed) {
                                                                    cancelBooking();
                                                                    // SweetAlert確認後執行
                                                                    bookingstateCell.innerText = "未預定";
                                                                    bookingstateCell.classList.add("hidden-element");
                                                                    //
                                                                    //websocket
                                                                    // 在用戶按下取消按鈕時調用此函數
                                                                    function cancelBooking(event) {
                                                                        // 1. 建立WebSocket連接
                                                                        let webSocket = new WebSocket("ws://localhost:8080/PolyBrain/Notation/1002");

                                                                        // 2. 在WebSocket連接打開時執行的操作
                                                                        webSocket.onopen = function (event) {
                                                                            console.log("WebSocket連接已打開");
                                                                            let memNoValue = memnoCell; // 或者使用 innerText，根据需求选择
                                                                            // 3. 創建一個包含memNo的消息
                                                                            let message = {
                                                                                type: "cancellbtm",
                                                                                memNo: memNoValue,
                                                                                date: booking.tabledate,
                                                                                period: booking.periodtime,
                                                                                bookNo: booking.bookingno
                                                                            };
                                                                            console.log(memNoValue);
                                                                            console.log(message);
                                                                            // 4. 將消息轉換為JSON字符串並發送到後台管理員
                                                                            webSocket.send(JSON.stringify(message));
                                                                        };

                                                                        // 5. 監聽WebSocket的消息
                                                                        webSocket.onmessage = function (event) {
                                                                            console.log("接收到WebSocket消息：", event.data);

                                                                            // 在這裡您可以處理來自後台管理員的任何回覆
                                                                        };

                                                                        // 6. 監聽WebSocket的關閉
                                                                        webSocket.onclose = function (event) {
                                                                            console.log("WebSocket連接已關閉");
                                                                        };

                                                                        // 注意：如果有錯誤，您也可以添加一個onerror處理程序來處理WebSocket錯誤
                                                                    }
                                                                    //
                                                                    console.log("有經過");
                                                                    // 还原时段
                                                                    $.ajax({
                                                                        type: 'GET',
                                                                        url: '/PolyBrain/loginRequired/booking/cancelstate',
                                                                        data: {
                                                                            periodtime: booking.periodtime,
                                                                            tableno: booking.tableno,
                                                                            tabledate: booking.tabledate
                                                                        },
                                                                        success: function (tableBResponse) {
                                                                        console.log("Ya");
                                                                            // 取消預約後觸發再點擊一次查詢重整
                                                                            $("#order1").trigger("click");
                                                                        },
                                                                        error: function (error) {
                                                                            console.error("還原時段請求失敗");
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                    },
                                                    error: function (error) {
                                                        Swal.fire("取消預定失敗", "請稍後再試！", "error");
                                                    }
                                                });
                                            }
                                        });
                                    });

                                    actionCell.appendChild(cancelButton); // 添加按鈕到表格中
                                    bookingstateCell.classList.add("hidden-element");
                                } else {
                                    actionCell = row.insertCell();
                                }
                            });
                        },
                    });
                },
                error: function (error) {
                    alert(error.status);
                }
            });
        });
});

    $(document).ready(function () {
        // 清空按鈕點擊事件
        let cleanButton = document.querySelector("#clean");
        cleanButton.addEventListener("click", function () {
            location.reload();
        });
    });
//會員判斷

    async function validateMemStatus(){
        sessionStorage.setItem('currentPage', window.location.href);
        const response = await fetch('/PolyBrain/general/validateMemStatus',{
            method: 'POST',
            headers: {'content-type': 'application/json; charset:utf-8'},
        })
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            console.log(data.loginStatus);
            const {memNo, memName, loginStatus} = data;
            //
            console.log(data);

            $('ul#dropdown-menu').append(`
                <li><a class="dropdown-item" href="#!">會員專區</a></li>
                <li><a class="dropdown-item" href="#!">購物車</a></li>
                <li><hr class="dropdown-divider" /></li>
                `);
            if(loginStatus){
                $('span#memName').text(memName);
                $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/logout.html">登出</a></li>');
                let memDetail = [memNo, memName];
                sessionStorage.setItem("bidOrderVo", JSON.stringify(data));
                 // 如果已經登入，不再執行以下這行，以避免無限迴圈
                if (window.location.href.indexOf('/PolyBrain/view/book/BookingCheck.html') === -1) {
                window.location.href = 'http://localhost:8080/PolyBrain/view/book/BookingCheck.html';
                }
                // 清除存儲的當前頁面 URL
                sessionStorage.removeItem('currentPage');
                return memDetail;
            }else{
                $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/login.html">登入</a></li>');
            }
        });
        return response;
    }
