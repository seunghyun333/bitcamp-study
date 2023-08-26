<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>
    
<form action='/auth/login.jsp' method='post'>
<table border='1'>
<tr>
 <th>email</th> <td><input type='email' name='email' value='${cookie.email.value}'></td>
</tr>
<tr>
 <th>pw</th> <td><input type='password' name='pw'></td>
</tr>
</table> 
<button>로그인</button>
 <input type='checkbox' name='saveEmail'> 이메일 저장
  </form>
    

<jsp:include page="../footer.jsp"/>
</body>
</html>

