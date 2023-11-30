package com.spjiang.fastfds.testhttp;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dataset implements Serializable {
    private String name;
    private Integer dataType;
    private Integer annotateType;
    private String industryType;
    private Integer modelScene;
    private Integer module;
    private String remark;
    private Integer type;
    private boolean isImport;
}
