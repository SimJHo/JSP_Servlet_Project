<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.nav-item{
		margin: 15px;
	}
	.navbar .nav-item>a{
		border-radius: 10px;
	}
	.navbar-brand{
		padding: 10px;
		border-radius: 10px;
		font-weight: bolder;
	}
	.navbar .nav-item>a:hover, .navbar-brand:hover{
		background-color: rgba(255, 255, 255, 0.2); 
	}
	nav{
		margin-bottom: 10px;
	}
	h1{
		font-weight: bolder;
	}
</style>
</head>
<body>
	<header class="container mb-auto">
		<a href="/JSP_Servlet_Project/main.do"><img src="./img/JSP_Servlet_Project.png"></a>

		<nav class="navbar navbar-expand-lg bg-dark bg-gradient">
			<div class="container-fluid">
				<a class="navbar-brand text-light" href="/JSP_Servlet_Project/main.do">홈으로</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav">
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light" href="#" role="button" 
							data-bs-toggle="dropdown" aria-expanded="false"> 투표 </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/candidate.do">후보조회</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/vote.jsp">투표하기</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/vote_check.do">투표검수조회</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/vote_count.do">후보자등수</a></li>
							</ul>
						</li>
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> 홈쇼핑</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/registration.do">회원등록</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/list.do">회원목록조회/수정</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/sales.do">회원매출조회</a></li>
							</ul>
						</li>
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> 골프 </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/golf_teacher.do">강사조회</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/golf_regist_view.do">수강신청</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/golf_student.do">회원정보조회</a></li>
								<li><a class="dropdown-item" href="/JSP_Servlet_Project/golf_sum.do">강사매출현황</a></li>
							</ul>
						</li>
						
						<li class="nav-item">
							<a class="nav-link text-light" href="/JSP_Servlet_Project/rsp_start.jsp"> 가위바위보 </a>
						</li>
						
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>