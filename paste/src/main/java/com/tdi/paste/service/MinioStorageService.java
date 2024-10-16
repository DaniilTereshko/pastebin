package com.tdi.paste.service;

import com.tdi.paste.config.properties.S3Config;
import com.tdi.paste.service.api.StorageService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
@Service
@AllArgsConstructor
public class MinioStorageService implements StorageService {

    public static final String FAILED_PASTE_UPLOAD_MESSAGE = "Failed to upload paste";
    public static final String PASTE_CONTENT_TYPE = "text/plain";
    public static final int PART_SIZE = -1;
    private final S3Config config;
    private final MinioClient minioClient;

    @Override
    public void uploadPaste(File paste) {
        var bucketName = config.getBucket();

        try {
            if (!isBucketExist(bucketName)) {
                createBucket(bucketName);
            }
            putObject(paste, PASTE_CONTENT_TYPE);
            log.info("File '{}' uploaded successfully to bucket '{}'.", paste.getName(), bucketName);
        } catch (Exception ex) {
            log.error("Failed to upload paste '{}'. Error: {}", paste.getName(), ex.getMessage(), ex);
            throw new RuntimeException(FAILED_PASTE_UPLOAD_MESSAGE, ex);
        }
    }

    private void putObject(File file, String contentType) throws Exception {
        try (var fis = new FileInputStream(file)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(fis, fis.available(), PART_SIZE)
                    .bucket(config.getBucket())
                    .object(file.getName())
                    .contentType(contentType)
                    .build());
            log.info("File '{}' sent to storage.", file.getName());
        } catch (Exception ex) {
            log.error("Error reading file '{}'. Error: {}", file.getName(), ex.getMessage(), ex);
            throw ex;
        }
    }

    private boolean isBucketExist(String bucketName) throws Exception {
        var exists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        log.info("Bucket '{}' existence check: {}", bucketName, exists);
        return exists;
    }

    private void createBucket(String bucketName) throws Exception {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            log.info("Bucket '{}' created successfully.", bucketName);
        } catch (Exception ex) {
            log.error("Failed to create bucket '{}'. Error: {}", bucketName, ex.getMessage(), ex);
            throw ex;
        }
    }
}