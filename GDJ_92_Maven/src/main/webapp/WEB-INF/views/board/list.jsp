<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>
<body id="page-top">


	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		
		<!-- Start -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<!-- page contents 내용 -->
					<!-- 메인 페이지 사이드바에서 BOARD에서 Notice를 클릭했을 때 나와야하는 페이지 구성 -->
					<!-- NOTICE(공지사항)의 목록 리스트가 나왔으면 좋겠잖아 -->
					<!-- 그럼 리스트 어떻게 보여줄거야 -->
					<div class="col-md-8 offset-md-2">
					
					<!-- notice 페이지인지 qna 페이지인지 알려주기 -->
					<!-- notice랑 qna는 보여지는 페이지가 거의 비슷하기 때문에 board로 통일해서 같이 사용해줌 -->
					<!-- notice 페이지에 들어가면 notice, qna 페이지에 들어가면 qna가 출력되게 -->
					<h2>${board }</h2>
					
					<div class="row">
						<form id="searchForm">
							<div class="input-group mb-3">
							<input type="hidden" id="pageNum" name="pageNum">
								<select class="form-control" name="kind" aria-label="Default select example">
								  <option value="k1" ${pager.kind eq 'k1' ? 'selected' : ''}>Title</option>
								  <option value="k2" ${pager.kind eq 'k2' ? 'selected' : ''}>Contents</option>
								  <option value="k3" ${pager.kind eq 'k3' ? 'selected' : ''}>Writer</option>
								</select>
								  <input type="text" class="form-control" value="${pager.keyword }" name="keyword" placeholder="Recipient’s username" aria-label="Recipient’s username" aria-describedby="button-addon2">
								  <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Button</button>
							</div>
						</form>
					</div>
					
					<!-- 테이블 형태로 보여줘야겠지 -> 그럼 테이블 만들어 -->
						<table class="table table-striped">
						<!-- 첫 번째 행 만들어서 각 열의 제목들 하드코딩 -->
							<thead>
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Date</th>
									<th>Hit</th>
								</tr>
							</thead>
							<tbody>
								<!-- 각 행에서 반복 출력해야 될 것들 어떻게 출력할래 -->
								<!-- JSTL(JSP Standard Tag Library)의 반복문 태그 -->
								<!-- 배열, 리스트, 맵 등 여러 개의 값을 하나씩 꺼내 쓸 때 사용(자바의 for-each문과 비슷한 역할) -->
								
								<!-- ${list }는 
									NoticeController의 model.addAttribute("list", list);에서 "list"랑 같은거임 -->
								<!-- list의 각 요소를 반복문이 돌 때마다 l 변수에 담아서 출력할거니까
									출력해줄 때 l.뭐시기 를 써주면서 출력해야지 -->	
									
								<c:forEach var="l" items="${list }">
									<tr>
										<td>${l.boardNum }</td>
										<td>
											<!-- c:catch 를 쓰는 이유는 답글이라는 기능은 notice 페이지에는 없고 qna 페이지에만 존재함 -->
											<!-- qna list에서만 답글 기능이 나왔으면 좋겠음 -->
											<!-- c:catch로 감싸주면 에러가 발생해도 JSP가 죽지 않고, 예외를 무시하거나, 에러 변수로 받아 처리할 수 있음 -->
											<c:catch>
											<!-- begin이 0부터 시작하는 이유는 depth는 1이상이여야 들여쓰기를 하기 때문 -->
											<c:forEach begin="1" end="${l.boardDepth }">⇨&nbsp;&nbsp;&nbsp;</c:forEach>
											</c:catch>
											<a href="./detail?boardNum=${l.boardNum }">${l.boardTitle }</a>
										</td>
										<td>${l.boardWriter }</td>
										<td>${l.boardDate }</td>
										<td>${l.boardHit }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div>
							<nav aria-label="Page navigation example">
							  <ul class="pagination">
							    <li class="page-item">
							      <a class="page-link pn" data-pn="${pager.startNum - 1 }" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    	<c:forEach begin="${pager.startNum }" end="${pager.endNum }" var="i">
							    		<li class="page-item"><a class="page-link pn" data-pn="${i }">${i }</a></li>
							    	</c:forEach>
							    
							    <li class="page-item">
							      <a class="page-link pn" data-pn="${pager.endNum + 1 }" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</nav>
						</div>
						
						<div>
							<!--  GET 방식(단순 주소 보내기) -->
							<!-- 디자인 할 클래스를 부트스트랩에서 복사해서 가져옴 -->
							<!-- a 태그를 버튼처럼 보이게 만들어줌 -->
							<a href="./add" class="btn btn-outline-primary">글쓰기</a>
						</div>
						
					</div>
				</div>
			</div>
			<!-- End Content -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>