//Calerdar js
$(document).ready(async function () {
    //會員判斷
     const memDetail = await validateMemStatus();
             // 在這裡你可以使用 memDetail 中的 memNo
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
                 var mem = memDetail[0];
        // step1 取得元素、初始化fullcalendar插件  ->  new FullCalendar.Calendar(html元素,初始化函式)

        // *屬性說明
        // initialDate    從哪一天當作起點 new Date():當下時間
        // weekends       周末要不要顯示事件
        // editable       是否開放使用者手動編輯
        // eventDidMount  會迭代每筆事件、自訂事件的渲染操作、info是指每筆事件、isSpecialDate 判斷是否是預約日
        // events         這是月曆的所有事件,透過addEvent()方法填入新事件

        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {


            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth'

            },
            initialDate: new Date(),
            weekends: true,
            editable: false,

            //事件建立，這邊新增單選按鈕
            eventDidMount: function (info) {
                if (info.event.extendedProps.isSpecialDate) {
                    var morchecked = "";
                    var evechecked = "";
                    var nightchecked = "";

                    if (info.event.extendedProps.tablemor != 0) {
                        morchecked = "checked disabled";
                    };
                    if (info.event.extendedProps.tableeve != 0) {
                        evechecked = "checked disabled";
                    };
                    if (info.event.extendedProps.tablenight != 0) {
                        nightchecked = "checked disabled";
                    };
                    let i = info.event.extendedProps.index;
                    const radios = `
                        <div>
                            <input id = "mor${i}" name ="period" type = "radio" ${morchecked} value="0">
                            <label for="mor${i}">早上</label><br>
                            <input id = "eve${i}" name ="period" type = "radio" ${evechecked} value="1">
                            <label for="eve${i}">下午</label><br>
                            <input id = "night${i}" name ="period" type = "radio" ${nightchecked} value="2">
                            <label for="night${i}">晚上</label>
                        </div>`;
                    info.el.insertAdjacentHTML('beforeend', radios);
                };
                //這是如果日期成立會增加單選選項進去日曆
                if (info.event.extendedProps.everyday) {
                    const radios = `
                        <div>
                            ${info.event.extendedProps.radio1}
                            ${info.event.extendedProps.radio2}
                            ${info.event.extendedProps.radio3}
                        </div>`;
                    info.el.insertAdjacentHTML('beforeend', radios);
                };
            },
            events: [
            ],

        });

        // 用于清空月历事件的函数
        function clearCalendarEvents() {
            calendar.getEvents().forEach(function (event) {
                event.remove();
            });
        }
        var selectedTable = null;
        // step2 發請求 假設已經成功回傳了 這是後端回傳的資料  應該是一個預約單的陣列
        $("#tableSelect").change(function () {
            //console.log("456");

            // table下拉選單的值
             selectedTable = $(this).val();
            console.log("用戶選擇了桌子：" + selectedTable);
            // 將所選的值放到tableno
            $('input[name="table"]').val(selectedTable);
            console.log("tableno：" + selectedTable);
            //console.log(selectedDate);
            //console.log(selectedPeriod);
            //console.log(selectedTable);
            // 發送Ajax請求獲得桌子開放狀態
            console.log(selectedTable);
            $.ajax({
                type: 'GET',
                url: '/PolyBrain/loginRequired/tablebooking',
                data: {
                    table: selectedTable
                },
                dataType: 'json',
                success: function (response) {
                    console.log(response);
                    // step3 用迴圈取得所有預約單資料 加入事件到月曆的events中
                    // addEvent():加入事件
                    // extendedProps 可以在事件中放入參數跟參數值 key:value
                    // isSpecialDate:true  定義這是特殊日
                    // 清空月曆事件
                    clearCalendarEvents();
                    // 獲取當前日期
                    const currentDate = new Date();
                    for (let i = 0; i < response.length; i++) {
                        // 將點擊的日期事件存到eventDate
                            const eventDate = new Date(response[i].tabledate);

                        // 檢查事件日期是否大於當天日期，小於的話則不渲染到日曆中
                        if (eventDate >= currentDate) {
                            const event = {
                                title: `預約Table: ${response[i].tableno}`,
                                start: response[i].tabledate,
                                extendedProps: {
                                    isSpecialDate: true,
                                    tablemor: response[i].tablemor,
                                    tableeve: response[i].tableeve,
                                    tablenight: response[i].tablenight,
                                    index: i
                                }
                            };
                        //console.log(response[i].tablebookno);

                        calendar.addEvent(event);
                    }
                }
            }
            });
        });

        // 初始化月曆
        calendar.render();
        $(document).ready(function () {
            // 拉到外面宣告 因為後續請求也會用到
            var selectedPeriod = null;
            var datePart = null;

            // 建立一個獲得日期參數的方法並且格式化
            function handleDateClick(clickedDate) {
                // 在控制台中显示日期
                console.log('點擊的日期：', clickedDate.toISOString());
                var Param = clickedDate.toISOString();
                datePart = Param.split('T')[0];
                console.log("日期:" + datePart);
            }

            // 添加點擊 "fc-daygrid-day" 類的元素，讓它都能觸發
            $(document).on('click', '.fc-daygrid-day', function () {
                var clickedDateString = $(this).data('date'); // 獲取日期
                var clickedDate = new Date(clickedDateString); //轉換日期對象
                handleDateClick(clickedDate); // 讓上述handleDateClick可以調用
            });

            // 獲取時段(period)
            $(document).on('click', 'input[name="period"]', function () {
                // 得到period參數 "0"、"1"、"2"
                selectedPeriod = $(this).val();

                console.log("Selected Period: " + selectedPeriod);
                var date = calendar.getDate();
                //alert("The current date of the calendar is " + date.toISOString());
            });
            console.log("tabledate+" + datePart);


        let ok = document.querySelector("#submit");
        ok.addEventListener('click', function () {
            // 防止表單提交的默認行為
            event.preventDefault();

            Swal.fire({
                title: '確認預約',
                text: '您確定要預約嗎？',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '是的',
                cancelButtonText: '取消'
            }).then((result) => {
                if (result.isConfirmed) {
                // 取得表單的值
                var formData = {
                    // bookdate、periodtime、tableno是VO對應
                    tabledate: datePart, // 使用 datePart 的值
                    periodtime: selectedPeriod,
                    tableno: selectedTable,
                    memno: mem
                }
                console.log(mem);
            // Ajax發送請求資料
            $.ajax({
                type: 'POST',
                url: '/PolyBrain/loginRequired/booking/insert',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    console.log(response);
                    console.log(response.success);
                    if (!formData.tableno) {
                                // 如果tableno或periodtime不存在，顯示SweetAlert2警告
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Oh no...',
                                    text: '請選擇桌號!'
                                });
                    }else if(!formData.periodtime){
                        Swal.fire({
                             icon: 'error',
                             title: 'Oh no...',
                             text: '請選擇時段!'
                        });
                    }else if (response.success) {
                        console.log("123");
                        const statusMap = { 0: '上午', 1: '下午', 2: '晚上' };
                        const bookingDetailsHTML = `
                          <p>日期: ${datePart}</p>
                          <p>時段: ${statusMap[selectedPeriod]}</p>
                          <p>桌號: ${selectedTable}</p>
                        `;
                        Swal.fire({
                            title: '預約詳情',
                            html: bookingDetailsHTML,
                            icon: 'success',
                            confirmButtonText: '關閉'
                        })
                            .then((result) => {
                                if (result.isConfirmed) {
                                    location.reload();
                                }
                            });
                            // Ajax另一個請求用來修改後台時段參數
                            $.ajax({
                                type: 'GET',
                                url: '/PolyBrain/loginRequired/booking/state',
                                data: {
                                    periodtime: formData.periodtime,
                                    tableno: formData.tableno,
                                    tabledate: formData.tabledate
                                },
                                success: function (tableBResponse) {
                                    console.log(tableBResponse);
                                },
                                error: function (error) {
                                    console.error("更改後台時段請求失敗：" + error.status);
                                }
                            })
                    }
                },
                error: function (error) {
                    alert(error.status);
                }
            });
               }
    });

        });

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
                if (window.location.href.indexOf('/PolyBrain/view/book/Calendar.html') === -1) {
                window.location.href = 'http://localhost:8080/PolyBrain/view/book/Calendar.html';
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
