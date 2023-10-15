async function validateMemStatus(){
    const response = await fetch('/PolyBrain/view/validateMemStatus',{
        method: 'POST',
        headers: {'content-type': 'application/json; charset:utf-8'},
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        const {memNo, memName, loginStatus} = data;
        $('ul#dropdown-menu').append(`
            <li><a class="dropdown-item" href="#!">會員專區</a></li>
            <li><a class="dropdown-item" href="#!">購物車</a></li>
            <li><hr class="dropdown-divider" /></li>
            `);
        if(loginStatus){
            $('span#memName').text(memName);
            $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/logout.html">登出</a></li>');
            let memDetail = [memNo, memName];
            return memDetail;
        }else{
            $('ul#dropdown-menu').append('<li><a id="logOut" class="dropdown-item" href="http://localhost:8080/PolyBrain/view/member/login.html">登入</a></li>');
        }
    });
    return response;
}