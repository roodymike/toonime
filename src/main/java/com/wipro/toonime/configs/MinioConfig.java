package com.wipro.toonime.configs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import io.minio.MinioClient;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    String accessKey = "admin";

    String accessSecret = "password";

    String minioUrl = "http://127.0.0.1:9000";

    @Bean
    public MinioClient generateMinioClient(){
        try{
            MinioClient client = new MinioClient( minioUrl, accessKey, accessSecret);
            return client;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
