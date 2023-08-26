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


<form action='/comment/add.jsp' method='post' enctype='multipart/form-data'>

<div style="display: flex; flex-direction: column; align-items: center; height: 20vh;">
<br>

  <textarea name="content" class="custom-textarea">댓글을 입력하세요</textarea>
  <button>등록</button>
</div>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>



