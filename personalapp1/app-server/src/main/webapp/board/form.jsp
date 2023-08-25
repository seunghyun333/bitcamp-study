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


<h1>게시글</h1>");
<form action='/board/add' method='post' enctype='multipart/form-data'>
제목 <input type='text' name='title'><br>
내용 <textarea name='content'></textarea><br>
비밀여부 <label>
  <input type=\"radio\" name=\"secret\" value=\"0\"> 0 (비밀 아님) </label><label>
  <input type=\"radio\" name=\"secret\" value=\"1\"> 1 (비밀)
  </label><br>
파일 <input type='file' name='files' multiple><br>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>


