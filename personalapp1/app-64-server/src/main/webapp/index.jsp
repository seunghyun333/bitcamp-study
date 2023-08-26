<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My-Diary♥</title>
<style>
  h1 {
    color: pink;
  }

</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 style='text-align:center' >My-Diary♥</h1>
<div style="text-align:center;">
    <a href='../board/form.jsp'>오늘의 기분을 공유하기</a>
</div>
<br>
<jsp:include page="footer.jsp"/>
</body>
</html>

