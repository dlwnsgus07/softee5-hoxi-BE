package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.exception.InternalServerException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class S3RepositoryImpl implements S3Repository {
    private final AmazonS3 s3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile multipartFiles) {
        String fileName = generateFileName();
        try {
            log.info("[S3 File Upload] 시작 {}", fileName);
            s3Client.putObject(bucket, fileName, multipartFiles.getInputStream(), getObjectMetadata(multipartFiles));
            log.info("[S3 File Upload] 완료 {}", fileName);
            return fileName;
        } catch (SdkClientException | IOException e) {
            log.error("[S3 File Upload 실패]", e);
            throw new InternalServerException("[S3 File Upload 실패]", e);
        }
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFiles) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFiles.getContentType());
        objectMetadata.setContentLength(multipartFiles.getSize());
        return objectMetadata;
    }

    private String generateFileName() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss").format(LocalDateTime.now())
                + "-" + UUID.randomUUID();
    }

    @Override
    public void delete(String fileName) {
        try {
            s3Client.deleteObject(bucket, fileName);
            log.info("[S3 file delete {}]", fileName);
        } catch (SdkClientException e) {
            log.error("[S3 file delete error]");
            throw new InternalServerException("[S3 file delete 실패]", e);
        }
    }
}
