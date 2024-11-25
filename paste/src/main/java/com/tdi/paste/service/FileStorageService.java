package com.tdi.paste.service;

import com.tdi.paste.api.request.CreatePasteRequest;
import com.tdi.paste.service.api.TemporaryStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class FileStorageService implements TemporaryStorageService {
    private static final String ERROR_WRITING_FILE_TO_TEMP_DIR_MESSAGE = "Error writing file to storage.";
    private static final String TEMP_DIR_CREATION_FAILED_MESSAGE = "Could not create directory for pastes.";
    private static final String FILE_NAME_TEMPLATE = "%s.txt";
    private static final String TEMP_PASTES_DIR = "./paste/temporary_pastes";

    @Override
    public File saveToDirectory(CreatePasteRequest request) {
        File directory = new File(TEMP_PASTES_DIR);

        createDirectory(directory);

        String fileName = String.format(FILE_NAME_TEMPLATE, UUID.randomUUID());
        File file = new File(directory, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(request.getText());
            log.info("Successfully saved paste to temporary file: {}", file.getAbsolutePath());
        } catch (IOException ex) {
            deleteFile(file);
            log.error("An error occurred while writing to the file: {}. Error: {}", file.getAbsolutePath(), ex.getMessage(), ex);
            throw new RuntimeException(ERROR_WRITING_FILE_TO_TEMP_DIR_MESSAGE, ex);
        }

        return file;
    }

    @Override
    public void deleteFile(File file) {
        if (file.exists() && !file.delete()) {
            log.error("Could not delete temporary file: {}", file.getAbsolutePath());
        } else {
            log.info("Temporary file deleted: {}", file.getAbsolutePath());
        }
    }

    private void createDirectory(File directory) {
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                log.error("Could not create temporary directory for pastes at path: {}", directory.getAbsolutePath());
                throw new RuntimeException(TEMP_DIR_CREATION_FAILED_MESSAGE);
            }
            log.info("Created temporary directory for pastes at path: {}", directory.getAbsolutePath());
        }
    }
}