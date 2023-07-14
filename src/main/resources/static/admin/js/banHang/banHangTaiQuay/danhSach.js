document.getElementById('form-tao-moi').addEventListener('submit', function (event) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit

    // Gửi request tạo hóa đơn
    fetch(this.action, {
        method: this.method,
        body: new FormData(this)
    }).then(function (response) {
        if (response.ok) {
            // Chuyển hướng tới trang hiển thị thông tin hóa đơn
            window.location.href = '/banHang/' + response.id;
        } else {
            console.error('Lỗi khi tạo hóa đơn');
        }
    }).catch(function (error) {
        console.error('Lỗi khi tạo hóa đơn:', error);
    });
});