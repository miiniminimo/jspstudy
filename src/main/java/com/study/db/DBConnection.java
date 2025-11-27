package com.study.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    static {
        try (InputStream input = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("db.properties 파일을 classpath에서 찾을 수 없습니다.");
            }

            Properties prop = new Properties();
            prop.load(input);

            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
            DRIVER = prop.getProperty("db.driver", "com.mysql.cj.jdbc.Driver");

            Class.forName(DRIVER);

        } catch (Exception e) {
            throw new ExceptionInInitializerError("DB 설정 초기화 실패: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
