package com.wicky.chedam.service;

import com.wicky.chedam.core.SqlAnalyzer;
import com.wicky.chedam.dao.MysqlJdbcDao;
import com.wicky.chedam.dao.TypedObject;
import com.wicky.chedam.web.vo.Egg;
import com.wicky.chedam.web.vo.Rope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PullService {

    @Autowired
    private MysqlJdbcDao jdbcDao;

    public List<String> extract(Rope root) {
        Egg curr = root.getCurr();
        String ddl = curr.getDdl();
        SqlAnalyzer analyzer = new SqlAnalyzer();

        String tableName = analyzer.toTableName(ddl);
        String query = analyzer.toQuery(ddl);
        Map<String, Class<?>> types = analyzer.toTypesMap(ddl);

        List<List<TypedObject>> dbResult = jdbcDao.extractData(query, types);

        List<String> results = convertToInsertSql(tableName, dbResult);

        return results;
    }

    private List<String> convertToInsertSql(String tableName, List<List<TypedObject>> dbResult) {

        List<String> result = new ArrayList<>();

        String endOfLine = ";";
        String template = "insert into order_header (:fields) values (:values)" + endOfLine;

        for (List<TypedObject> objects : dbResult) {
            StringBuilder sbFields = new StringBuilder();
            StringBuilder sbValues = new StringBuilder();
            for (TypedObject obj : objects) {
                sbFields.append(obj.getName()).append(", ");
                sbValues.append(getValueStr(obj.getType(), obj.getValue())).append(", ");
            }

            if(sbFields.length() > 0){
                sbFields.deleteCharAt(sbFields.length() - 2);
                sbValues.deleteCharAt(sbValues.length() - 2);
            }

            String withFields = StringUtils.replace(template, ":fields", sbFields.toString());
            String statement = StringUtils.replace(withFields, ":values", sbValues.toString());

            result.add(statement);
        }

        return result;
    }

    private String getValueStr(Class<?> type, Object value) {
        System.out.println("type = " + type);
        switch(type.getSimpleName()) {
            case "Integer":
                return "" + value;
            case "String":
            case "Timestamp":
                return "'" + value + "'";
        }
        return value.toString();
    }
}
