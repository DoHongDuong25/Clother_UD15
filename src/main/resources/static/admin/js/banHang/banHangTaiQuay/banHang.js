function quayLai() {
    window.location.href = "/admin/BanHangTaiQuay";
}

// File: banHang.js
// Lấy tham chiếu đến modal và phần body của modal
const modal = document.getElementById("modalThemSanPhanm");
const modalBody = document.getElementById("modal-body");

// Hàm xử lý sự kiện click để hiển thị modal và tải nội dung từ file HTML khác
function showModal() {
    // Sử dụng fetch để tải nội dung từ file HTML khác
    fetch("../../modal.html")
        .then(function (response) {
            return response.text();
        })
        .then(function (html) {
            // Gán nội dung tải được vào phần body của modal
            modalBody.innerHTML = html;

            // Hiển thị modal
            $(modal).modal("show");
        });
}