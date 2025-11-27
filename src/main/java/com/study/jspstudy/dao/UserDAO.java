package com.study.jspstudy.dao;

import com.study.db.DBConnection;
import com.study.jspstudy.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 회원가입
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (user_id, password, name, school_name, grade, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getSchoolName());
            pstmt.setString(5, user.getGrade());
            pstmt.setString(6, user.getEmail());

            int rows = pstmt.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 로그인
    public User login(String userId, String password) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapRowToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 전체 회원 목록
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // id로 한 명 찾기 (필요하면 사용)
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRowToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 회원 삭제
    public boolean deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 마이페이지: 내 정보 수정
    public boolean updateUser(User user) {
        String sql = "UPDATE users " +
                "SET password = ?, name = ?, school_name = ?, grade = ?, email = ? " +
                "WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSchoolName());
            pstmt.setString(4, user.getGrade());
            pstmt.setString(5, user.getEmail());
            pstmt.setInt(6, user.getId());

            int rows = pstmt.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 관리자 페이지: 관리자 권한 부여/해제
    public boolean updateAdminFlag(int id, boolean isAdmin) {
        String sql = "UPDATE users SET is_admin = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, isAdmin);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ResultSet -> User 객체 변환
    private User mapRowToUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setUserId(rs.getString("user_id"));
        u.setPassword(rs.getString("password"));
        u.setName(rs.getString("name"));
        u.setSchoolName(rs.getString("school_name"));
        u.setGrade(rs.getString("grade"));
        u.setEmail(rs.getString("email"));
        u.setAdmin(rs.getBoolean("is_admin"));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        return u;
    }
}
