function quayLai() {
    window.location.href = "/admin/BanHangTaiQuay";
}

function flexUrlSubmitBanHangTaiQuay(url, method, formName) {
    $("#flexUrlTableForm" + formName).attr("action", url);
    $("#flexUrlTableForm" + formName).attr("method", method);
    document.getElementById("flexUrlTableForm" + formName).submit();
}

$(document).ready(function () {
    $('.btn-primary').click(function () {
        var sanPhamId = $(this).attr('data-id');
        console.log(sanPhamId);
        var modal = $('#modalChiTiet');

        $.ajax({
            url: '/' + sanPhamId + '/mausac-kichco', type: 'GET', success: function (data) {
                modal.find('.modal-body-spct').html(data);
                modal.modal('show');
            }, error: function () {
            }
        });
    });
});

$(document).ready(function () {
    $('.xoaHDCT').click(function () {
        let hoaDonCTId = $(this).data('id');

        $('.XoaHDCT').modal('show');

        $('.XoaHDCT .btn-dong-y').click(function () {
            $.get('/update-XoaSP/' + hoaDonCTId, function (response) {
                Swal.fire({
                    icon: 'success', title: 'Đã xóa sản phẩm thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    location.reload();
                });
            });

            $('.XoaHDCT').modal('hide');
        });

        $('.XoaHDCT .btn-khong').click(function () {
            // Đóng modal
            $('.XoaHDCT').modal('hide');
        });
    });
});

$(document).ready(function () {
    $('.HuyDonBanTaiQuay').click(function () {
        let hoaDonId = $(this).data('id');

        $('.HuyDonTaiQuay').modal('show');

        $('.HuyDonTaiQuay .btn-dong-y').click(function () {
            $.get('/HuyDon/' + hoaDonId, function (response) {
                Swal.fire({
                    icon: 'success', title: 'Đã hủy hóa đơn thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/BanHangTaiQuay";
                });
            });

            $('.HuyDonTaiQuay').modal('hide');
        });

        $('.HuyDonTaiQuay .btn-khong').click(function () {
            // Đóng modal
            $('.HuyDonTaiQuay').modal('hide');
        });
    });
});

$(document).ready(function () {
    // Xử lý sự kiện khi trang được tải lần đầu
    $('.quantity-input').each(function () {
        $(this).data('initial-value', $(this).val());
    });

    // Xử lý sự kiện khi nhấn nút tăng số lượng
    $('.increment-btn').on('click', function () {
        var hoaDonCTId = $(this).attr('data-id');
        var quantityInput = $(this).siblings('.quantity-input');
        var saveBtn = $(this).siblings('.save-btn');
        var cancelBtn = $(this).siblings('.cancel-btn');

        var quantity = parseInt(quantityInput.val());
        quantity++;
        quantityInput.val(quantity);
        saveBtn.show();
        cancelBtn.show();
    });

    // Xử lý sự kiện khi nhấn nút giảm số lượng
    $('.decrement-btn').on('click', function () {
        var hoaDonCTId = $(this).attr('data-id');
        var quantityInput = $(this).siblings('.quantity-input');
        var saveBtn = $(this).siblings('.save-btn');
        var cancelBtn = $(this).siblings('.cancel-btn');

        var quantity = parseInt(quantityInput.val());
        if (quantity > 1) {
            quantity--;
            quantityInput.val(quantity);
            saveBtn.show();
            cancelBtn.show();
        }
    });

    // Xử lý sự kiện khi thay đổi số lượng trong input
    $('.quantity-input').on('input', function () {
        var saveBtn = $(this).siblings('.save-btn');
        var cancelBtn = $(this).siblings('.cancel-btn');
        saveBtn.show();
        cancelBtn.show();
    });

    // Xử lý sự kiện khi nhấn nút lưu
    $('.save-btn').on('click', function () {
        var hoaDonCTId = $(this).attr('data-id');
        var quantityInput = $(this).siblings('.quantity-input');
        var quantity = parseInt(quantityInput.val());

        // Gửi Ajax request để lưu thay đổi số lượng
        $.ajax({
            url: '/update-SoLuong/' + hoaDonCTId,
            type: 'POST',
            data: {quantity: quantity},
            success: function (response) {
                // Xử lý thành công
                Swal.fire({
                    icon: 'success', title: 'Cập nhật số lượng thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);
                    location.reload();
                });
            },
            error: function (error) {
                // Xử lý lỗi
                console.log('Lỗi khi lưu số lượng');
            }
        });
    });

    // Xử lý sự kiện khi nhấn nút hủy
    $('.cancel-btn').on('click', function () {
        var hoaDonCTId = $(this).attr('data-id');
        var quantityInput = $(this).siblings('.quantity-input');
        var saveBtn = $(this).siblings('.save-btn');
        var cancelBtn = $(this);

        // Trả lại giá trị ban đầu của ô input
        var initialValue = quantityInput.data('initial-value');
        quantityInput.val(initialValue);
        saveBtn.hide();
        cancelBtn.hide();
    });
});



