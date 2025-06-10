package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDAO;
import model.entity.CategoryBean;

public class CategoryRegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoryIdStr = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");

		try {
			int categoryId = Integer.parseInt(categoryIdStr);
			CategoryBean newCategory = new CategoryBean();
			newCategory.setCategoryId(categoryId);
			newCategory.setCategoryName(categoryName);

			CategoryDAO categoryDAO = new CategoryDAO();
			boolean result = categoryDAO.registerCategories(newCategory);

			if (result) {
				request.setAttribute("message", "カテゴリの登録が完了しました。");
			} else {
				request.setAttribute("message", "登録に失敗しました。");
			}

			// 登録後も最新のカテゴリ一覧を取得してJSPに渡す
			List<CategoryBean> categoryList = categoryDAO.getAllCategories();
			request.setAttribute("categoryList", categoryList);

			// 登録結果を表示するためにJSPへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/category-register.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException e) {
			request.setAttribute("message", "カテゴリIDは半角数字を入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/category-register.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "予期せぬエラーが発生しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/category-register.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryBean> categoryList = null;
		try {
			categoryList = categoryDAO.getAllCategories();
			request.setAttribute("categoryList", categoryList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "カテゴリ一覧の取得中にエラーが発生しました。");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/category-register.jsp");
		dispatcher.forward(request, response);
	}
}
