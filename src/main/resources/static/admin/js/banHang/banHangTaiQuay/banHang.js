function quayLai() {
    window.location.href = "/admin/BanHangTaiQuay";
}

$(document).ready(function () {
    $('#modalSanPham').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var hoaDonId = button.data('id');
        var modal = $(this);

        $.ajax({
            url: '/banHang/ThemSanPham',
            type: 'GET',
            success: function (data) {
                modal.find('.modal-body').html(data);
            },
            error: function () {
            }
        });
    });
});

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