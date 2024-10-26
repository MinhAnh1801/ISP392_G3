// TransactionsDAO.java
package DAO;

import Context.DBContext;
import Model.Transactions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class TransactionsDAO extends DBContext {

    // Phương thức lấy danh sách giao dịch theo user_id
    public List<Transactions> getTransactionsByUserId(int userId) {
        List<Transactions> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM Transactions WHERE user_id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transactions transaction = new Transactions(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("amount"),
                        rs.getString("transaction_type"),
                        rs.getString("description"),
                        rs.getString("transaction_date")
                );
                transactionList.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    public static void main(String[] args) {
        TransactionsDAO dao = new TransactionsDAO();

        // Test với các tham số mẫu
        int userId = 2; // Giả sử userId là 1
        String transactionType = "fee"; // Lọc theo loại giao dịch 'Credit'
        String transactionMonth = "2024-10"; // Lọc theo tháng 10 năm 2024
        int start = 0; // Bắt đầu từ giao dịch đầu tiên
        int total = 5; // Lấy 5 giao dịch mỗi lần

        // Gọi phương thức getFilteredTransactions
        List<Transactions> transactions = dao.getFilteredTransactions(userId, transactionType, transactionMonth, start, total);

        // In kết quả ra màn hình
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            transactions.forEach(transaction -> {
                System.out.println("Transaction ID: " + transaction.getId());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Type: " + transaction.getTransaction_type());
                System.out.println("Date: " + transaction.getTransaction_date());
                System.out.println("-----------------------------");
            });
        }
    }

    public void recordTransaction(int userId, int paymentId, int paymentAmount, String paymentType) {
        String sql = "INSERT INTO Transactions (user_id, amount, transaction_date, transaction_type) VALUES (?, ?, GETDATE(), ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, paymentAmount);
            stmt.setString(3, paymentType); // Thêm transaction_type từ payment_type
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTransaction(Transactions transaction) {
        String sql = "INSERT INTO Transactions (user_id, amount, transaction_type, description, transaction_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transaction.getUser_id());
            ps.setInt(2, transaction.getAmount());
            ps.setString(3, transaction.getTransaction_type());
            ps.setString(4, transaction.getDescription());
            ps.setString(5, transaction.getTransaction_date());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transactions> getFilteredTransactionsByUserId(int userId, String transactionType) {
        List<Transactions> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE user_id = ?";

        if (transactionType != null && !transactionType.isEmpty()) {
            sql += " AND transaction_type = ?";
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            if (transactionType != null && !transactionType.isEmpty()) {
                ps.setString(2, transactionType);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transactions transaction = new Transactions(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("amount"),
                        rs.getString("transaction_type"),
                        rs.getString("description"),
                        rs.getString("transaction_date")
                );
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    public int getTransactionCount(int userId, String transactionType, String transactionMonth) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Transactions WHERE user_id = ?");

        if (transactionType != null && !transactionType.isEmpty()) {
            sql.append(" AND transaction_type = ?");
        }
        if (transactionMonth != null && !transactionMonth.isEmpty()) {
            sql.append(" AND MONTH(transaction_date) = MONTH(?) AND YEAR(transaction_date) = YEAR(?)");
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int index = 1;
            ps.setInt(index++, userId);
            if (transactionType != null && !transactionType.isEmpty()) {
                ps.setString(index++, transactionType);
            }
            if (transactionMonth != null && !transactionMonth.isEmpty()) {
                ps.setString(index++, transactionMonth);
                ps.setString(index++, transactionMonth);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
