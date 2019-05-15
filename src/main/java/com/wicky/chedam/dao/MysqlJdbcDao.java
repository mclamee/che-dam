package com.wicky.chedam.dao;

import com.wicky.chedam.dao.po.TypedCell;
import com.wicky.chedam.dao.po.TypedEntity;
import com.wicky.chedam.dao.po.TypedRow;
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

    public List<TypedRow> getDbResult(String query, Map<String, Class<?>> types) {
        List<TypedRow> results = jdbcTemplate.query(query, (rs, rowNum) ->
                types.entrySet().stream().map(i ->
                {
                    TypedCell cell = TypedCell.builder().name(i.getKey()).type(i.getValue()).build();
                    try {
                        cell.setValue(rs.getObject(cell.getName()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return cell;
                }).collect(TypedRow::new, TypedRow::add, TypedRow::addAll)
        );
        return results;
    }

    public TypedEntity getDbResult(SqlEntity entity) {
        // merge the list
        List<TypedRow> mergedRows = entity.getQueryList().stream()
            .flatMap(e -> {
                List<TypedRow> rows = getDbResult(e.getQuery(), e.getTypes());
                return rows.stream();
            })
            .collect(Collectors.toList());

        return new TypedEntity(entity.getTableName(), mergedRows);
    }
}
