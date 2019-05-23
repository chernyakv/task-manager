package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class FilseServiceImpl implements FileService {

    @Value("${backend.server.url}api/v1/files")
    private String backendServerUrl;

    @Override
    public List<String> allFiles(String taskId, String projectId) {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(backendServerUrl + "?taskId=" + taskId + "&projectId=" + projectId, String[].class));
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(String filename, String taskId, String projectId) {
        String requestUri = backendServerUrl + "/" + filename + "?taskId=" + taskId + "&projectId=" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForEntity(requestUri, ByteArrayResource.class);
    }

    @Override
    public void uploadFile(MultipartFile file, String taskId, String projectId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition.builder("form-data")
                .name("file")
                .filename(file.getOriginalFilename())
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        System.out.println(contentDisposition);
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "?taskId=" + taskId + "&projectId=" + projectId, request, String.class).getBody();
    }
}
