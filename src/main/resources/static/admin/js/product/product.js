// product manage
VirtualSelect.init({
  ele: "select",
});

function showConfirmModalDeleteDialog(id, name) {
  $("#productName").text(name);
  $("#yesOptionDeleteModalId").attr("href", "/admin/product/delete/" + id);
  $("#deleteModalId").modal("show");
}

function changeIsShowProductDetail(url, idHTML, idItem, isCheck) {
  $("#yesOptionChangeIsShow").attr(
    "href",
    "/admin/product/" + url + "/" + idItem + "/" + !isCheck
  );
  $("#isShowModalId").modal("show");
  $("#notChangeIsShow").attr("onclick", notChangeIsShow(idHTML, isCheck));
}

function notChangeIsShow(idHTML, isCheck) {
  document.getElementById(idHTML).checked = isCheck;
}

function showConfirmModalDialogDeleteAllbyId() {
  $("#confirmationDeleteIds").modal("show");
}

function flexUrlSubmit(url, method, formName) {
  $("#flexUrlTableForm" + formName).attr("action", "/admin/product/" + url);
  $("#flexUrlTableForm" + formName).attr("method", method);
  document.getElementById("flexUrlTableForm" + formName).submit();
}

function toggleProductIds(source) {
  checkboxes = document.getElementsByName("productIds");
  for (var i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
  }
}

// function openBoxCreateImgs(id) {
//   if (document.getElementById(id).isCheck === true) {
//   }
// }
function showInfoProductDetailById(
  nguoiTao,
  ngayTao,
  nguoiCapNhat,
  NgayCapNhat,
  moTa
) {
  $("#infoProductDetail").modal("show");
  document.getElementById("infoProductDetailContentString0").innerHTML =
    nguoiTao;
  document.getElementById("infoProductDetailContentString1").innerHTML =
    ngayTao;
  document.getElementById("infoProductDetailContentString2").innerHTML =
    nguoiCapNhat;
  document.getElementById("infoProductDetailContentString3").innerHTML =
    NgayCapNhat;
  document.getElementById("infoProductDetailContentString4").innerHTML = moTa;
}

function openPopupIsShowSpeedAddProduct(tenThuocTinh, tenField) {
  document.getElementById("tenThuocTinh").innerHTML = tenThuocTinh;
  document.getElementById("fieldthuocTinhInput").value = tenField;
  $("#iShowSpeedModalId").modal("show");
}

$("#multiple-select-field").select2({
  theme: "bootstrap-5",
  width: $(this).data("width")
    ? $(this).data("width")
    : $(this).hasClass("w-100")
    ? "100%"
    : "style",
  placeholder: $(this).data("placeholder"),
  closeOnSelect: false,
});

function openPopupChangeIsShowFormAddProduct() {
  $("#isShowModalId").modal("show");
}

function changeIsShowFormAddProduct() {
  flexUrlSubmit("changeIsShowFormAddProduct", "post", "AddProduct");
}
