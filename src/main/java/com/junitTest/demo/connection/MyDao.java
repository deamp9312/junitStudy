//package com.junitTest.demo.connection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Repository
//public class MyDao {
//
//    @Autowired
//    private DataSource dataSource;  // HikariCP 커넥션 풀 DataSource
//
//    public String getNameById(Long id) throws SQLException {
//        String sql = "SELECT name FROM junit_entity WHERE id = ?";
//        try (Connection conn = dataSource.getConnection();  // 커넥션 풀에서 커넥션 획득
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setLong(1, id);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("name");
//                }
//            }
//        } // try-with-resources로 close 자동 호출(커넥션 반납)
//        return null;
//    }
//}
