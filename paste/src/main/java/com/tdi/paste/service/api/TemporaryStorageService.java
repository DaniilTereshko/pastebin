package com.tdi.paste.service.api;

import com.tdi.paste.api.request.CreatePasteRequest;

import java.io.File;

/**
 * A service designed for temporarily saving pastes in text files.
 */
public interface TemporaryStorageService {

    /**
     * Accepts a request to create a paste and saves the paste as a temporary text file
     * based on the provided data to temporary directory. This temporary text file is then used by the
     * {@link StorageService} to save the paste to permanent storage.
     *
     * @param request the {@link CreatePasteRequest} containing the data for the paste.
     * @return the temporarily saved {@link File}.
     */
    File saveToDirectory(CreatePasteRequest request);

    /**
     * A method for deleting a temporary file. It should be used after
     * the temporary file has been saved to permanent storage.
     * This method is called either after the successful execution of
     * {@link StorageService#uploadPaste(File)} or if an error occurs
     * during the paste-saving process, in which case it is necessary
     * to undo the work already done by {@link TemporaryStorageService#saveToDirectory(CreatePasteRequest)}.
     *
     * @param file the temporarily saved {@link File}.
     */
    void deleteFile(File file);
}