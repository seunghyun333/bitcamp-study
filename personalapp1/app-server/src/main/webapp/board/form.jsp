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
  .custom-textarea {
  	display: block;
    height: 150px; 
    width: 60%; 
  }
  .label {
    margin-bottom: 5px; 
  }
</style>
</head>
<body>

<jsp:include page="../header.jsp"/>


<h1>게시글</h1>
<form action='/board/add' method='post' enctype='multipart/form-data'>
제목 <input type="text" name='title'><br><br>
<label for="content" class="label">내용 </label> <textarea name="content" class="custom-textarea"></textarea><br>
비밀여부 <label>
  <input type="radio" name="secret" value="0"> (비밀 아님) 
  </label>
  <label>
  <input type="radio" name="secret" value="1"> (비밀)
  </label><br><br>
파일 <input type='file' name='files' multiple><br> <br>
<button>등록</button> <br><br>

</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>


