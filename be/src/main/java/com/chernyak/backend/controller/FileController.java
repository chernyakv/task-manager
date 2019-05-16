package com.chernyak.backend.controller;

import com.chernyak.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "projectId") String projectId, HttpServletRequest request)  throws IOException {
        //String fileName = fileStorageService.storeFile(file);
        fileService.saveFile(file, taskId, projectId);
        return null;
    }

    @GetMapping
    public ResponseEntity<List<String>> files( @RequestParam(value = "taskId") String taskId,
                                               @RequestParam(value = "projectId") String projectId,
                                               HttpServletRequest request) {
        List<String> fileNames = fileService.allFiles(taskId, projectId);


        final List<String> uriComponents = fileNames.stream()
                .map(fileName -> MvcUriComponentsBuilder.fromMethodName(FileController.class, "file", fileName, taskId, projectId, request).build())
                .map(UriComponents::toString)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(uriComponents);
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> file(@PathVariable String fileName, @RequestParam(value = "taskId") String taskId,
                                         @RequestParam(value = "projectId") String projectId, HttpServletRequest request) {

        String uploadsDir = "./files/" + projectId + "/" + taskId + "/";
        Path path = Paths.get(uploadsDir);

        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName, path);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {

        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



}
