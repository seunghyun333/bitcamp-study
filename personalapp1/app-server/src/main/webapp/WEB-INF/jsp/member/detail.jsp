<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>
    
<h1>회원</h1>

<c:if test="${empty member}">
<p>해당 번호의 회원이 없습니다!</p>
</c:if>
<c:if test="${not empty member}">
	<form action='update' method='post' enctype='multipart/form-data'>
	<table border='1'>
	<tr>
		<th style='width:120px;'>사진</th>
		<td style='width:300px;'>
		<c:if test="${empty member.photo}">
	          	<img style='height:80px' src='/images/avatar.png'>
	    </c:if>	
	    <c:if test="${not empty member.photo}">
	              <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-07/member/${member.photo}'>
	                <img src='http://rilqiqaqfxro19010722.cdn.ntruss.com//member/${member.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
	              </a>
	    </c:if> 
	           <input type='file' name='photo'></td></tr>
	           
	<tr><th style='width:120px;'>번호</th>
	           <td style='width:300px;'><input type='text' name='no' value='${member.no}' readonly></td></tr>
	<tr><th>이름</th> <td><input type='text' name='name' value='${member.name}'></td></tr>
	<tr><th>이메일</th> <td><input type='email' name='email' value='${member.email}'></td></tr>
	<tr><th>암호</th> <td><input type='password' name='pw'></td></tr>

	<tr><th>등록일</th> <td>${simpleDateFormatter.format(member.w_date)}</td></tr>
	</table>
	
	<div>
	<button>변경</button>
	<button type='reset'>초기화</button>
		<a href='delete?no=${member.no}'>삭제</a>
	<a href='list'>목록</a>
	</div>
	</form>
</c:if>
<jsp:include page="../footer.jsp"/>

</body>
</html>





