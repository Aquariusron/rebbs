<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー一覧</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class = "main-contents">
<div class="header">
			<a href="./">戻る</a>
			<a href="signup">新規登録</a>
</div>
<br />
<br />
<div class="profile">
	<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
</div>
<br />
<br />
<div class="users">
<%--userをfor文で回してIDと名前を一覧表示したい --%>
	<c:forEach items="${users}" var="user">
		<br />
		<div class="message">
			<div class="account-name">
				<a href="settings?id=${user.id}" >
				<span class="name"><c:out value="${user.name}" /></span></a>
			</div>
			<div class="subject"><c:out value="${user.loginId}" /></div>
			<form action="stopuser" method="post">
				<input type="hidden" name="userid" value="${user.id}">
					<c:if test="${ user.stop  == true }">
						<button name="stop" type="submit" value="false" onclick="alert('アカウントを停止しますか？')">停止</button>
						<input type ="hidden" name="id" value="${user.id}">
						<input type ="hidden" name="stop" value="${user.stop}">
					</c:if>
					<c:if test="${ user.stop  == false }">
						<button name="stop" type="submit"  value="true" onclick="alert('アカウントを復活させますか？')">復活</button>
						<input type ="hidden" name="id" value="${user.id}">
						<input type ="hidden" name="stop" value="${user.stop}">

					</c:if>
			</form>
		</div>
			<br />
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