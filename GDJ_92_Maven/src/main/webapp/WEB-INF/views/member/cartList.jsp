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
					<div class="col-md-8 offset-md-2">
					<h2>장바구니</h2>
					
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<div class="form-check">
											  <input class="form-check-input" type="checkbox" value="" id="checkAll">
											  <label class="form-check-label" for="checkDefault">
											    전체선택
											  </label>
										</div>
									</th>
									<th>상품명</th>
									<th>이자율</th>
									<th>상품종류</th>
								</tr>
							</thead>
							<tbody>
								<form action="./cartDelete" method="post" id="frm">
									<!-- 각 행에서 반복 출력해야 될 것들 어떻게 출력할래 -->
									<!-- JSTL(JSP Standard Tag Library)의 반복문 태그 -->
									<!-- 배열, 리스트, 맵 등 여러 개의 값을 하나씩 꺼내 쓸 때 사용(자바의 for-each문과 비슷한 역할) -->
									
									<!-- ${list }는 
										NoticeController의 model.addAttribute("list", list);에서 "list"랑 같은거임 -->
									<!-- list의 각 요소를 반복문이 돌 때마다 l 변수에 담아서 출력할거니까
										출력해줄 때 l.뭐시기 를 써주면서 출력해야지 -->	
										
									<c:forEach var="l" items="${list }">
										<tr>
											<td>
												<div class="form-check">
												  <input class="form-check-input ch" name="productNum" type="checkbox" value="${l.productNum}">
												</div>
											</td>
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
								</form>
							</tbody>
						</table>
						
						<div>
							<button class="btn btn-danger" id="del">DELETE</button>
						</div>
						
					</div>
				</div>
			</div>
			<!-- End Content -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		<script type="text/javascript" src="/js/cart/cartList.js"></script>
</body>
</html>