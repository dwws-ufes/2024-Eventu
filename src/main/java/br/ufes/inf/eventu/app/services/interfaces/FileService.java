package br.ufes.inf.eventu.app.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;

public interface FileService extends GenericService<File> {
    public String uploadFile(MultipartFile file, Long attractionId, AttachmentType attachmentType) throws Exception;
}
