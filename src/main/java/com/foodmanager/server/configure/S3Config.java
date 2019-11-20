package com.foodmanager.server.configure;

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
    private String accessKey;
    private String secretKey;
    private String region;
    public S3Config(){
        accessKey = "AKIAXXPRRAWGDRB7YJNE";
        secretKey = "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";
        region = "ap-northeast-2";
    }
    @Bean
    public AmazonS3 amazonS3Client(){
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.fromName(region))
                .build();
    }

    public String getRegion() {
        return region;
    }
}
