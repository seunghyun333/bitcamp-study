<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>

<%@ page import="bitcamp.personalapp.vo.Board" %>
<%@ page import="bitcamp.personalapp.vo.AttachedFile" %>

<jsp:useBean id="boardDao" type="bitcamp.personalapp.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>


<% 
   request.setAttribute("refresh", "2;url=list.jsp" );
    Board board = boardDao.findBy(Integer.parseInt(request.getParameter("no")));
    pageContext.setAttribute("board", board);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글</h1>

<% 
    if (board == null) {
%>    
<p>해당 번호의 게시글이 없습니다!</p>
<%
    } else {
%>   	
<form action='/board/update.jsp' method='post' enctype='multipart/form-data'>
<table border='1'>
<tr><th style='width:120px;'>번호</th>
           <td style='width:300px;'><input type='text' name='no' value='${board.no}' readonly></td></tr>
<tr><th>제목</th> <td><input type='text' name='title' value='${board.title}'></td></tr>

<tr><th>내용</th>
           <td><textarea name='content' style='height:300px; width:500px;'>${board.content}</textarea></td></tr>


<tr><th>작성자</th> <td>${board.mno.name}</td></tr>
<tr><th>비밀여부</th> <td>${board.secret}</td></tr>
<tr><th>조회수</th> <td>${board.v_count}</td></tr>
<tr><th>등록일</th> <td>${simpleDateFormatter.format(board.w_date)}</td></tr>
<tr><th>첨부파일</th><td>

<%
     for (AttachedFile file : board.getAttachedFiles()) {
         pageContext.setAttribute("file", file);
%>      		  
<a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-07/board/${file.filePath}'>${file.filePath}</a>
[<a href='/board/file/delete?no=${file.no}'>삭제</a>]
<br>
<%
        }
%>


<input type='file' name='files' multiple>

</td></tr>
</table>
<div>
<button>변경</button>
<button type='reset'>초기화</button>
<a href='/board/delete?no=${board.no}'>삭제</a>
<a href='/board/list.jsp'>목록</a>
</div>
</form>

<% 
      try {
        board.setV_count(board.getV_count() + 1);
        boardDao.updateCount(board);
        sqlSessionFactory.openSession(false).commit();

      } catch (Exception e) {
        sqlSessionFactory.openSession(false).rollback();
      }
    }
%>
<jsp:include page="../footer.jsp"/>
</body>
</html>

  





