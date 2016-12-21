<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
	<link href="bbs.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
<a href="users">戻る</a>
<br />
<br />
<div class="title"><h2><c:out value="ユーザー登録"/></h2></div>
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
<form action="signup" method="post">
	<input type="hidden" name="id" value="${editUser.id}" />

	<label for="name">名前(10文字以下)</label><%--valueいじる--%>
	<br /><input name="name" id="name" value="${ fillName }" style="margin-left:70px;"/><br />
	<br />
	<label for="loginId">ログインID名(6文字以上20文字以下)</label><%--valueいじる--%>
	<br /><input name="loginId" id="loginId" value="${ fillLoginId }" style="margin-left:70px;"/><br />
	<br />
	<label for="password">パスワード(6文字以上255文字以下)</label><br />
	<input name="password" type="password" id="password" style="margin-left:70px;"/><br />
	<br />
	<label for="password_confirm">パスワードの確認</label><br />
	<input name="password_confirm" type="password" id="password_confirm" style="margin-left:70px;"/><br />
	<br />
	<br />
	支店：<select name="branchId">
			<c:forEach items="${branches}" var="branch">
				<c:if test="${ branch.id == editUser.branchId }">
					<option value="${branch.id}" selected><c:out value="${branch.name}"></c:out></option>
				</c:if>
				<c:if test="${ branch.id != editUser.branchId }">
					<option value="${branch.id}"><c:out value="${branch.name}"></c:out></option>
				</c:if>
			</c:forEach>
		</select>

	役職：<select name="positionId">
			<c:forEach items="${positions}" var="position">
				<c:if test="${ position.id == editUser.postId }">
					<option value="${position.id}" selected><c:out value="${position.name}"></c:out></option>
				</c:if>
				<c:if test="${ position.id != editUser.postId }">
					<option value="${position.id}"><c:out value="${position.name}"></c:out></option>
				</c:if>
			</c:forEach>
		</select>
	<input type="submit" value="登録" style="margin-left:20px;"/><br />
</form>
<br />
<br />
<div class="copyright">Copyright(c)Akane Yamashita</div>
</div>
</body>
</html>
