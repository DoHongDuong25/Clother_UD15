function quayLai() {
  window.location.href = "/admin/BanHangTaiQuay";
}

function flexUrlSubmitBanHangTaiQuay(url, method, formName) {
  $("#flexUrlTableForm" + formName).attr("action", url);
  $("#flexUrlTableForm" + formName).attr("method", method);
  document.getElementById("flexUrlTableForm" + formName).submit();
}
// $(document).ready(function () {
//     $('#modalSanPham').on('show.bs.modal', function (event) {
//         var button = $(event.relatedTarget);
//         var hoaDonId = button.data('id');
//         var modal = $(this);

//         $.ajax({
//             url: '/banHang/ThemSanPham',
//             type: 'GET',
//             success: function (data) {
//                 modal.find('.modal-body').html(data);
//             },
//             error: function () {
//             }
//         });
//     });
// });

// $(document).ready(function () {
//     $('#modalSanPham').on('show.bs.modal', function (event) {
//         var button = $(event.relatedTarget);
//         var hoaDonId = button.data('id');
//         var modal = $(this);
//
//         $.ajax({
//             url: '/banHang/ThemSanPham',
//             type: 'GET',
//             success: function (data) {
//                 modal.find('.modal-body').html(data);
//             },
//             error: function () {
//             }
//         });
//     });
// });
