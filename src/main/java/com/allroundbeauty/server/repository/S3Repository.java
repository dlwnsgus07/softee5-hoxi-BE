package com.allroundbeauty.server.repository;

import org.springframework.web.multipart.MultipartFile;

public interface S3Repository {
    String upload(MultipartFile multipartFiles);

    void delete(String fileName);
}
