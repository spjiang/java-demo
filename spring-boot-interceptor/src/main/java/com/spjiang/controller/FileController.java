package com.spjiang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package: com.spjiang.controller
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-15 19:59
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/download")
    public void download() {
        System.out.println("download....");
    }

    @RequestMapping("/upload")
    public void upload() {
        System.out.println("upload....");
    }

}
