$(document).ready(function () {
    $('.HuyDon').click(function () {
        var hoaDonId = $(this).data('id');

        $('.huyModal').modal('show');

        $('.huyModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/customer/updateHuyDon/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'error', title: 'Đã hủy thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    location.reload();
                });
            });

            $('.huyModal').modal('hide');
        });

        $('.huyModal .btn-khong').click(function () {
            $('.huyModal').modal('hide');
        });
    });
});
