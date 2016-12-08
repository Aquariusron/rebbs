<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="beans.Position" %>
<%@ page import="beans.Branch" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<br />
<a href="./">戻る</a>
<br />
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<br />
<div class="form-area">
	<form action="newMessage" method="post">
			件名：<input type="text" name="subject" height="80" width="1" class="tweet-box" value="${message.subject}">(50文字まで)<br />
			カテゴリー：<input type="text" name="category" height="80" width="1" class="tweet-box" value="${message.category}">(10文字まで)<br />
			本文：<br /><textarea name="text" cols="80" rows="5" class="tweet-box"><c:out value="${message.text}" /></textarea>
			<br />
			<input type="submit" value="新規投稿">(1000文字まで)
	</form>
	<c:remove var="message" scope="session"/>
</div>
<br />
<br />

<div class = "copyright">Copyright(c)Akane Yamashita</div>
</body>
</html>