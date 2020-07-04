package com.web;
import java.sql.*;

public class onlinetest {
    // MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/web?serverTimezone=UTC";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/sqltestdb?useSSL=false&useOldAliasMetadataBehavior=true&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    public static final String USER = "root";
    public static final String PASS = "1234";
    public static Connection conn = null;
    public static Statement stmt = null;
    public static void main(String[] args) {
       
        try{
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name FROM user";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("password");
    
                // �������
                System.out.print("ID: " + id);
                System.out.print(", վ������: " + name);
                System.out.print("\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}