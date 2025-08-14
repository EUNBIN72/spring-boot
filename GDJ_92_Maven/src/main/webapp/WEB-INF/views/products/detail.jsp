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
					<div class="row col-md-8 offset-md-2">	
						<table class="table table-striped">
							<thead>
								<tr>
									<th>상품 번호</th>
									<th>상품 이름</th>
									<th>상품 내용</th>
									<th>상품 이율</th>
									<th>판매기간</th>
									<th>상품종류</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${vo.productNum }</td>
									<td>${vo.productName }</td>
									<td>${vo.productContents }</td>
									<td>${vo.productRate }</td>
									<td>${vo.productDate }</td>
									<td>${vo.productKindVO.kindName }</td>
								</tr>
							</tbody>
						</table>
						<div>
							<form action="./delete" method="post">
								<!-- productNum은 hidden으로 가져옴(안보이지만 정보는 가져오는 것) -->
								<input type="hidden" name="productNum" value="${vo.productNum}">
								<a class="btn btn-outline-primary" href="./update?productNum=${vo.productNum}">Update</a>
								<button class="btn btn-outline-danger">Delete</button>
							</form>
						<div>
						<button class="btn btn-primary" id="cartAdd" data-product-num="${vo.productNum}">장바구니</button>
							<form action="/account/add" method="post">
								<input type="hidden" name="productNum" value="${vo.productNum}">
								<button class="btn btn-primary" id="add" data-product-num="${vo.productNum}">가입</button>
							</form>
						</div>
					
					</div>
				</div>
			</div>
			<!-- End Content -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		<script type="text/javascript" src="/js/product/detail.js"></script>
</body>
</html>