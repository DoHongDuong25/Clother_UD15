function quayLai() {
    window.location.href = "/admin/BanHangTaiQuay";
}
// Lấy tham chiếu đến modal và nút "Thêm sản phẩm"
const modal = document.getElementById("modal");
const modalContent = document.getElementById("modal-content");
const btnThemSanPham = document.getElementById("btn-them-san-pham");

// Xử lý sự kiện click trên nút "Thêm sản phẩm"
btnThemSanPham.addEventListener("click", function () {
    // Sử dụng fetch để tải nội dung từ file "modal.html"
    fetch("path/to/modal.html")
        .then(function (response) {
            return response.text();
        })
        .then(function (html) {
            // Gán nội dung tải được vào phần content của modal
            modalContent.innerHTML = html;

            // Hiển thị modal
            modal.style.display = "block";
        });
});