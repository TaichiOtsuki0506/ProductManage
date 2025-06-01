package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDAO;
import model.entity.CategoryBean;

@WebServlet("/category-register")
public class CategoryRegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String categoryIdSr = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");

		try {
			int categoryId = Integer.parseInt(categoryIdSr);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category-register.jsp");
		dispatcher.forward(request, response);
	}
}
