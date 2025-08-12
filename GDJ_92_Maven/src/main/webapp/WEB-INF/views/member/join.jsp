<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
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
					<form method="post" enctype="multipart/form-data">
						<div class="mb-3">
						  <label for="id" class="form-label">ID</label>
						  <input type="text" class="form-control" name="username">
						</div>
						<div class="mb-3">
						  <label for="password" class="form-label">Password</label>
						  <input type="password" class="form-control" name="password">
						</div>
						<div class="mb-3">
						  <label for="name" class="form-label">Name</label>
						  <input type="text" class="form-control" name="name">
						</div>
						<div class="mb-3">
						  <label for="email" class="form-label">Email</label>
						  <input type="text" class="form-control" name="email">
						</div>
						<div class="mb-3">
						  <label for="phone" class="form-label">Phone</label>
						  <input type="text" class="form-control" name="phone">
						</div>
						<div class="mb-3">
						  <label for="birth" class="form-label">Birth</label>
						  <input type="date" class="form-control" name="birth">
						</div>
						
						<div>
							<label for="file" class="form-label">File</label>
							<input type="file" class="" name="profile">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<!-- End Content -->
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		<!-- End of Content Wrapper -->
		
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	
</body>
</html>