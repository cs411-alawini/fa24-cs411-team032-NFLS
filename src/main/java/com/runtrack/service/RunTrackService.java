import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class RunTrackService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getTopRunnersAndProducts(String userId, java.util.Date startDate, java.util.Date endDate) throws SQLException {
        // Define the stored procedure call
        String procedureCall = "{CALL GetTopRunnersAndProducts(?, ?, ?)}";

        // Prepare input parameters
        Object[] params = new Object[] { userId, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()) };

        // Define parameter types
        int[] types = new int[] { Types.CHAR, Types.DATE, Types.DATE };

        // Execute the stored procedure
        return jdbcTemplate.execute(connection -> {
            CallableStatement callableStatement = connection.prepareCall(procedureCall);
            for (int i = 0; i < params.length; i++) {
                callableStatement.setObject(i + 1, params[i], types[i]);
            }
            return callableStatement;
        }, (CallableStatementCallback<Map<String, Object>>) callableStatement -> {
            Map<String, Object> resultMap = new HashMap<>();

            boolean hasResultSet = callableStatement.execute();

            // First ResultSet: Top 3 Runners
            if (hasResultSet) {
                List<Map<String, Object>> topRunners = new ArrayList<>();
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    Map<String, Object> runner = new HashMap<>();
                    runner.put("UserId", rs.getString("UserId"));
                    runner.put("FullName", rs.getString("FullName"));
                    runner.put("TotalDistance", rs.getDouble("TotalDistance"));
                    topRunners.add(runner);
                }
                resultMap.put("TopRunners", topRunners);
                rs.close();
            }

            // Check if there is a second ResultSet (User's total distance)
            if (callableStatement.getMoreResults()) {
                List<Map<String, Object>> userDistance = new ArrayList<>();
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("UserId", rs.getString("UserId"));
                    user.put("FullName", rs.getString("FullName"));
                    user.put("TotalDistance", rs.getDouble("TotalDistance"));
                    userDistance.add(user);
                }
                resultMap.put("UserDistance", userDistance);
                rs.close();
            }

            // Check if there is a third ResultSet (Recent Purchases)
            if (callableStatement.getMoreResults()) {
                List<Map<String, Object>> recentPurchases = new ArrayList<>();
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    Map<String, Object> purchase = new HashMap<>();
                    purchase.put("ProductId", rs.getString("ProductId"));
                    purchase.put("ProductName", rs.getString("ProductName"));
                    purchase.put("ProductPrice", rs.getBigDecimal("ProductPrice"));
                    purchase.put("UserId", rs.getString("UserId"));
                    recentPurchases.add(purchase);
                }
                resultMap.put("RecentPurchases", recentPurchases);
                rs.close();
            }

            return resultMap;
        });
    }
}
