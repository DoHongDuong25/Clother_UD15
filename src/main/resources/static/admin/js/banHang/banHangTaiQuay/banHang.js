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

$(document).ready(function () {
    $('.btn-primary').click(function () {
        var sanPhamId = $(this).attr('data-id');
        console.log(sanPhamId);
        var modal = $('#modalChiTiet');

        $.ajax({
            url: '/' + sanPhamId + '/mausac-kichco',
            type: 'GET',
            success: function (data) {
                modal.find('.modal-body-spct').html(data);
                modal.modal('show');
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