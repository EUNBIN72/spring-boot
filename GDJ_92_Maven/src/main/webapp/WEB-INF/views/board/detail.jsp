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
						<!-- controller에서 detail이라는 이름으로 보냈기 때문에 detail. 으로 시작 -->
						<table class="table table-striped">
							<thead>
									<tr>
										<th>Title</th>
										<th>Writer</th>
									</tr>
								</thead>
								<tbody>
									<!-- 각 행에서 반복 출력해야 될 것들 어떻게 출력할래 -->
									<!-- JSTL(JSP Standard Tag Library)의 반복문 태그 -->
									<!-- 배열, 리스트, 맵 등 여러 개의 값을 하나씩 꺼내 쓸 때 사용(자바의 for-each문과 비슷한 역할) -->
									
									<!-- ${list }는 
										NoticeController의 model.addAttribute("list", list);에서 "list"랑 같은거임 -->
									<!-- list의 각 요소를 반복문이 돌 때마다 vo 변수에 담아서 출력할거니까
										출력해줄 때 vo.뭐시기 를 써주면서 출력해야지 -->	
										<tr>
											<td>${vo.boardTitle }</a></td>
											<td>${vo.boardWriter }</td>
										</tr>
								</tbody>
						</table>
						<div>
							<form id="frm">
								<!-- boardNum은 안보이게 hidden으로 처리 -->
								<input type="hidden" name="boardNum" value="${vo.boardNum }">
							</form>
							
							<button class="btn btn-outline-primary action" data-kind="u">Update</button>
							<button class="btn btn-outline-danger action" data-kind="d">Delete</button>
						</div>
				</div>
			</div>
			<!-- End Content -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		
		<script type="text/javascript" src="/js/board/board_detail.js"></script>
</body>
</html>