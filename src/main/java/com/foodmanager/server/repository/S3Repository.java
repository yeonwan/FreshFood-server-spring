package com.foodmanager.server.repository;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.foodmanager.server.services.ImageHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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

    @Async
    public  HttpStatus deleteImageFromS3(String bucket, String folder, String fileName){
        DeleteObjectRequest deleteObjectRequest =
                new DeleteObjectRequest(bucket, folder +"/" + fileName);
        amazonS3.deleteObject(deleteObjectRequest);
        return  HttpStatus.OK;
    }

    @Async
    public  HttpStatus deleteImageFolderFromS3(String bucket, String folder){
        ObjectListing objectList = amazonS3.listObjects( bucket, folder );
        List<S3ObjectSummary> objectSummeryList =  objectList.getObjectSummaries();
        String[] keysList = new String[ objectSummeryList.size() ];
        int count = 0;
        for( S3ObjectSummary summery : objectSummeryList ) {
            keysList[count++] = summery.getKey();
        }
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest( bucket).withKeys( keysList );
        amazonS3.deleteObjects(deleteObjectsRequest);
        return  HttpStatus.OK;
    }
}
