package com.wicky.chedam.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
public class TypedRow extends ArrayList<TypedCell> implements Serializable {
}
