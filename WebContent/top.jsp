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
			<c:out value=" " />
		<a href="users">ユーザー管理</a>
			<c:out value=" " />
		<a href="logout">ログアウト</a>
	</div>
	<br />
	<br />
	<br />
	<br />
	<div class="title">掲示板</div>
	<br />
	<br />
	<form method="get">
	□カテゴリー:
	<select name="category">
		<option value=""><c:out value=""></c:out></option><%--空のセレクトタブ --%>
		<c:forEach items="${ categories }" var="category">
			<c:if test="${ selectedCategory == category.category }">
				<option value="${ category.category }" selected><c:out value="${ category.category }"></c:out></option>
			</c:if>
			<c:if test="${ selectedCategory != category.category }">
				<option value="${ category.category }"><c:out value="${ category.category }"></c:out></option>
			</c:if>
		</c:forEach>
	</select>
	<br />
	<br />
	□日時　　　:
	<input type="date" name="old"  value="${ selectedOldDate }">
	<input type="date" name="current" value="${ selectedCurrentDate }">
	<input type="submit" value="絞り込む" >
	</form>
	<br/>
	<a href="./"><button name="reset" style="margin-left:20px;">絞込みリセット</button></a>
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${ errorMessages }" var="comment">
					<li><c:out value="${ comment }" />
				</c:forEach>
			</ul>
		</div>
	<c:remove var="errorMessages" scope="session"/>
	</c:if>

	<br />
	<br />
	<br />
	<c:forEach items="${ messages }" var="message">
	<table align="center"><TD>
		<br />
		<div class="message">
			<div class="subject"><c:out value="【${ message.subject }】" /></div>
			<br />
			<HR>
			<div class="category"><c:out value="カテゴリー【${ message.category }】" /></div>
			<HR>
			<br />
			<div class="text">
				<c:forEach var="s" items="${ fn:split(message.text, '
				') }">
					<c:out value="${ s }"></c:out><br />
				</c:forEach>
			</div>
			<br />
			<div class="date"><fmt:formatDate value="${ message.getDate() }" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<div class="name"><c:out value="${ message.name }" /></div>
		</div>
		<HR>
		<br />
		<div class="comment">
			<c:forEach items="${ comments }" var="comment">
				<c:if test="${ comment.messageId == message.id }">
					<div class="text">
					<%--splitで配列が出来、<br />で改行をforeachでまわしている--%>
						<c:forEach var="s" items="${ fn:split(comment.getComment(), '
	')}">
						    <c:out value="${ s }" /><br />
						</c:forEach>
					</div>
					<div class="date"><fmt:formatDate value="${ comment.getInsertDate() }" pattern="yyyy/MM/dd HH:mm:ss" />
					<c:out value="${ comment.name }" />
					</div>
				</c:if>
			</c:forEach>
		</div>
		<br />
		<div class="form-area">
			<form action="comment" method="post">
				コメント：(500文字まで)<br />
				<TEXTAREA cols="100" rows="5" name="text" style="margin-left:20px;"></TEXTAREA>
				<%--どのメッセージに紐つけているか値を飛ばす --%>
				<input type ="hidden" name="message_id" value="${ message.id }" style="margin-left:70px;">
				<br />
				<input type="submit" value="返信する"style="margin-left:570px;">
				<br />
			</form>
		</div>
		<br />
		<br />
	</TD></TABLE>
	<br />
	</c:forEach>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div class = "copyright">Copyright(c)Akane Yamashita</div>

</body>
</html>