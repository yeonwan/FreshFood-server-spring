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

    @PostMapping("images/registerImage/{folder}/{UserId}") //folder = foods 입력해주고 UserId 입력해주면 S3에 알맞게 저장
    public ResponseEntity<?> registerImage(@PathVariable long UserId, @PathVariable String folder,
                                           @RequestParam("file") MultipartFile multipartFile, @RequestParam("fileName") String fileName) throws IOException {
        return imageHandleService.uploadImageToS3(UserId, multipartFile, folder, fileName);
    }
}
