function redirectToTatCaDon() {
    window.location.href = "/admin/DonHang/TatCaDon";
}

function redirectToChoxacNhan() {
    window.location.href = "/admin/DonHang/ChoXacNhanDonHang";
}

function redirectToChoGiaoHang() {
    window.location.href = "/admin/DonHang/ChoGiaoHang";
}

function redirectToDangGiao() {
    window.location.href = "/admin/DonHang/DangGiaoHang";
}

function redirectToDaGiao() {
    window.location.href = "/admin/DonHang/DaGiaoHang";
}

function redirectToDahuy() {
    window.location.href = "/admin/DonHang/DaHuyHang";
}

<!-- JS CHỜ GIOA HÀNG -> ĐANG GIAO HÀNG -->
$(document).ready(function () {
    $('.GiaoHang').click(function () {
        var hoaDonId = $(this).data('id');

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

                    // Tải lại trang
                    location.reload();
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

<!-- JS GIAO TẤT CẢ-->
$(document).ready(function () {
    $('#GiaoTatCa').click(function () {
        // Kiểm tra số lượng hàng trong bảng
        var rowCount = $('#user-table-ChoLayHang tbody tr').length;

        // Kiểm tra nếu không có dữ liệu
        if (rowCount === 0) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi',
                text: 'Không có dữ liệu',
                showConfirmButton: false,
                timer: 2000
            });
            return; // Dừng thực hiện các lệnh tiếp theo
        }

        var hoaDonId = $(this).data('id');

        // Lưu trạng thái tab hiện tại vào sessionStorage
        var activeTab = $('.nav-link.active').attr('href');
        sessionStorage.setItem('activeTab', activeTab);

        // Hiển thị modal xác nhận
        $('.giaoHangTatCaModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangTatCaModal .btn-dong-y').click(function () {
            // Gửi yêu cầu giao hàng tất cả bằng Ajax
            $.get('/updateGiaoHangAll', function (response) {
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận chuyển tất cả đơn cho đơn vị vận chuyển',
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
            $('.giaoHangTatCaModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangTatCaModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangTatCaModal').modal('hide');
        });
    });
});

<!-- JS TÌM HÓA ĐƠN CHỜ LẤY HÀNG-->
function searchByIdChoLayHang() {
    var input = document.getElementById("search-input-choLayHang").value.trim().toLowerCase();
    var table = document.getElementById("user-table-ChoLayHang");
    var rows = table.getElementsByTagName("tr");

    var found = false; // Biến kiểm tra xem đã tìm thấy kết quả hay chưa

    for (var i = 0; i < rows.length; i++) {
        var orderIdCell = rows[i].getElementsByTagName("th")[0];
        var customerNameCell = rows[i].getElementsByTagName("td")[1];
        var phoneNumberCell = rows[i].getElementsByTagName("td")[2];
        var totalPriceCell = rows[i].getElementsByTagName("td")[4];
        var noteCell = rows[i].getElementsByTagName("td")[5];

        if (orderIdCell && customerNameCell && phoneNumberCell && totalPriceCell && noteCell) {
            var orderId = orderIdCell.textContent.trim().toLowerCase();
            var customerName = customerNameCell.textContent.trim().toLowerCase();
            var phoneNumber = phoneNumberCell.textContent.trim().toLowerCase();
            var totalPrice = parseFloat(totalPriceCell.textContent.replace(/[^0-9.]/g, ''));
            var note = noteCell.textContent.trim().toLowerCase();

            if (
                orderId.indexOf(input) > -1 ||
                customerName.indexOf(input) > -1 ||
                phoneNumber.indexOf(input) > -1 ||
                totalPrice.toString().indexOf(input) > -1 ||
                note.indexOf(input) > -1
            ) {
                rows[i].style.display = "";
                found = true; // Đánh dấu đã tìm thấy kết quả
            } else {
                rows[i].style.display = "none";
            }
        }
    }

    if (!found) {
        Array.from(rows).forEach(function (row) {
            row.style.display = ""; // Hiển thị lại tất cả các dòng
        });

        Swal.fire({
            icon: 'warning',
            title: 'Không tìm thấy',
            showConfirmButton: false,
            timer: 1500
        });
    }
}

<!--TÌM KIẾM THEO NGÀY-->
function searchByCreationDateChoGiaoHang() {
    var inputDate = document.getElementById("search-input-date-choGiaoHang").value;
    var table = document.getElementById("user-table-ChoLayHang");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var creationDateCell = rows[i].getElementsByTagName("td")[3]; // Điều chỉnh chỉ mục nếu cần thiết

        if (creationDateCell) {
            var creationDate = creationDateCell.innerText;
            var formattedCreationDate = formatDate(creationDate);

            if (formattedCreationDate === inputDate) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}

function formatDate(dateString) {
    var parts = dateString.split("/");
    var day = parts[0];
    var month = parts[1];
    var year = parts[2];
    return year + "-" + month + "-" + day;
}
