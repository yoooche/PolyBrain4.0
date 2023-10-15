(() => {

  const email = document.querySelector('#email');
  const password = document.querySelector('#password');
  const errMsg = document.querySelector('#errMsg');


  document.querySelector('#btn_login').addEventListener('click', () => {

    let loginData = {
        empEmail: email.value,
        empPwd: password.value
    }

    fetch('empLogin', { /*------ Match Controller WebServlet Url ------*/
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(loginData)
    })
      .then(resp => {
        if(!resp.ok){
            throw new Error('error')
        }
        return console.log(resp.json);
      })
      .then(body => {
        errMsg.textContent = '';
        const { success, message } = body;
        if (success) {

          const { empNo, empName, empEmail } = body;
          console.log(empNo);
          sessionStorage.setItem('empNo', empNo);
          sessionStorage.setItem('empName', empName);
          sessionStorage.setItem('empEmail', empEmail);
        } else {
          errMsg.textContent = message;
        }
      }).catch(e => console.log(e))
  });
})();


