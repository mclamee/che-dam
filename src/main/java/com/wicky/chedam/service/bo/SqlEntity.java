package com.wicky.chedam.service.bo;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "queryList")
public class SqlEntity implements Serializable {
    @NonNull private String tableName;
    private List<SqlQuery> queryList = new ArrayList<>();
}
