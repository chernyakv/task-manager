package com.chernyak.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileService {
    void saveFile(MultipartFile file, String taskId, String projectId) throws IOException;
    List<String> allFiles(String taskId, String projectId);
    void delete(String filename, String taskId, String projectId) throws IOException;
    public Resource loadFileAsResource(String fileName, Path path);
}
