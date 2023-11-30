package com.spjiang.fastfds.testhttp;

import lombok.Data;

import java.io.Serializable;

@Data
public class Code implements Serializable {
    private String text;
    private String uuid;
}
