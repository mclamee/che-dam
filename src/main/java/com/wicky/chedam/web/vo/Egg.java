package com.wicky.chedam.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Egg implements Serializable {
    private Integer id;
    @Builder.Default
    private List<Rope> ropes = new ArrayList<>();

    private EggType type;
    private EggYolk yolk;

    public void addRope(Egg toEgg, RefKey... refKeys){
        this.addRope(toEgg, Arrays.asList(refKeys));
    }

    public void addRope(Egg toEgg, List<RefKey> refKeys){
        this.ropes.add(Rope.builder().from(this).to(toEgg).refKeys(refKeys).build());
    }
}
