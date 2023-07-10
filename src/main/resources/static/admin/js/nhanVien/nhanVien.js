<!-- Xử lý phân trang -->
function changePage(event, pageNumber) {
    event.preventDefault();
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/admin/NguoiDung?page=" + pageNumber);
    xhr.onload = function () {
        if (xhr.status === 800) {
            // Cập nhật nội dung trang mới
            var newPageContent = xhr.responseText;
            document.getElementById("table-container").innerHTML = newPageContent;
        }
    };
    xhr.send();
}

<!-- Lọc Trạng Thái -->
function filterByStatus() {
    var selectedStatus = document.getElementById("status").value.toLowerCase();
    var rows = document.querySelectorAll("#user-name-label tbody tr");

    rows.forEach(function (row) {
        var statusCell = row.querySelector("td:nth-child(6) input[type='checkbox']");
        var status = statusCell.checked ? "active" : "inactive";
        var displayRow = false;

        if (selectedStatus === "all" || selectedStatus === status) {
            displayRow = true;
        }
        if (displayRow) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}

<!--Tìm kiếm mã nv,tên,eamil,sđt -->
function searchByCustomerNameAndPhoneAllHD() {
    var input = document.getElementById("search-input-allHD-name").value.toLowerCase();
    var table = document.getElementById("user-name-label");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var idCell = rows[i].getElementsByTagName("td")[0];
        var customerNameCell = rows[i].getElementsByTagName("td")[1];
        var emailCell = rows[i].getElementsByTagName("td")[2];
        var phoneCell = rows[i].getElementsByTagName("td")[3];

        if (idCell && customerNameCell && emailCell && phoneCell) {
            var idCell = idCell.textContent || idCell.innerText;
            var customerName = customerNameCell.textContent || customerNameCell.innerText;
            var emailCell = emailCell.textContent || emailCell.innerText;
            var phoneNumber = phoneCell.textContent || phoneCell.innerText;
            idCell = idCell.toLowerCase();
            customerName = customerName.toLowerCase();
            emailCell = emailCell.toLowerCase();
            phoneNumber = phoneNumber.toLowerCase();

            if (
                (idCell.indexOf(input) > -1 ||
                    customerName.indexOf(input) > -1 ||
                    phoneNumber.indexOf(input) > -1 ||
                    emailCell.toString().indexOf(input) > -1)
            ) {
                rows[i].style.display = "";

                var searchLabel = input;
                if (idCell.indexOf(input) > -1) {
                    searchLabel += " (Mã Nhân Viên)";
                }
                if (customerName.indexOf(input) > -1) {
                    searchLabel += " (Tên Nhân Viên)";
                }
                if (phoneNumber.indexOf(input) > -1) {
                    searchLabel += " (Số Điện Thoại)";
                }

                if (emailCell.indexOf(input) > -1) {
                    searchLabel += " (Email)";
                }
                document.getElementById("search-input-allHD-name").value = searchLabel;
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}

<!-- Tìm kiếm ngày tạo -->
function searchByCreationDate() {
    var inputDate = document.getElementById("search-input-allHD-date").value;
    var table = document.getElementById("user-name-label");
    var rows = table.getElementsByTagName("tr");
    var found = false;

    for (var i = 0; i < rows.length; i++) {
        var creationDateCell = rows[i].getElementsByTagName("td")[4]; // Điều chỉnh chỉ mục nếu cần thiết

        if (creationDateCell) {
            var creationDate = creationDateCell.innerText;
            var formattedCreationDate = formatDate(creationDate);

            if (formattedCreationDate === inputDate) {
                rows[i].style.display = "";
                found = true;
            } else {
                rows[i].style.display = "none";
            }
        }
    }
    if (found) {
        Swal.fire({
            icon: 'success',
            title: 'Tìm kiếm thành công!',
            showConfirmButton: false,
            timer: 1500
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Không tìm thấy',
            showConfirmButton: false,
            timer: 1500
        });
    }
}
function formatDate(dateString) {
    var parts = dateString.split("/");
    var day = parts[0];
    var month = parts[1];
    var year = parts[2];
    return year + "-" + month + "-" + day;
}

<!-- Nút bật/tắt trạng thái -->
$(document).ready(function () {
    $('.custom-control-input').click(function () {
        var userId = $(this).closest('tr').data('user-id');
        var statusCheckbox = $(this);
        var status = statusCheckbox.is(':checked') ? 0 : 1;

        $.post("/updateStatus", {userId: userId, status: status}, function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Cập nhật thành công',
                showConfirmButton: false,
                timer: 1500
            });
        }).fail(function (error) {
            Swal.fire({
                icon: 'error',
                title: 'Có lỗi xảy ra',
                text: error.responseText
            });
        });
    });
});
