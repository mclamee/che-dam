package com.wicky.chedam.dao;

import com.wicky.chedam.service.bo.SqlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MysqlJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<List<TypedObject>> getDbResult(String query, Map<String, Class<?>> types) {
        List<List<TypedObject>> results = jdbcTemplate.query(query, (rs, rowNum) ->
                types.entrySet().stream().map(i ->
                {
                    TypedObject obj = TypedObject.builder().name(i.getKey()).type(i.getValue()).build();
                    try {
                        obj.setValue(rs.getObject(obj.getName()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return obj;
                }).collect(Collectors.toList())
        );
        return results;
    }

    public List<List<TypedObject>> getDbResult(SqlEntity entity) {
        return entity.getQueryList().stream()
                .flatMap(e -> getDbResult(e.getQuery(), e.getTypes()).stream())
                .collect(Collectors.toList());
    }
}
