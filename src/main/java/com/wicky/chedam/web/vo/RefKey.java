package com.wicky.chedam.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefKey implements Serializable {
    private String fromKey;
    private String toKey;

    public static RefKey newRef(String fromKey, String toKey){
        return new RefKey(fromKey, toKey);
    }

    public static RefKey newRef(String key){
        return new RefKey(key, key);
    }
}
