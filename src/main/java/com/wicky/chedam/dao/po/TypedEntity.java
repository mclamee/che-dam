package com.wicky.chedam.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TypedEntity extends ArrayList<TypedRow> implements Serializable {
    private String entityName;

    public TypedEntity(String tableName, List<TypedRow> rowsInList) {
        super(rowsInList);
        this.entityName = tableName;
    }
}
