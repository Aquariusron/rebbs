<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="bbs.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class = "main-contents">

	<a href="newMessage">新規投稿</a>
	<a href="users">ユーザー管理</a>
	<a href="logout">ログアウト</a>
<br />
<br />
<div class="title">
	<div class="title"><h2><c:out value="掲示板" /></h2></div>
</div>
<br />
<form method="get">
□category:
	<select name="category">
		<option value=""><c:out value=""></c:out></option>
		<c:forEach items="${categories}" var="category">
			<c:if test="${ selectedCategory == category.category }">
				<option value="${category.category}" selected><c:out value="${category.category}"></c:out></option>
			</c:if>
			<c:if test="${ selectedCategory != category.category }">
				<option value="${category.category}"><c:out value="${category.category}"></c:out></option>
			</c:if>
		</c:forEach>
	</select>
	<br />
	<br />
	□date:
	<br />
		<input type="date" name="old" style="margin-left:85px;" value="${selectedOldDate}">
		<input type="date" name="current" value="${selectedCurrentDate}">

	<input type="submit" value="絞り込む">
</form>

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
			<TABLE><TR><TH><TD>
				<div class="subject"><c:out value="■${message.subject}" /></div><br />
				<div class="category"><c:out value="□${message.category}" /></div><br />
				<div class="text">
					<c:forEach var="s" items="${fn:split(message.text, '
	')}">
					    <div>${s}</div>
					</c:forEach>
				</div>
				<div class="date"><fmt:formatDate value="${message.getDate()}" pattern="yyyy/MM/dd HH:mm:ss" />
				<c:out value="${message.name}" /></div>
			</TH></TD></TR></TABLE>
		</div>
		<br />
		<div class="comment">
			<TABLE><TR><TD>
				<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.messageId == message.id}">
						<div class="text">
						<c:forEach var="s" items="${fn:split(comment.getComment(), '
	')}">
					    <div>${s}</div>
					</c:forEach>
						<%-- <c:out value="${comment.getComment()}" />--%></div>
						<div class="date"><fmt:formatDate value="${comment.getInsertDate()}" pattern="yyyy/MM/dd HH:mm:ss" />
						<c:out value="${comment.name}" /></div>
					</c:if>
				</c:forEach>
			</TD></TR>
			</TABLE>
		</div>
		<br />
		<div class="form-area">
			<form action="comment" method="post">
				コメント：(500文字まで)<br />
				<TEXTAREA cols="100" rows="5" name="text" style="margin-left:20px;"></TEXTAREA>
				<%--どのメッセージに紐つけているか値を飛ばす --%>
				<input type ="hidden" name="message_id" value="${message.id}" style="margin-left:70px;">
				<br />
				<input type="submit" value="返信する"style="margin-left:570px;">
				<br />
			</form>
		</div>
		<br />
</c:forEach>
	<br />
	<br />
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