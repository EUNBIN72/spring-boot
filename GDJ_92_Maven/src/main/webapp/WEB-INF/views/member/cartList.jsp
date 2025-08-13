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
					<h2>장바구니</h2>
					
					<!-- 테이블 형태로 보여줘야겠지 -> 그럼 테이블 만들어 -->
						<table class="table table-striped">
						<!-- 첫 번째 행 만들어서 각 열의 제목들 하드코딩 -->
							<thead>
								<tr>
									<th></th>
									<th>상품명</th>
									<th>이자율</th>
									<th>상품종류</th>
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
										<td></td>
										<td>
											<!-- c:catch 를 쓰는 이유는 답글이라는 기능은 notice 페이지에는 없고 qna 페이지에만 존재함 -->
											<!-- qna list에서만 답글 기능이 나왔으면 좋겠음 -->
											<!-- c:catch로 감싸주면 에러가 발생해도 JSP가 죽지 않고, 예외를 무시하거나, 에러 변수로 받아 처리할 수 있음 -->
									
											<a href="../products/detail?productNum=${l.productNum}">${l.productName}</a>

										</td>
										<td>${l.productRate }</td>
										<td>${l.kindNum }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
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