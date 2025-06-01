<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.dao.CategoryDAO"%>
<%@ page import="model.entity.CategoryBean"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ登録入力画面</title>
</head>
<body>

	<div style="text-align: center">
		<h2>登録データ入力</h2>
		<hr style="height: 3px; background-color: #0000ff" />
		<br>
	</div>
	<p>登録する情報を入力してください</p>
	<form action="${request.contextPath}/category-register" method="post">
		<label for="categoryId">カテゴリID:</label> <input type="text"
			id="categoryId" name="categoryId" required><br> <label
			for="categoryName">カテゴリ名:</label> <input type="text"
			id="categoryName" name="categoryName" required><br> <input
			type="submit" value="登録">
	</form>

	<hr>

	<h2>カテゴリ一覧</h2>

	<%
	CategoryDAO categoryDAO = new CategoryDAO();
	List<CategoryBean> categoryList = null;
	try {
		categoryList = categoryDAO.getAllCategories();
		request.setAttribute("categoryList", categoryList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>

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
