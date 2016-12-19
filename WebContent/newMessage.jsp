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
	<link href="bbs.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class ="main-contents">
<a href="./">戻る</a>
<br />
<br />
<div class="title"><h2><c:out value="新規投稿"></c:out></h2></div>
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
	<form action="newMessage" method="post">
			■件名：(50文字まで)<br />
			<input type="text" name="subject" height="80" width="1" class="tweet-box" value="${message.subject}"style="margin-left:20px;"><br /><br />
			□カテゴリー：(10文字まで)<br />
			<input type="text" name="category" height="80" width="1" class="tweet-box" value="${message.category}"style="margin-left:20px;"><br /><br />
			本文：(1000文字まで)<br /><textarea name="text" cols="80" rows="7" class="tweet-box"style="margin-left:20px;"><c:out value="${message.text}" /></textarea>
			<br />
			<br />
			<input type="submit" value="新規投稿"style="margin-left:450px;">
	</form>
	<c:remove var="message" scope="session"/>
<br />
<br />
<br />
</div>
<div class = "copyright">Copyright(c)Akane Yamashita</div>
</body>
</html>