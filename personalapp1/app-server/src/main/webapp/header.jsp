<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<div style='height:120px;background-color:pink;text-align:center';>
	<img src='https://www.ncloud.com/public/img/logo-m.png' style='height:50px'>
	<br>
	<br>

	
<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>

<% if (loginUser.getNo() == 0) { %>
  <a href='/auth/form.jsp'>로그인</a>
<%     } else {
     if (loginUser.getPhoto() == null) { %>
          <img style='height:40px' src='/images/avatar.png'> 
<% } else { %>
         <img src='http://rilqiqaqfxro19010722.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
   <% } %>      
      ${loginUser.name} <a href='/auth/logout.jsp'>로그아웃</a>
<% } %>    


	<a href='/diary/list.jsp'>일기</a>
	<a href='/board/list.jsp'>자유게시판</a>
	<a href='/member/list.jsp'>회원</a>
	  
</div>


