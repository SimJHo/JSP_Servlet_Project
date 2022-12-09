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
	
		<jsp:include page="header.jsp" />
		
		<main class="px-3 container">
		
			<br><h1>홈쇼핑 회원 등록</h1><br>
			
			<form action="insert.do" method="post" onsubmit="alert('등록 완료!')">
				<table class="table table-dark table-striped">
					<tr>
						<th>회원번호(자동발생)</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" value="${custNo}"  name="custNo" size="20" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
					
					<tr>
						<th>회원성명</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="custName" size="20" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
					
					<tr>
						<th>회원전화</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="phone" size="30" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
					
					<tr>
						<th>회원주소</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="address" size="50" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
					
					<tr>
						<th>가입일자</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="joinDate" size="20" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
					
					<tr>
						<th>고객등급[A:VIP,B:일반,C:직원]</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="grade" size="20" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
			
					<tr>
						<th>도시코드</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" name="city" size="20" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
							</div>
						</td>
					</tr>
				
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-light">수강신청</button>
							<button type="reset" class="btn btn-light ms-3">다시쓰기</button> 
						</td>
					</tr>
				</table>
			</form>
		</main>
		
		<jsp:include page="footer.jsp" />
		
	</div>
</body>
</html>