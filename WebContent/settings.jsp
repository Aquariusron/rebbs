<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${editUser.name}の設定</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">

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

<form action="settings" method="post" enctype="multipart/form-data"><br />
	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/><br />

	<label for="account">ログインID</label>
	<input name="account" value="${editUser.loginId}" /><br />

	<label for="password">パスワード</label>
	<input name="password" id="password"/> <br />
	<label for="password">パスワードの確認</label>
	<input name="password_confirm" id="password"/> <br />


	<select name="branchId">
		<c:forEach items="${branches}" var="branch">
			<c:if test="${ branch.id == editUser.branchId }">
				<option value="${editUser.branchId}" selected><c:out value="${branch.name}"></c:out></option>
			</c:if>
			<c:if test="${ branch.id != editUser.branchId }">
				<option value="${editUser.branchId}" ><c:out value="${branch.name}"></c:out></option>
			</c:if>
		</c:forEach>
	</select>

	<select name="positionId">
		<c:forEach items="${positions}" var="position">
			<c:if test="${ position.id == editUser.postId }">
				<option value="${position.id}"selected><c:out value="${position.name}"></c:out></option>
			</c:if>
			<c:if test="${ position.id != editUser.postId }">
				<option value="${position.id}"><c:out value="${position.name}"></c:out></option>
			</c:if>

		</c:forEach>
	</select>
	<input type="submit" value="変更" /> <br />
	<a href="users">戻る</a>
</form>
<div class="copyright">Copyright(c)Akane Yamashita</div>
</div>
</body>
</html>
