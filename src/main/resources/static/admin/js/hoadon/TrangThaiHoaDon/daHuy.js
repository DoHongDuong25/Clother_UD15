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


<!-- JS TÌM HÓA ĐƠN ĐÃ HỦY-->
function searchByIdDaHuy() {
    var input = document.getElementById("search-input-daHuy").value.trim().toLowerCase();
    var table = document.getElementById("user-table-DaHuy");
    var rows = table.getElementsByTagName("tr");

    var found = false; // Biến kiểm tra xem đã tìm thấy kết quả hay chưa

    for (var i = 0; i < rows.length; i++) {
        var orderIdCell = rows[i].getElementsByTagName("th")[0];
        var customerNameCell = rows[i].getElementsByTagName("td")[0];
        var phoneNumberCell = rows[i].getElementsByTagName("td")[1];
        var totalPriceCell = rows[i].getElementsByTagName("td")[3];

        if (orderIdCell && customerNameCell && phoneNumberCell && totalPriceCell) {
            var orderId = orderIdCell.textContent.trim().toLowerCase();
            var customerName = customerNameCell.textContent.trim().toLowerCase();
            var phoneNumber = phoneNumberCell.textContent.trim().toLowerCase();
            var totalPrice = parseFloat(totalPriceCell.textContent.replace(/[^0-9.]/g, ''));

            if (
                orderId.indexOf(input) > -1 ||
                customerName.indexOf(input) > -1 ||
                phoneNumber.indexOf(input) > -1 ||
                totalPrice.toString().indexOf(input) > -1
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

function searchByCreationDateDaHuy() {
    var inputDate = document.getElementById("search-input-date-DaHuy").value;
    var table = document.getElementById("user-table-DaHuy");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var creationDateCell = rows[i].getElementsByTagName("td")[2]; // Điều chỉnh chỉ mục nếu cần thiết

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
