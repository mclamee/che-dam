package com.wicky.chedam.core;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlAnalyzer {
    public static final Pattern fieldsPattern = Pattern.compile("(?<field>\\w+)\\s*(?<type>\\w+)(\\(\\d*\\))?\\s*(not\\s*)?null\\s*,?");
    public static final Pattern tableNamePattern = Pattern.compile("create\\s+table\\s+(if\\s+exists\\s+)?(?<tableName>\\w+)\\s+\\(");

    public String toQuery(String ddl) {
        Map<String, Class<?>> types = this.toTypesMap(ddl);

        String tableName = this.toTableName(ddl);

        String select = "select ";
        String from = "from ";
        String where = "where 1=1 ";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Class<?>> entry : types.entrySet()) {
            sb.append(entry.getKey());
            sb.append(", ");
        }
        // remove last comma
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.insert(0, select).append(from).append(tableName).append(" ").append(where);
        return sb.toString();
    }

    public String toTableName(String ddl) {
        Matcher ma = tableNamePattern.matcher(ddl);
        if(ma.find()) {
            String tableName = ma.group("tableName");
            return tableName;
        }
        return "";
    }

    public Map<String, Class<?>> toTypesMap(String ddl) {
        HashMap<String, Class<?>> resultMap = new LinkedHashMap<>();
        Matcher ma = fieldsPattern.matcher(ddl);
        while(ma.find()){
            String field = ma.group("field");
            String type = ma.group("type");
            resultMap.put(field, getClazz(type));
        }
        return resultMap;
    }

    private Class<?> getClazz(String type) {
        switch (type) {
            case "int":
                return Integer.class;
            case "varchar":
                return String.class;
            case "datetime":
                return Timestamp.class;
        }
        return null;
    }

}
