package com.wicky.chedam.service;

import com.wicky.chedam.dao.po.TypedCell;
import com.wicky.chedam.dao.po.TypedEntity;
import com.wicky.chedam.dao.po.TypedRow;
import com.wicky.chedam.service.bo.SqlEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExportService {

    public List<String> exportAsSql(SqlEntity entity, TypedEntity dbData) {

        List<String> result = new ArrayList<>();

        String endOfLine = ";";
        String template = "insert into "+entity.getTableName()+" (:fields) values (:values)" + endOfLine;

        for (TypedRow row : dbData) {
            StringBuilder sbFields = new StringBuilder();
            StringBuilder sbValues = new StringBuilder();
            for (TypedCell cell : row) {
                sbFields.append(cell.getName()).append(", ");
                sbValues.append(getValueStr(cell.getType(), cell.getValue())).append(", ");
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
