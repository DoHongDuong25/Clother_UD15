$(document).ready(function () {
    $('.DaGiao').click(function () {
        var hoaDonId = $(this).data('id');

        $('.giaoHangTCModal').modal('show');

        $('.giaoHangTCModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/customer/updateGiaoHangThanhCong/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đơn hàng đã giao thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    location.reload();
                });
            });

            $('.giaoHangTCModal').modal('hide');
        });

        $('.giaoHangTCModal .btn-khong').click(function () {
            $('.giaoHangTCModal').modal('hide');
        });
    });
});

<!-- JS HOÀN THÀNH TẤT CẢ-->
$(document).ready(function () {
    $('#HoanThanhTatCa').click(function () {
        var rowCount = $('#user-table-DangGiaoCustomer tbody tr').length;

        if (rowCount === 0) {
            Swal.fire({
                icon: 'error', title: 'Lỗi', text: 'Không có dữ liệu', showConfirmButton: false, timer: 2000
            });
            return;
        }

        $('.hoanThanhTatCaModal').modal('show');

        $('.hoanThanhTatCaModal .btn-dong-y').click(function () {
            // Gửi yêu cầu hoàn thành tất cả bằng Ajax
            $.get('/customer/updateThanhCongAll', function (response) {
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đã giao thành công tất cả các đơn',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    location.reload();
                });
            });

            $('.hoanThanhTatCaModal').modal('hide');
        });

        $('.hoanThanhTatCaModal .btn-khong').click(function () {
            $('.hoanThanhTatCaModal').modal('hide');
        });
    });
});