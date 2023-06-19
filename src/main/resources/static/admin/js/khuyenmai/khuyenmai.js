
function search(pageNum) {
    var page = pageNum || 1;
    var size = document.getElementById('size').value || 10;
    var keyword = document.getElementById('search').value;
    var status = document.getElementById('status').value || "ALL";
    var date = document.getElementById('time').value || "ALL";
    var url = '/admin/voucher?page=' + encodeURIComponent(page) + '&size=' + encodeURIComponent(size) + '&keyword=' + encodeURIComponent(keyword) + '&status=' + encodeURIComponent(status) + '&date=' + encodeURIComponent(date);
    var link = document.createElement('a');
    link.href = url;
    link.click();
}
