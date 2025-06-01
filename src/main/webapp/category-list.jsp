<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL使用の宣言 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリリスト</title>
</head>
<body>

	<h2>カテゴリリスト</h2>

	<c:if test="${empty categoryList}">
		<p>カテゴリが見つかりませんでした。</p>
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

</body>
</html>
