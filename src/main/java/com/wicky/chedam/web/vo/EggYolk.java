package com.wicky.chedam.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EggYolk implements Serializable {
    private String name;
    private String data;
    @Builder.Default
    private List<String> restrictions = new ArrayList<>();
}
