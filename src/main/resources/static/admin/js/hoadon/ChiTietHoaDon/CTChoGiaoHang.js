<!-- JS CHỜ GIAO HÀNG -> ĐANG GIAO HÀNG -->
$(document).ready(function () {
    $('.GiaoHang').click(function () {
        var hoaDonId = $(this).data('id');

        // Lưu trạng thái tab hiện tại vào sessionStorage
        var activeTab = $('.nav-link.active').attr('href');
        sessionStorage.setItem('activeTab', activeTab);

        // Hiển thị modal xác nhận
        $('.giaoHangModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/updateGiaoHang/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Đã chuyển hàng cho đơn vị vận chuyển thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/ChoGiaoHang";
                });
            });

            // Đóng modal
            $('.giaoHangModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangModal').modal('hide');
        });
    });
});

function quayLai() {
    window.location.href = "/admin/DonHang/ChoGiaoHang";
}
