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
					<div class="row justify-content-center">
						<div class="col-md-6">
						<form method="post">
							<input type="hidden" name="productNum" value="${vo.productNum }">						
							<div class="mb-3">
							  <label for="title" class="form-label">Title</label>
							  <input type="text" class="form-control" name="productaTitle" value="${vo.productTitle }">
							</div>
							<div class="mb-3">
							  <label for="contents" class="form-label">Contents</label>
							  <textarea class="form-control" rows="9" name="productContents">${vo.productContents }</textarea>
							</div>
							<div class="mb-3">
							  <label for="rate" class="form-label">Rate</label>
							  <input type="text" class="form-control" name="productRate">${vo.productRate }</input>
							</div>

							<div class="mb-3">
							  <label for="kind" class="form-label">Kind</label>
							  <input type="text" class="form-control" name="productKind">${vo.kindName }</input>
							</div>
							
							<div>
								<!--  GET 방식(단순 주소 보내기) -->
								<!-- 디자인 할 클래스를 부트스트랩에서 복사해서 가져옴 -->
								<!-- a 태그를 버튼처럼 보이게 만들어줌 -->
								<button type="submit" class="btn btn-outline-primary">submit</button>
							</div>
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
</body>
</html>