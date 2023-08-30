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
    
<jsp:include page="../header.jsp"></jsp:include>    


<h1>회원 목록</h1>
<div style='margin:5px;'>
<a href='add'>회원가입</a>
</div>
<table border='1'>
<thead>
  <tr><th>회원번호</th> <th>이름</th> <th>이메일</th></tr>
</thead>
<tbody>
<c:forEach items="${list}" var="member">
	<tr>
	 <td>${member.no}</td>
	  <td>
	 <img src='http://rilqiqaqfxro19010722.cdn.ntruss.com/member/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
       <a href='detail?no=${member.no}'>${member.name}</a></td> 
       <td>${member.email}</td></tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>
</body>
</html>




