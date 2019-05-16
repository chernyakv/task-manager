package com.chernyak.fapi.controller;

import com.chernyak.fapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

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
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "projectId") String projectId){
        //String fileName = fileStorageService.storeFile(file);
        fileService.saveFile(file, taskId, projectId);

        //files.add(file.getOriginalFilename());

       // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //        .path("/downloadFile/")
         //       .path(fileName)
          //      .toUriString();

        return  null;
    }

    @GetMapping
    public ResponseEntity<List<String>> getFiles(
            @RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "projectId") String projectId){
        //String fileName = fileStorageService.storeFile(file);


        //files.add(file.getOriginalFilename());

        // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //        .path("/downloadFile/")
        //       .path(fileName)
        //      .toUriString();

        return  new ResponseEntity<>(fileService.allFiles(taskId, projectId), HttpStatus.OK);
    }

}
