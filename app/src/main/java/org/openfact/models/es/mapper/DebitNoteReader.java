package org.openfact.models.es.mapper;

import com.helger.ubl21.UBL21Reader;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.debitnote_21.DebitNoteType;
import org.openfact.models.FileModel;
import org.openfact.models.es.DocumentMapper;
import org.openfact.models.es.entity.DocumentEntity;
import org.openfact.models.es.entity.IndexedDocumentEntity;
import org.w3c.dom.Document;

import javax.ejb.Stateless;

@Stateless
@MapperType(value = "DebitNote")
public class DebitNoteReader implements DocumentMapper {

    @Override
    public DocumentEntity buildEntity(Document document, FileModel fileModel) {
        DebitNoteType debitNoteType = UBL21Reader.debitNote().read(document);
        IndexedDocumentEntity entity = new IndexedDocumentEntity();
        entity.setFileId(fileModel.getId());
        return entity;
    }

}
