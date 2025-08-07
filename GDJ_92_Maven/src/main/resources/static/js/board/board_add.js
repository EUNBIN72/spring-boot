const add = document.getElementById("add");
const result = document.getElementById("result");
const del = document.querySelectorAll(".del");

let count = result.getAttribute("data-file-count");

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
