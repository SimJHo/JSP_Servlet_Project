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
</style>
</head>

<body class="d-flex h-100 text-center text-bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	
		<jsp:include page="header.jsp"/>

		<main class="px-3 container">
			<br>
			<h1> 가위/바위/보 중 한가지를 선택해주세요.</h1>
			<br>

			<form action="rsp.do">
				<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="rsp" id="btnradio1" autocomplete="off" value="1" required>
					<label class="btn btn-outline-primary" for="btnradio1"><img src="./img/scissors.png"></label>
					
					<input type="radio" class="btn-check" name="rsp" id="btnradio2" autocomplete="off" value="2">
					<label class="btn btn-outline-primary" for="btnradio2"><img src="./img/rock.png"></label>
					
					<input type="radio" class="btn-check" name="rsp" id="btnradio3" autocomplete="off" value="3">
					<label class="btn btn-outline-primary" for="btnradio3"><img src="./img/paper.png"></label>
				</div><br><br>
				<input class="btn btn-primary" type="submit" value="선택">
			</form>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>