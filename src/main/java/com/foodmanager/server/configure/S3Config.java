package com.foodmanager.server.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@ConfigurationProperties(prefix="spring.aws")
public class S3Config{
    private Logger logger = LoggerFactory.getLogger(S3Config.class);
    private String accessKey;
    private String secretKey;
    private String region;
    public S3Config(){
        accessKey = System.getenv("AWS_ACCESS_KEY_ID");
        secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
        region = "ap-northeast-2";
    }

    @Bean
    public AmazonS3 amazonS3Client(){
        logger.info("Building AmazonS3 Connection");
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.fromName(region))
                .build();
    }
}
