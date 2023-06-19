function myFunction(){
    alert("pro")
}
function submitForm(pageNum, pageSize) {
    var page = pageNum || 1;
    var size = pageSize || 10;
    var keyword = document.getElementById('search').value;
    var status = document.getElementById('status').value;
    var date = document.getElementById('time').value;
    var url = '/admin/voucher?page=' + encodeURIComponent(page) + '&size=' + encodeURIComponent(size) + '&keyword=' + encodeURIComponent(keyword) + '&status=' + encodeURIComponent(status) + '&date=' + encodeURIComponent(date);
    var link = document.createElement('a');
    link.href = url;
    link.click();
}
