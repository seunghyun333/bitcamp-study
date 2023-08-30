<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.personalapp.vo.AttachedFile"%>
<%@ page import="bitcamp.personalapp.vo.Board"%>
<jsp:useBean id="board" class="bitcamp.personalapp.vo.Board" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My-Diary♥</title>
<style>
  h1 {
    color: pink;
  }
  .image-grid img {
    max-width: 100%;
    height: auto;
  }
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 style='text-align:center' >My-Diary♥</h1>
<div style="text-align:center;">
    <a href='/app/board/add'>오늘의 기분을 공유하기</a>
<div class="image-grid">
<%
    if (board != null) {
        List<AttachedFile> attachedFiles = board.getAttachedFiles();
        if (attachedFiles != null) {
            for (AttachedFile file : attachedFiles) {
                pageContext.setAttribute("file", file);
%>
        <div class="image-container">
            <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-07/board/${file.filePath}' target="_blank">
                <img src='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-07/board/${file.filePath}' alt='Image'>
            </a>
            <br>
        </div>
<%
            }
        } else {
%>
        <p>게시판 or 다이어리 or 회원 사진 예정</p>
<%
        }
    } 
%>
</div>
</div>
<br>
<jsp:include page="footer.jsp"/>
</body>
</html>
