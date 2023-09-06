<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>
<form action='add' method="post" enctype="multipart/form-data">
<table border="1">
<tr>
  <th>이름</th> <td style="width:200px;"><input type='text' name='name'></td>
</tr>
<tr>
  <th>이메일</th> <td style="width:180px;"><input type='email' name='email'></td>
</tr>
<tr>
  <th>비밀번호</th> <td style="width:180px;"><input type='password' name='pw'></td>
</tr>
<tr>
  <th>전화번호</th> <td style="width:180px;"><input type='number' name='tel'></td>
</tr>
<tr>
  <th>사진</th> <td><input type='file' name='photofile'></td>
</tr>

</table>
<button>등록</button>
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>

