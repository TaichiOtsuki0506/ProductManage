<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ登録と一覧表示</title>
</head>
<body>

	<h2>カテゴリ登録</h2>

	<form action="${pageContext.request.contextPath}/category-register"
		method="post">
		<div>
			<label for="categoryId">カテゴリID:</label> <input type="text"
				id="categoryId" name="categoryId">
		</div>
		<br>
		<div>
			<label for="categoryName">カテゴリ名:</label> <input type="text"
				id="categoryName" name="categoryName">
		</div>
		<br>
		<button type="submit">登録</button>
	</form>

	<hr>

	<h2>カテゴリ一覧</h2>
	<c:if test="${not empty message}">
		<p style="color: green;">${message}</p>
	</c:if>

	<c:if test="${not empty categoryList}">
		<table border="1">
			<tr>
				<th>カテゴリID</th>
				<th>カテゴリ名</th>
			</tr>
			<c:forEach var="category" items="${categoryList}">
				<tr>
					<td>${category.categoryId}</td>
					<td>${category.categoryName}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty categoryList}">
		<p>登録されているカテゴリはありません。</p>
	</c:if>

</body>
</html>
