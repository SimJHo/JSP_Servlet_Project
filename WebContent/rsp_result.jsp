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
	.form-check-input{
		display: none;
	}
	.vs{
		font-size:  50px;
		vertical-align: middle;
	}
	.btn>a{
		text-decoration: none;
		color: white;
	}
</style>
</head>

<body class="d-flex h-100 text-center text-bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	
		<jsp:include page="header.jsp"/>

		<main class="px-3 container">
			<br>
			<h1> 결과 </h1>
			<br>

			<table class="table table-dark table-striped">
				<tr>
					<th>나</th>
					<th> </th>
					<th>컴퓨터</th>
				</tr>
				<c:forEach var="rspResult" items="${ rsp }">
					<tr>
						<td><img src="./img/${ rspResult.you }"></td>
						<td class="vs">VS</td>
						<td><img src="./img/${ rspResult.com }"></td>
					</tr>
					<tr>
						<td class="vs" colspan="3">${ rspResult.result }</td>
					</tr>
				</c:forEach>
			</table>
			
			<button type="button" class="btn btn-primary">
				<a href="/JSP_Servlet_Project/rsp_start.jsp">다시하기</a>
			</button>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>