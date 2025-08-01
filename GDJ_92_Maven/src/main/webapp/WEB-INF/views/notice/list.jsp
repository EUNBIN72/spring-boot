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
					<!-- 메인 페이지 사이드바에서 BOARD에서 NOTICE를 클릭했을 때 나와야하는 페이지 구성 -->
					<!-- NOTIVE(공지사항)의 목록 리스트가 나왔으면 좋겠잖아 -->
					<!-- 그럼 리스트 어떻게 보여줄거야 -->
					<div class="row col-md-8 offset-md-2">
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
										<td><a href="./detail?boardNum=${l.boardNum }">${l.boardTitle }</a></td>
										<td>${l.boardWriter }</td>
										<td>${l.boardDate }</td>
										<td>${l.boardHit }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
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
</body>
</html>