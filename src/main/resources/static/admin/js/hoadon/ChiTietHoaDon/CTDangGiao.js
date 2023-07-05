<!-- JS ĐANG GIAO -> ĐÃ GIAO -->
$(document).ready(function () {
    $('.DaGiao').click(function () {
        var hoaDonId = $(this).data('id');

        // Lưu trạng thái tab hiện tại vào sessionStorage
        var activeTab = $('.nav-link.active').attr('href');
        sessionStorage.setItem('activeTab', activeTab);

        // Hiển thị modal xác nhận
        $('.giaoHangTCModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangTCModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/updateGiaoHangThanhCong/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đơn hàng đã giao thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/DangGiaoHang";
                });
            });

            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangTCModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });
    });
});

function quayLai() {
    window.location.href = "/admin/DonHang/DangGiaoHang";
}
