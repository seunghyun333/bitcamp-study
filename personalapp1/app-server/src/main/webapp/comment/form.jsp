<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" 
    errorPage="/error.jsp" %>
<%@ page import="bitcamp.personalapp.vo.Board" %>
<% Board currentBoard = (Board) session.getAttribute("currentBoard"); %>
<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>

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



<form action='/comment/add.jsp' method='post'>


<div style="display: flex; flex-direction: column; align-items: center; height: 20vh;">
<br>

<label for="content" class="label"> 내용 </label> <textarea name="content" class="custom-textarea">댓글을 입력하세요</textarea>
게시글 번호<input type="number" name='cno' value='<%= currentBoard.getNo() %>'><br>
회원 번호<input type="number" name='mno' value='${loginUser.no}'><br>
  
  <button>등록</button>
</div>
</form>



</body>
</html>



