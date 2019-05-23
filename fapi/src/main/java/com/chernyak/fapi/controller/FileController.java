package com.chernyak.fapi.controller;

import com.chernyak.fapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFiles(@RequestParam(value = "file") MultipartFile file,
                                         @RequestParam(value = "taskId") String taskId,
                                         @RequestParam(value = "projectId") String projectId) {

        try {
            fileService.uploadFile(file, taskId, projectId);
        }
        catch (IOException e){
            return new ResponseEntity<>("Error while reading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> getFilesInfo(@RequestParam(value = "taskId") String taskId,
                                                 @RequestParam(value = "projectId") String projectId,
                                                 HttpServletRequest request){

        List<String> files = fileService.allFiles(taskId, projectId);

        return  new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
                                                 @RequestParam(value = "taskId") String taskId,
                                                 @RequestParam(value = "projectId") String projectId,
                                                 HttpServletRequest request) {

        ResponseEntity<ByteArrayResource> response = fileService.downloadFile(fileName, taskId, projectId);
        HttpHeaders headers = response.getHeaders();
        String contentDisposition = headers.getContentDisposition().toString();
        MediaType contentType = headers.getContentType();
        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(response.getBody());
    }
}
