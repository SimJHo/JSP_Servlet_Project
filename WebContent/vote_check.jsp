<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	main{
		min-height: 400px;
	}
</style>
</head>

<body class="d-flex h-100 text-center text-bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	
		<jsp:include page="header.jsp"/>

		<main class="px-3 container">
		
			<br><h1>투표 검수 조회</h1><br>
		
			<table class="table table-dark table-striped">
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
				<c:forEach var="voteCheck" items="${ check }">
					<tr>
						<td>${ voteCheck.v_name }</td>
						<td>${ voteCheck.v_jumin }</td>
						<td>${ voteCheck.v_age }</td>
						<td>${ voteCheck.v_gender }</td>	
						<td>${ voteCheck.m_no }</td>
						<td>${ voteCheck.v_time }</td>
						<td>${ voteCheck.v_confirm }</td>
					</tr>
				</c:forEach>
			</table>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>