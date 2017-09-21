package org.openfact.models.db.jpa;

import org.openfact.models.FileModel;
import org.openfact.models.StorageException;
import org.openfact.models.db.JpaModel;
import org.openfact.models.db.jpa.entity.FileEntity;

import javax.persistence.EntityManager;

public class FileAdapter implements FileModel, JpaModel<FileEntity> {

    private final FileEntity file;
    private final EntityManager em;

    public FileAdapter(EntityManager em, FileEntity entity) {
        this.file = entity;
        this.em = em;
    }

    public static FileEntity toEntity(FileModel model, EntityManager em) {
        if (model instanceof FileAdapter) {
            return ((FileAdapter) model).getEntity();
        }
        return em.getReference(FileEntity.class, model.getId());
    }

    @Override
    public FileEntity getEntity() {
        return file;
    }

    @Override
    public String getId() {
        return file.getId();
    }

    @Override
    public String getFilename() {
        return file.getFilename();
    }

    @Override
    public String getExtension() {
        return file.getFileExtension();
    }

    @Override
    public byte[] getFile() throws StorageException {
        return file.getFile();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileModel)) {
            return false;
        }
        FileModel other = (FileModel) obj;
        if (getId() != null) {
            if (!getId().equals(other.getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

}
