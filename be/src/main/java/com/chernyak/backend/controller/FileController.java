package com.chernyak.backend.controller;

import com.chernyak.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
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

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "projectId") String projectId, HttpServletRequest request)  throws IOException {

        try{
            fileService.saveFile(file, taskId, projectId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> files(@RequestParam(value = "taskId") String taskId,
                                              @RequestParam(value = "projectId") String projectId,
                                              HttpServletRequest request) {

        List<String> fileNames = fileService.allFiles(taskId, projectId);

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> file(@PathVariable String fileName, @RequestParam(value = "taskId") String taskId,
                                         @RequestParam(value = "projectId") String projectId, HttpServletRequest request) throws IOException {

        String uploadsDir = "./files/" + projectId + "/" + taskId + "/";
        Path path = Paths.get(uploadsDir  + fileName );
        byte[] data = Files.readAllBytes(path);
        path = Paths.get(uploadsDir);
        Resource resource = fileService.loadFileAsResource(fileName, path);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {

        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(new ByteArrayResource(data));
    }



}
