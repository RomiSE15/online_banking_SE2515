package bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BankService {
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = new ArrayList<>();
        String query = "SELECT u.name, a.account_number, a.balance " +
                "FROM users u JOIN accounts a ON u.id = a.user_id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new UserResponse(
                        rs.getString("name"),
                        rs.getString("account_number"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static record UserResponse(String name, String accountNumber, double balance) {}
    public void addUser(String name, String accNum, double initialBalance) {
        String insertUser = "INSERT INTO users (name) VALUES (?) RETURNING id";
        String insertAcc = "INSERT INTO accounts (user_id, account_number, balance) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            int userId = -1;
            try (PreparedStatement psUser = conn.prepareStatement(insertUser)) {
                psUser.setString(1, name);
                ResultSet rs = psUser.executeQuery();
                if (rs.next()) userId = rs.getInt(1);
            }
            if (userId != -1) {
                try (PreparedStatement psAcc = conn.prepareStatement(insertAcc)) {
                    psAcc.setInt(1, userId);
                    psAcc.setString(2, accNum);
                    psAcc.setDouble(3, initialBalance);
                    psAcc.executeUpdate();
                }
                conn.commit();
                System.out.println("bank.User added to DB successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
    public void updateBalance(String name, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? " +
                "FROM users WHERE accounts.user_id = users.id AND users.name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setString(2, name);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Balance updated.");
            else System.out.println("bank.User not found or error.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String name) {
        String sql = "DELETE FROM users WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("bank.User " + name + " deleted.");
            else System.out.println("bank.User not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}