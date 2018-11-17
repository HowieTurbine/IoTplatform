package com.service.com.dao;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SQLInterpreter {
    private static DatabaseConnection util;


    public SQLInterpreter() {
        util = new DatabaseConnection();
    }

    public String getData(String password, int id) {
        String SQL = "SELECT password,FreeMode from user where id=" + id;
        try {
            Connection conn = util.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            String database_password = rs.getString(1);
            int FreeMode = rs.getInt(2);
            Date d = new Date(System.currentTimeMillis());

            stmt.executeUpdate("UPDATE user SET last=\""+d+"\" WHERE id="+id);
            System.out.println("UPDATE user SET last=\""+d+"\" WHERE id="+id);
            if(FreeMode==1)
                return "OK";
            if (database_password.equals(password))
                return "OK";
            else
                return "NO";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public Map<String, Object> getTableData() {
        String query = "SELECT * from user";
        HashMap<String, Object> response = new HashMap<>();
        double start, end;
        start = System.currentTimeMillis();
        try {
            Connection conn = util.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            end = System.currentTimeMillis();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            response.put("status", "OK");
            List<List<String>> TotalResult = new ArrayList<>();
            List<String> NameCol = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                String name = rsmd.getColumnName(i);
                NameCol.add(name);
            }
            TotalResult.add(NameCol);
            while (rs.next()) {
                List<String> DataCol = new ArrayList<>();
                for (int i = 1; i <= count; i++) {
                    String name = rsmd.getColumnName(i);
                    String cur = rs.getString(name);
                    DataCol.add(cur);
                }
                TotalResult.add(DataCol);
            }
            response.put("result", TotalResult);
            response.put("time", (end - start) / 1000);

        } catch (SQLException e) {
            end = System.currentTimeMillis();
            String errorMessage = util.processException(e);
            response.put("status", "ERROR");
            response.put("result", errorMessage);
            response.put("time", (end - start) / 1000);
        }
        return response;
    }

    public String login(String account,String password) {
        String query = "SELECT password from login WHERE account=\""+account+"\"";
        try {

            Connection conn = util.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String database_password = rs.getString(1);
            if (database_password.equals(password))
            {
                System.out.println("OK");
                return "OK";
            }

            else
                return "NO";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String changeFreeModel(int value,int id) {
        String query = "UPDATE user SET FreeMode="+value+" WHERE id="+id;
        try {
            Connection conn = util.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(query);
            System.out.println("F OK");
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
