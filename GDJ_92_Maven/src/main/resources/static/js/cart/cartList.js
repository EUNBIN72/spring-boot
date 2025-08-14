/**
 * 
 */

// 장바구니 페이지
// 전체 체크박스를 클릭했을 때
 const checkAll = document.getElementById("checkAll");
 const ch = document.querySelectorAll(".ch")
 const frm = document.getElementById("frm")
 const del = document.getElementById("del")
 
 del.addEventListener("click", () => {
	frm.submit();
 })
 
 
 checkAll.addEventListener("click", () => {
     ch.forEach((item) => {
         item.checked = checkAll.checked;
     });
 });

 ch.forEach((item) => {
     item.addEventListener("click", () => {
         checkAll.checked = document.querySelectorAll(".ch:checked").length === ch.length;
     });
 });
