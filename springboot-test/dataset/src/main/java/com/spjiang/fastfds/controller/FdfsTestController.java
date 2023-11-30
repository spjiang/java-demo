package com.spjiang.fastfds.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.spjiang.fastfds.util.FdfsTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Component(value = "FdfsTestController")
public class FdfsTestController {

    @Autowired
    private FdfsTools fdfsTools;

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }

    /**
     * 下载fastdfs里的文件
     * http://localhost:8621/mybatis/download?fullPath=group1/M00/64/AF/CgEF1GSSpkGACiJlAAvqH-Poukk892.jpg
     *
     * @param fullPath
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(@RequestParam String fullPath, HttpServletResponse response) throws IOException {
        byte[] bytes = fdfsTools.downloadFile(fullPath);
        String name = FileNameUtil.getName(fullPath);
        System.out.println(name);
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(name.getBytes(), StandardCharsets.ISO_8859_1));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ServletOutputStream ops = response.getOutputStream();
        IoUtil.copy(inputStream, ops);
        IoUtil.closeIfPosible(inputStream);
        IoUtil.closeIfPosible(ops);
    }


    /**
     * 前端单文件上传 form-data形式参数， 传1个 MultipartFile类型，参数名file
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadOne")
    public String upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getOriginalFilename());
        String s = fdfsTools.uploadFile(multipartFile);
        return "fullPath=" + s;
    }

    /**
     * 前端多文件上传  form-data形式参数 传多个MultipartFile类型,参数名随意
     *
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadBatch")
    public List<String> uploadBatch(MultipartHttpServletRequest request) throws IOException {
        List<String> result = new ArrayList<>();
        Map<String, MultipartFile> fileMap = request.getFileMap();
        for (MultipartFile multipartFile : fileMap.values()) {
            if (!multipartFile.isEmpty()) {
                System.err.println(multipartFile.getOriginalFilename());
                String s = fdfsTools.uploadFile(multipartFile);
                result.add("fullPath=" + s);
            }
        }
        return result;
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @return fullPath=group1/M00/64/AF/CgEF1GSSqdGAHrO4AAAAD0mMR3c910.txt
     */
    @PostMapping("/saveTxtToFdfs")
    public String saveTxtToFdfs(@RequestParam String content) {
        String s = fdfsTools.uploadFile(content, "txt");
        return "fullPath=" + s;
    }

    /**
     * 删除FastDfs上的文件
     * http://localhost:8621/mybatis/delFile?fullPath=group1/M00/64/AF/CgEF1GSSpnKAY3CiAAvea9v85c0615.jpg
     *
     * @param fullPath
     * @return
     */
    @DeleteMapping("/delFile")
    public String deleteFastDfsFile(@RequestParam String fullPath) {
        fdfsTools.deleteFile(fullPath);
        return "ok!";
    }

}
