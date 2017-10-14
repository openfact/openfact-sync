package org.openfact.files;

import org.openfact.files.exceptions.FileFetchException;

public interface FileModel {

    String getId();

    /**
     * @return filename including extension e.g. file.pdf
     */
    String getFilename();

    /**
     * @return file extension e.g. .pdf
     */
    String getExtension();

    /**
     * @return file value on bytes
     */
    byte[] getFile() throws FileFetchException;
}
