package com.chernyak.fapi.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void uploadFile(MultipartFile file, String taskId, String projectId) throws IOException;
    ResponseEntity<ByteArrayResource> downloadFile(String filename, String taskId, String projectId);
    List<String> allFiles(String taskId, String projectId);
 }
