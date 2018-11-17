package com.service;

import com.service.com.dao.SQLInterpreter;

import java.util.Map;

public class DataService {
    private SQLInterpreter sqlInterpreter;

    public DataService() {
        sqlInterpreter = new SQLInterpreter();
    }

    public String getData(String string, int id) {
        return sqlInterpreter.getData(string, id);
    }

    public String login(String account, String password) {
        return sqlInterpreter.login(account, password);
    }

    public Map<String, Object> getTableData() {
        return sqlInterpreter.getTableData();
    }

    public String ChangeFreeMode(int value,int id){return sqlInterpreter.changeFreeModel(value,id);}
}
