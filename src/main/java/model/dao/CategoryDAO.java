package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

public class CategoryDAO {
    public List<CategoryBean> getAllCategories() {
        List<CategoryBean> categories = new ArrayList<>();
        String sql = "SELECT category_id, category_name FROM categories";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CategoryBean category = new CategoryBean();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // エラーハンドリング
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return categories;
    }
}
