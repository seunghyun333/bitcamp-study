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

<jsp:include page="../header.jsp"/>

<h1 style='margin:5px; text-align:center'>로그인</h1>
    
<form action='/auth/login' method='post'>
<table border='1' class="center-table">
<tr>
 <th>email</th> <td><input type='email' name='email' value='${cookie.email.value}'></td>
</tr>
<tr>
 <th>pw</th> <td><input type='password' name='pw'></td>
</tr>
</table> 
<div style='margin: 5px auto; text-align: center; '>
<button>로그인</button>
 <input type='checkbox' name='saveEmail'> 이메일 저장
 </div>
  </form>
    

<jsp:include page="../footer.jsp"/>
</body>
</html>

