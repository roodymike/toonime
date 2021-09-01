package com.wipro.toonime.controllers;

import com.wipro.toonime.models.Video;
import com.wipro.toonime.repos.VideoRepo;
import com.wipro.toonime.services.FileOpsMinio;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MinioAdapter {
    @Autowired
    FileOpsMinio minIOService;
    VideoRepo videoDir;

    @GetMapping(path = "/buckets")
    public List<Bucket> listBuckets(){
        return minIOService.getAllBuckets();
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        //System.out.println(files.getOriginalFilename());
        minIOService.uploadFile(files.getOriginalFilename(), files.getBytes());
        return new ResponseEntity<>("FileName :- " +files.getOriginalFilename() + " of size "+files.getSize() + "B uploaded.", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
        byte[] data = minIOService.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);

    }
}
