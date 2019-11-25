package com.foodmanager.server.repository;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.foodmanager.server.services.ImageHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Repository
public class S3Repository {
    private Logger logger = LoggerFactory.getLogger(S3Repository.class);
    @Autowired
    private AmazonS3 amazonS3;

    @Async
    public HttpStatus uploadImageToS3(byte[] bytes, String bucket, String folder, String id){
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bytes.length);
            logger.info(folder+"/"+id);
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, folder+"/"+id, new ByteArrayInputStream(bytes), metadata);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            return HttpStatus.OK;
        }
        catch (SdkClientException e) {
            e.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }
}
