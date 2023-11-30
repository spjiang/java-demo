package com.spjiang.fastfds.util;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class FdfsTools {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    /**
     * 文件上传, byte 流类型
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(
                byteArrayInputStream,
                fileSize,
                extension,
                null);
        return storePath.getFullPath();
    }

    /**
     * MultipartFile类型的文件上传ַ
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(
                file.getInputStream(),
                file.getSize(),
                FileNameUtil.getSuffix(file.getOriginalFilename()),
                null);
        return storePath.getFullPath();
    }

    /**
     * 普通文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadFile(
                inputStream,
                file.length(),
                FileNameUtil.getSuffix(file),
                null);
        return path.getFullPath();
    }


    public String createFileAndUpload(String sourceFilePath) throws Exception {
        File file = new File(sourceFilePath);
        return uploadFile(file);
    }

    /**
     * 带输入流形式的文件上传
     *
     * @param inputStream
     * @param size
     * @param fileName
     * @return
     */
    public String uploadFile(InputStream inputStream, long size, String fileName) {
        StorePath path = fastFileStorageClient.uploadFile(inputStream, size, fileName, null);
        return path.getFullPath();
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath path = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return path.getFullPath();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     */
    public byte[] downloadFile(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        log.info("fdfs下载文件的group:{}",group);
        log.info("fdfs下载文件的path:{}",path);
        return fastFileStorageClient.downloadFile(group, path, downloadByteArray);
    }

    public void deleteFile(String fileUrl) {
        if (StrUtil.isBlank(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
