package com.example.entityapi.controller;

import java.io.IOException;
import java.net.URLConnection;

import com.example.entityapi.service.CloudFileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    private final CloudFileStorageService storageService;

    @Autowired
    public FileUploadController(CloudFileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) {

        var data = storageService.downloadFile(filename);
        // Set headers
        final HttpHeaders headers = new HttpHeaders();        
        headers.setContentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(filename)));

        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }

    @PostMapping("/files/")
    public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        storageService.uploadFile(file.getOriginalFilename(), file.getBytes(), file.getSize());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}