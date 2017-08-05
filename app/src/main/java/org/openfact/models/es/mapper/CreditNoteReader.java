package org.openfact.models.es.mapper;

import com.helger.ubl21.UBL21Reader;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;
import org.openfact.models.FileModel;
import org.openfact.models.es.DocumentMapper;
import org.openfact.models.es.entity.DocumentEntity;
import org.openfact.models.es.entity.IndexedDocumentEntity;
import org.w3c.dom.Document;

import javax.ejb.Stateless;

@Stateless
@MapperType(value = "CreditNote")
public class CreditNoteReader implements DocumentMapper {

    @Override
    public DocumentEntity buildEntity(Document document, FileModel fileModel) {
        CreditNoteType creditNoteType = UBL21Reader.creditNote().read(document);
        IndexedDocumentEntity entity = new IndexedDocumentEntity();
        entity.setFileId(fileModel.getId());
        return entity;
    }

}
