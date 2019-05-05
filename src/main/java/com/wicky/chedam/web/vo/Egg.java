package com.wicky.chedam.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Egg implements Serializable {
    private String name;
    private String ddl;
    private List<String> conditions;
}
