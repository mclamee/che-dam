package com.wicky.chedam.dao.po;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TypedCell implements Serializable {
    private String name;
    private Class<?> type;
    private Object value;
}
