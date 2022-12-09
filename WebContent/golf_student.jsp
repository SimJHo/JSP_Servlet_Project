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
			<table class="table table-dark table-striped">
				<tr>
					<th>수강월</th>
					<th>회원번호</th>
					<th>회원명</th>
					<th>강의명</th>
					<th>강의장소</th>
					<th>수강료</th>
					<th>등급</th>
				</tr>
				<c:forEach var="golf_student" items="${ student }">
					<tr>
						<td>${ golf_student.regist_month }</td>
						<td>${ golf_student.c_no }</td>
						<td>${ golf_student.c_name }</td>
						<td>${ golf_student.teacher_code }</td>
						<td>${ golf_student.class_area }</td>
						<td>${ golf_student.tuition }</td>
						<td>${ golf_student.grade }</td>
					</tr>
				</c:forEach>
			</table>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>