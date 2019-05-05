package com.wicky.chedam.core;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SqlAnalyzerTest {

    @Test
    public void toQuery() {
        String sql = getDDL();

        String query = new SqlAnalyzer().toQuery(sql);
        assertEquals("select order_no, order_type, cust_name, ref_no, ref_type, entry_datetime from order_header where 1=1 ", query);
    }

    @Test
    public void toTypesMap() {
        String sql = getDDL();

        Map<String, Class<?>> maps = new SqlAnalyzer().toTypesMap(sql);

        Map<String, Class<?>> expected = new LinkedHashMap<>();
        expected.put("order_no", Integer.class);
        expected.put("order_type", Integer.class);
        expected.put("cust_name", String.class);
        expected.put("ref_no", Integer.class);
        expected.put("ref_type", Integer.class);
        expected.put("entry_datetime", Timestamp.class);

        assertEquals(expected, maps);
    }

    private String getDDL() {
        return "create table order_header (" +
                    "  order_no int not null," +
                    "  order_type int not null," +
                    "  cust_name varchar(10) not null," +
                    "  ref_no int null," +
                    "  ref_type int null," +
                    "  entry_datetime datetime not null" +
                    ")";
    }
}