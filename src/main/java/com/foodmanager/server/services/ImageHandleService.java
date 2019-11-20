package com.foodmanager.server.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.foodmanager.server.configure.S3Config;
import com.foodmanager.server.repository.S3Repository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        return new ResponseEntity<>(s3Repository.uploadImageToS3(bytes, bucket, folder+"/"+UserId, uploadFileName));
    }
}