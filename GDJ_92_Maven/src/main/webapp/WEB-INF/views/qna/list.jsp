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
					<!-- 메인 페이지 사이드바에서 BOARD에서 Product를 클릭했을 때 나와야하는 페이지 구성 -->
					<!-- Products(상품)의 목록 리스트가 나와야 하니까 -->
					<div class="row col-md-8 offset-md-2">					
						<table class="table table-striped">
							<thead>
								<tr>
									<th>글 번호</th>
									<th>글 제목</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="l" items="${list }">
								<tr>
									<td>${l.boardNum }</td>
									<td>${l.boardTitle }</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						
						<div>
								<!--  GET 방식(단순 주소 보내기) -->
								<!-- 디자인 할 클래스를 부트스트랩에서 복사해서 가져옴 -->
								<!-- a 태그를 버튼처럼 보이게 만들어줌 -->
								<a href="./add" class="btn btn-outline-primary">답글달기</a>
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