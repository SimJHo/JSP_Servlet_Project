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
			<form action="vote.do" method="post" onsubmit="alert('투표 완료!')">
				<table class="table table-dark table-striped">
					<tr>
						<th>주민번호</th>
						<td>
							<div class="input-group mb-3">
								<input type="number" class="form-control" name="v_jumin" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
								<span class="input-group-text" id="basic-addon2">예) 9906151234567</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>성명</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" class="form-control" name="v_name" required> 
							</div>
						</td>
					</tr>
					<tr>
						<th>투표번호</th>
						<td>
							<div class="input-group mb-3">
								<select class="form-select"	name="m_no" aria-label="Default select example" required>
										<option value="">후보 번호를 선택해주세요.</option>
										<option value="1">1번</option>
										<option value="2">2번</option>
										<option value="3">3번</option>
										<option value="4">4번</option>
										<option value="5">5번</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>투표시간</th>
						<td>
							<div class="input-group mb-3">
								<input type="number" class="form-control" name="v_time" aria-label="Recipient's username" aria-describedby="basic-addon2" required> 
								<span class="input-group-text" id="basic-addon2">예) 0930</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>투표장소</th>
						<td>
							<div class="input-group mb-3">
								<input type="text" class="form-control" name="v_area" required> 
							</div>
						</td>
					</tr>
					<tr>
						<th>유권자확인</th>
						<td>
							<div class="form-check d-inline-flex m-3">
								<input class="form-check-input" type="radio"
									name="v_confirm" id="flexRadioDefault1" value="Y" required> 
								<label class="form-check-label" for="flexRadioDefault1">
									&nbsp;확인 </label>
							</div>
							<div class="form-check d-inline-flex m-3">
								<input class="form-check-input" type="radio"
									name="v_confirm" id="flexRadioDefault2" value="N">
								<label class="form-check-label" for="flexRadioDefault2">
									&nbsp;미확인 </label>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-light">투표하기</button>
							<button type="reset" class="btn btn-light ms-3">다시하기</button>
						</td>
					</tr>
				</table>
			</form>
		</main>

		<jsp:include page="footer.jsp"/>
		
	</div>
</body>
</html>