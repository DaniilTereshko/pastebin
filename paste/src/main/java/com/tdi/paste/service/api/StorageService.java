package com.tdi.paste.service.api;

import java.io.File;
import java.io.IOException;

/**
 * A service designed for interaction with a paste storage. The implementation is assumed to be S3.
 */
public interface StorageService {
    /**
     * Uploads a paste file to the permanent storage.
     *
     * @param paste the temporarily stored paste file on the server to be uploaded.
     */
    void uploadPaste(File paste);

    String getPaste(String bucket, String object);
}