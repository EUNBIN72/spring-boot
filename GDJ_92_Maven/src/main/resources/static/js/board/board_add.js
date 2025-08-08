const add = document.getElementById("add");
const result = document.getElementById("result");
const del = document.querySelectorAll(".del");
const deleteFile = document.querySelectorAll(".deleteFile");


let count = result.getAttribute("data-file-count");

//-----------------------
deleteFile.forEach( (item)=>{
	item.addEventListener("click", function() {

		let flag = confirm("정말 삭제하시겠습니까?")
		if(!flag) {
			return;
		}
		// fetch, axios
		let params = new URLSearchParams();
		params.append("fileNum", item.getAttribute("data-file-num"))
		fetch(`./fileDelete`, {
			method:"POST",
			body:params 
		})	
		.then(r=>r.text())
		.then(r=>{
			if(r.trim()==1){
				count--;
				item.remove();
			} else {
				alert('삭제 실패')
			}
		})
	})
})

add.addEventListener("click", () => {

  if (count > 4) {
	alert("최대 5개까지 가능");
    // 5개 초과면 아무 동작 안함
    return;
  }

  let div = document.createElement("div");
  div.classList.add("mb-3");

  let ch = document.createElement("input");
  ch.setAttribute("type", "file");
  ch.classList.add("form-control");
  ch.setAttribute("name", "attaches");

  div.append(ch);

  ch = document.createElement("button");
  ch.classList.add("del");
  ch.setAttribute("type", "button");
  ch.innerText = "X";

  div.append(ch);

  result.append(div);
  
  count++;
});

result.addEventListener("click", (e) => {
  if (e.target.classList.contains("del")) {
    e.target.parentElement.remove();
	count--;
  }
});
