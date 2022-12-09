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
	table {
		margin: auto;
		max-width: 600px;
	}
</style>
</head>

<body class="d-flex h-100 text-center text-bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	
		<jsp:include page="header.jsp"/>

		<main class="px-3 container">
			<table class="table table-dark table-striped">
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
				<c:forEach var="voteCount" items="${ count }">
					<tr>
						<td>${ voteCount.m_no }</td>
						<td>${ voteCount.m_name }</td>
						<td>${ voteCount.m_cnt }</td>
					</tr>
				</c:forEach>
			</table>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>