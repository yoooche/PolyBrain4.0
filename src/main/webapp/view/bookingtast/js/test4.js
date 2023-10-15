/*!
* Start Bootstrap - Blog Home v5.0.9 (https://startbootstrap.com/template/blog-home)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-blog-home/blob/master/LICENSE)
*/





$(document).ready(function () {


        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/PolyBrain/booking/test',
            data: null,
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            success: function (response) {
                console.log(response);
                const tableBody = document.getElementById('tableBody');
                tableBody.classList.add("btn-test");
                tableBody.innerHTML = ''; // 清空表格内容
                 //設定對應的key , value
                 const statusMap = {
                        0: '未預約',
                        1: '已預約'  };
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


                });

                },
            error: function (error) {
                alert(error.status);
            }

        });

});




