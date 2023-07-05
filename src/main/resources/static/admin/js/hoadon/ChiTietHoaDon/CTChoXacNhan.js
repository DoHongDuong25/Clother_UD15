<!-- JS XÁC CHỜ XÁC NHẬN -> CHỜ GIAO HÀNG-->
$(document).ready(function () {
    $('.XacNhanDon').click(function () {
        var hoaDonId = $(this).data('id');

        $('.xacNhanModal').modal('show');

        $('.xacNhanModal .btn-dong-y').click(function () {
            $.get('/updateXacNhan/' + hoaDonId, function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Đã xác nhận thành công',
                    showConfirmButton: false,
                    timer: 1500
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/ChoXacNhanDonHang";
                });
            });

            $('.xacNhanModal').modal('hide');
        });

        $('.xacNhanModal .btn-khong').click(function () {
            $('.xacNhanModal').modal('hide');
        });
    });
});

<!-- JS CHỜ CHỜ XÁC NHẬN -> ĐÃ HỦY -->
$(document).ready(function () {
    $('.HuyDon').click(function () {
        var hoaDonId = $(this).data('id');

        $('.huyModal').modal('show');

        $('.huyModal .btn-dong-y').click(function () {
            $.get('/updateHuyDon/' + hoaDonId, function (response) {
                Swal.fire({
                    icon: 'error', title: 'Đã hủy thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/ChoXacNhanDonHang";
                });
            });

            // Đóng modal
            $('.huyModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.huyModal .btn-khong').click(function () {
            // Đóng modal
            $('.huyModal').modal('hide');
        });
    });
});

function quayLai() {
    window.location.href = "/admin/DonHang/ChoXacNhanDonHang";
}
