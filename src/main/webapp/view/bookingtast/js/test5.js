/*!
 * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
 * Copyright 2013-2023 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
 */
//
// Scripts
//
document.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

//document.addEventListener('DOMContentLoaded', () => {
//    const table = document.querySelector("#datatables");
//    const ths = table.querySelectorAll("th[data-sortable='true']");
//    ths.forEach(th => {
//        th.addEventListener("click", () => {
//            const column = th.textContent.toLowerCase();
//            const tbody = table.querySelector("tbody");
//            const rows = Array.from(tbody.querySelectorAll("tr"));
//
//            rows.sort((rowA, rowB) => {
//                const cellA = rowA.querySelector(`td[data-column='${column}']`);
//                const cellB = rowB.querySelector(`td[data-column='${column}']`);
//                return cellA.textContent.localeCompare(cellB.textContent);
//            });
//
//            const activeTh = table.querySelector(".datatable-sorter-active");
//            if (activeTh && activeTh !== th) {
//                activeTh.classList.remove("datatable-sorter-active", "datatable-ascending", "datatable-descending");
//            }
//
//            if (th.classList.contains("datatable-ascending")) {
//                rows.reverse();
//                th.classList.remove("datatable-ascending");
//                th.classList.add("datatable-descending");
//            } else {
//                th.classList.remove("datatable-descending");
//                th.classList.add("datatable-ascending");
//            }
//
//            tbody.innerHTML = "";
//            rows.forEach(row => tbody.appendChild(row));
//            th.classList.add("datatable-sorter-active");
//        });
//    });
//});
