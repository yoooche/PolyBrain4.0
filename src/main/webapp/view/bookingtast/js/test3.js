window.addEventListener('DOMContentLoaded', event => {

     const datatablesSimple = document.getElementById('datatablesSimple');
//     Simple-DataTables
//     https://github.com/fiduswriter/Simple-DataTables/wiki


    console.log(datatablesSimple);
    if (datatablesSimple) {
       new simpleDatatables.DataTable(datatablesSimple);

   }

});
