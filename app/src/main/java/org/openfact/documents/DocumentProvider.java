package org.openfact.documents;

import org.openfact.files.ModelParseException;
import org.openfact.files.XmlUBLFileModel;

import java.util.List;

public interface DocumentProvider {

    /**
     * @param file that contains xml file to be persisted
     */
    DocumentModel addDocument(XmlUBLFileModel file) throws ModelUnsupportedTypeException, ModelParseException;

    /**
     * @param id unique identity generated by the system
     * @return document
     */
    DocumentModel getDocument(String id);

    /**
     * @param document document to be removed
     * @return true if document was removed
     */
    boolean removeDocument(DocumentModel document);

    /**
     * @return list of documents
     */
    List<DocumentModel> getDocuments();

}