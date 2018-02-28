package org.clarksnut.models.jpa;

import org.clarksnut.models.DocumentModel;
import org.clarksnut.models.PartyModel;
import org.clarksnut.models.PartyProvider;
import org.clarksnut.models.jpa.entity.DocumentEntity;
import org.clarksnut.models.jpa.entity.PartyEntity;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Stateless
public class JpaPartyObservers {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private PartyProvider partyProvider;

    public void index(@Observes DocumentModel.DocumentCreationEvent documentCreationEvent) {
        DocumentModel document = documentCreationEvent.getCreatedDocument();
        DocumentEntity documentEntity = DocumentAdapter.toEntity(document, em);


        processParty(documentEntity.getSupplierAssignedId(), documentEntity.getSupplierAssignedId(), documentEntity.getSupplierName());
        if (documentEntity.getCustomerAssignedId() != null) {
            processParty(documentEntity.getCustomerAssignedId(), documentEntity.getCustomerAssignedId(), documentEntity.getCustomerName());
        }
    }

    private void processParty(String assignedId, String supplierCustomerAssignedId, String partyName) {
        PartyModel party = partyProvider.getPartyByAssignedId(assignedId, supplierCustomerAssignedId);
        if (party != null) {
            PartyEntity entity = IndexedPartyAdapter.toEntity(party, em);

            Set<String> newPartyName = new HashSet<>(Arrays.asList(partyName.split(" ")));
            if (!entity.getPartyNames().containsAll(newPartyName)) {
                newPartyName.addAll(entity.getPartyNames());
                entity.setPartyNames(newPartyName);
                em.merge(entity);
            }
        } else {
            PartyEntity entity = new PartyEntity();
            entity.setId(UUID.randomUUID().toString());
            entity.setAssignedId(assignedId);
            entity.setName(partyName);
            entity.setPartyNames(new HashSet<>(Arrays.asList(partyName.split(" "))));
            entity.setSupplierCustomerAssignedId(supplierCustomerAssignedId);
            em.persist(entity);
        }
    }

}