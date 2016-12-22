<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${editUser.name}の設定</title>
	<link href="bbs.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
<a href="users">戻る</a>
<br />
<br />
<div class="title">ユーザー編集</div>
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
<div class="settingsmain">
<form action="settings" method="post" enctype="multipart/form-data"><br />
	<input type="hidden" value="${editUser.id}" name="id" />
	<input type="hidden" value="${ editUser.branchId }" name="branchId" />
 	<input type="hidden" value="${ editUser.postId }" name="positionId" />

	<label for="name">名前(10文字以下)</label>
	<br />
	<input name="name" value="${editUser.name}" id="name" style="margin-left:70px;"/><br />
	<br />
	<label for="account">ログインID(6文字以上20文字以下)</label>
	<br />
	<input name="account" value="${editUser.loginId}" style="margin-left:70px;"/><br />
	<br />
	<br />
	<label for="password">パスワード(6文字以上255文字以下)</label>
	<br />
	<input name="password" id="password" type="password" style="margin-left:70px;"/>
	<br />
	<br />
	<label for="password">パスワードの確認</label>
	<br />
	<input name="password_confirm" id="password" type="password" style="margin-left:70px;"/> <br />
	<br />
	<c:if test="${ editUser.id != loginUser.id }">
	支店：
		<select name="branchId">
			<c:forEach items="${branches}" var="branch">
				<c:if test="${ branch.id == editUser.branchId }">
					<option value="${branch.id}" selected><c:out value="${branch.name}"></c:out></option>
				</c:if>
				<c:if test="${ branch.id != editUser.branchId }">
					<option value="${branch.id}" ><c:out value="${branch.name}"></c:out></option>
				</c:if>
			</c:forEach>
		</select>
	役職：
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
	</c:if>

	<input type="submit" value="変更" style="margin-left:20px;" />
	<br />
	<br />
</form>
<br />
<br />
<div class="copyright">Copyright(c)Akane Yamashita</div>
</div>
</div>
</body>
</html>
