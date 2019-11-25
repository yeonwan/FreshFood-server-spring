package com.foodmanager.server.controller;

import com.foodmanager.server.services.ImageHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageUploadController {
    @Autowired
    private ImageHandleService imageHandleService;

    @PostMapping("images/registerImage/{folder}/{UserId}")
    public ResponseEntity<?> registerImage(@PathVariable long UserId, @PathVariable String folder, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return imageHandleService.uploadImageToS3(UserId, multipartFile, folder);
    }
}
