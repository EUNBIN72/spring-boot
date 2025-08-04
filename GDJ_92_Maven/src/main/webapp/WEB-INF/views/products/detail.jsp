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
									<th>Num</th>
									<th>Title</th>
									<th>Content</th>
									<th>Rate</th>
									<th>Date</th>
									<th>Kind</th>
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
							<form id="frm">
								<!-- boardNum은 안보이게 hidden으로 처리 -->
								<input type="hidden" name="boardNum" value="${vo.productNum }">
							</form>
							
							<button class="btn btn-outline-primary action" data-kind="u">Update</button>
							<button class="btn btn-outline-danger action" data-kind="d">Delete</button>
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