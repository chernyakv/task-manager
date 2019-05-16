package com.chernyak.fapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void saveFile(MultipartFile file, String taskId, String projectId);
    List<String> allFiles(String taskId, String projectId);
 }
