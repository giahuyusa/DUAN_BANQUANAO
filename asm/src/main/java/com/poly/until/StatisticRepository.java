package com.poly.until;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class StatisticRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Tổng số sản phẩm đã bán
    public List<Map<String, Object>> getTotalSoldProducts() {
        String sql = "SELECT p.Name AS product_name, SUM(od.Quantity) AS total_sold " +
                     "FROM Order_Details od " +
                     "JOIN Products p ON od.product_id = p.Id " +
                     "GROUP BY p.Name ORDER BY total_sold DESC";
        return jdbcTemplate.queryForList(sql);
    }

    // Sản phẩm bán chạy nhất
    public Map<String, Object> getBestSellingProduct() {
        String sql = "SELECT TOP 1 p.Name AS product_name, SUM(od.Quantity) AS total_sold " +
                     "FROM Order_Details od " +
                     "JOIN Products p ON od.product_id = p.Id " +
                     "GROUP BY p.Name ORDER BY total_sold DESC";
        return jdbcTemplate.queryForMap(sql);
    }

    // Doanh thu theo ngày
    public List<Map<String, Object>> getDailyRevenue() {
        String sql = "SELECT CONVERT(DATE, o.create_date) AS order_date, SUM(o.total) AS total_revenue " +
                     "FROM Orders o GROUP BY CONVERT(DATE, o.create_date) ORDER BY order_date DESC";
        return jdbcTemplate.queryForList(sql);
    }

    // Doanh thu theo tuần
    public List<Map<String, Object>> getWeeklyRevenue() {
        String sql = "SELECT DATEPART(YEAR, o.create_date) AS year, DATEPART(WEEK, o.create_date) AS week, " +
                     "SUM(o.total) AS total_revenue FROM Orders o " +
                     "GROUP BY DATEPART(YEAR, o.create_date), DATEPART(WEEK, o.create_date) " +
                     "ORDER BY year DESC, week DESC";
        return jdbcTemplate.queryForList(sql);
    }

    // Doanh thu theo tháng
    public List<Map<String, Object>> getMonthlyRevenue() {
        String sql = "SELECT DATEPART(YEAR, o.create_date) AS year, DATEPART(MONTH, o.create_date) AS month, " +
                     "SUM(o.total) AS total_revenue FROM Orders o " +
                     "GROUP BY DATEPART(YEAR, o.create_date), DATEPART(MONTH, o.create_date) " +
                     "ORDER BY year DESC, month DESC";
        return jdbcTemplate.queryForList(sql);
    }
}
