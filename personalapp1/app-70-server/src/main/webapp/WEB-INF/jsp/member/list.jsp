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
<style>
      .center-table {
        margin: 0 auto;
        border-collapse: collapse;
      }
      .center-table th, .center-table td {
        border: 1px solid black;
        padding: 5px;
      }
    </style>
</head>
<body>
    
<jsp:include page="../header.jsp"></jsp:include>    


<h1 style='margin:5px; text-align:center'>회원 목록</h1>
<div style='margin: 5px auto; text-align: center; margin-right: -250px;'>
<a href='add'>회원가입</a>
</div>
<table class="center-table">
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

<div style='margin: 5px auto; text-align: center; margin-right: -250px;'>
<a href='/'>메인</a>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>




