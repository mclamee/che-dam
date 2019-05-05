package com.wicky.chedam.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypedObject {
    private String name;
    private Class<?> type;
    private Object value;
}
