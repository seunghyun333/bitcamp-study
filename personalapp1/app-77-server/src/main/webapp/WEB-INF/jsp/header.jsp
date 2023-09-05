<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style='height:250px;background-color:pink;text-align:center; padding-top: 50px;'>
<a href='/index.jsp'>
	<img src='https://www.ncloud.com/public/img/logo-m.png' style='height:50px'>
</a>
	<br>
<br>

<div style="text-align: right; margin-right: 80px;">
<c:choose>
  <c:when test="${empty sessionScope.loginUser}">
  <a href='/app/auth/form'>로그인</a>
  </c:when>
  <c:otherwise>
    <c:if test="${empty sessionScope.loginUser.photo}">
      <img style='height:40px' src='/images/avatar.png'>
    </c:if>
    <c:if test="${not empty sessionScope.loginUser.photo}">
      <img src='http://rilqiqaqfxro19010722.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
    </c:if>
     ${loginUser.name} <a href='/app/auth/logout'>로그아웃</a>
  </c:otherwise>
</c:choose>
<br>


 </div>
 
<br>
<br>
	<a href='/app/diary/list'>일기</a>
	<a href='/app/board/list'>자유게시판</a>
	<a href='/app/member/list'>회원</a>

</div>


