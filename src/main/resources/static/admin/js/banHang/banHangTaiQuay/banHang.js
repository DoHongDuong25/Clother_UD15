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
        let modalId = $(this).data('target');

        // Hiển thị modal xác nhận
        $(modalId).modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $(modalId + ' .btn-dong-y').click(function () {
            // Gửi yêu cầu xóa sản phẩm bằng Ajax
            $.get('/update-XoaSP/' + hoaDonCTId, function (response) {
                // Hiển thị thông báo xóa sản phẩm thành công với SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Đã xóa sản phẩm thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    location.reload();
                });
            });

            // Đóng modal
            $(modalId).modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $(modalId + ' .btn-khong').click(function () {
            // Đóng modal
            $(modalId).modal('hide');
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
    $('.thanhToanBanTaiQuay').click(function () {
        let hoaDonid = $(this).data('id');

        $('.thanhToanTaiQuay').modal('show');

        $('.thanhToanTaiQuay .btn-dong-y').click(function () {
            $.get('/thanhToan/' + hoaDonid, function (response) {
                swal.fire({
                    icon: 'success',
                    title: 'Đã thanh toán hóa đơn ' + hoaDonid + ' thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/BanHangTaiQuay";
                });
            });

            $('.thanhToanTaiQuay .btn-khong').modal('hide');
        });

        $('.thanhToanTaiQuay .btn-khong').click(function () {
            $('.thanhToanTaiQuay').modal('hide');
        });
    });
})

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

function openModalSanPham() {
    $('#modalSanPham').modal('show');
}

$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});

function openAddProduct() {
    $('#banHangTaiQuayModal').modal("show");
}

function openModalAddDetailProduct(id) {
    $('#modalAddDetailProduct' + id).modal("show");
}

function clearDataChoose(mauSacInputName, kichCoInputName, mauSacLabelName, kichCoLabelName) {
    var kichCoIdName = document.getElementsByName("kichCoId");
    $(kichCoIdName).removeAttr("checked");
    var mauSacIdName = document.getElementsByName("mauSacId");
    $(mauSacIdName).removeAttr("checked");
    var kichCoLabel = document.getElementsByName(kichCoLabelName);
    $(kichCoLabel).removeClass("label-active");

    resetModalData();
}

function labelActive(labelId, labelName, inputName, inputId) {
    var lstName = document.getElementsByName(labelName);
    $(lstName).removeClass("label-active");
    $('#' + labelId).addClass("label-active");
    var lstInputName = document.getElementsByName(inputName);
    $(lstInputName).removeAttr("checked");
    $('#' + inputId).attr("checked", "true");
}

function chooseOptionColorLabel(labelId, labelName, inputName, inputId, spName, spId) {
    var lstName = document.getElementsByName(labelName);
    $(lstName).removeClass("label-active");
    $('#' + labelId).addClass("label-active");
    var lstInputName = document.getElementsByName(inputName);
    $(lstInputName).removeAttr("checked");
    $('#' + inputId).attr("checked", "true");
    // choose san Pham id
    var lstSPName = document.getElementsByName(spName);
    $(lstInputName).removeAttr("checked");
    $('#' + spId).attr("checked", "true");
}

function getSoLuongInput(id, name) {
    var valueSoLuongName = document.getElementsByName(id);
    $(valueSoLuongName).val(0);
    var valueSoLuongId = document.getElementById(id).value;
    var soLuong = document.getElementsByName('soLuong');
    $(soLuong).val(valueSoLuongId);
}


window.onload = function () {
    var messageSuccess = '[[${messageSuccess}]]';
    var messageDanger = '[[${messageDanger}]]';
    $('#toastsCustomCss').attr("style", "position: absolute; top: 70px; right: 0;z-index: 1;");
    if (messageSuccess.length !== 0) {
        $("#messageSuccess").toast("show");
    }
    if (messageDanger.length !== 0) {
        $("#messageDanger").toast("show");
    }
}
$(document).ready(function () {
    $('.img-thumbnail').click(function () {
        var itemSanPhamId = $(this).closest('.modal-content').find('.modal-title').data('id');
        var tenKichCo = $(this).text().trim();

        console.log(itemSanPhamId);
        console.log(tenKichCo);

        // Gửi yêu cầu Ajax đến controller để lấy số lượng sản phẩm chi tiết dựa trên sản phẩm ID và tên kích cỡ
        $.ajax({
            type: 'GET', url: '/banHang/laySoLuongSanPhamChiTiet', data: {
                sanPhamId: itemSanPhamId, tenKichCo: tenKichCo
            }, success: function (response) {
                var soLuongSanPhamChiTiet = response.soLuongSanPhamChiTiet;
                console.log(soLuongSanPhamChiTiet);

                // Hiển thị số lượng sản phẩm chi tiết
                $('#soLuongHienCo' + itemSanPhamId).text(soLuongSanPhamChiTiet);
            }, error: function () {
                // Xử lý khi có lỗi xảy ra trong yêu cầu Ajax
                alert('Đã xảy ra lỗi khi gửi yêu cầu đến server.');
            }
        });
    });
});









