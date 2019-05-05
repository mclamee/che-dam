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
public class Rope implements Serializable {
    private Egg curr;
    private List<Ref> refs;
    private Rope next;
}
