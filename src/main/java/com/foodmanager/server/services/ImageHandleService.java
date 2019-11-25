package com.foodmanager.server.services;

import com.foodmanager.server.repository.S3Repository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Slf4j
public class ImageHandleService {
    private Logger logger = LoggerFactory.getLogger(ImageHandleService.class);
    @Autowired
    private S3Repository s3Repository;

    public ResponseEntity<?> uploadImageToS3(long UserId, MultipartFile image, String folder) throws IOException {
        String uploadFileName= image.getOriginalFilename();
        logger.info(uploadFileName);
        assert uploadFileName != null;
        byte[] bytes = image.getBytes();
        String bucket = "fm-foodpicturebucket";
        if(folder.equals("foods")) folder += ("/"+UserId);
        return new ResponseEntity<>(s3Repository.uploadImageToS3(bytes, bucket, folder, uploadFileName));
    }
}
