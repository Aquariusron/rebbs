<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class = "main-contents">
<div class="header">
			<a href="signup">新規登録</a>
			<a href="newMessage">新規投稿</a>
			<a href="users">ユーザー管理</a>
			<a href="login">ログイン</a>
			<a href="logout">ログアウト</a>
</div>
<br />
<br />
<div class="profile">
	<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
</div>
<br />

	<select name="messages">
		<c:forEach items="${messages}" var="message">
		<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<option value="${message.insertDate}" selected><c:out value="${message.insertDate}"></c:out></option>
		</c:forEach>
	</select>

<br />
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="comment">
				<li><c:out value="${comment}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<br />
<div class="messages">
	<c:forEach items="${messages}" var="message">
		<br />
			<div class="message">
				<div class="account-name">
					<span class="name"><c:out value="${message.name}" /></span>
				</div>

				<div class="subject"><c:out value="${message.subject}" /></div>
				<div class="category"><c:out value="${message.category}" /></div>
				<div class="text"><c:out value="${message.text}" /></div>
				<div class="name"><c:out value="${message.name}" /></div>
				<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.id == message.id}">
						<div class="account-name">
							<span class="name"><c:out value="${message.name}" /></span>
						</div>
						<%--これはログインしてるユーザーのIDを表示させるだけなのでいらない
						<div class="user_id"><c:out value="${loginUser.id}" /></div> --%>
						<div class="text"><c:out value="${comment.getComment()}" /></div>
						<%-- <div class="name"><c:out value="${comment.name}" /></div>--%>
						<div class="date"><fmt:formatDate value="${comment.getInsertDate()}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
					</c:if>
				</c:forEach>
			</div>
			<br />
			<div class="form-area">
				<form action="comment" method="post">
					コメント：<br />
					<TEXTAREA cols="100" rows="5" name="text" ></TEXTAREA>
						<input type ="hidden" name="message_id" value="${message.id}">
						<br />
						<br />
						<input type="submit" value="返信する">(500文字まで)
					<br />
				</form>
			</div>
	</c:forEach>
	<br />
	<br />
</div>
<br />
<br />
<br />
<br />
<br />
<div class = "copyright">Copyright(c)Akane Yamashita</div>
</div>
</body>
</html>