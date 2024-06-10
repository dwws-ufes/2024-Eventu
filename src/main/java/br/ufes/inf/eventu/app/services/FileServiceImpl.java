package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.File;
import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.persistence.AttractionTypeDAO;
import br.ufes.inf.eventu.app.persistence.FileDAO;
import br.ufes.inf.eventu.app.services.interfaces.FileService;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Autowired
    private FileDAO fileDAO;

    @Autowired
    private AttractionDAO attractionDAO;

    public String uploadFile(MultipartFile file, Long attractionId, AttachmentType attachmentType) throws Exception {
        try {
            // Generate a unique filename
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // Create a path for the file to be stored
            Path filePath = Paths.get(uploadDirectory, filename);

            // Store the file
            Files.copy(file.getInputStream(), filePath);

            var attraction = attractionDAO.findById(attractionId).get();

            File model = new File();
            model.setName(filename);
            model.setPath(filePath.toString());
            model.setMimetype(file.getContentType().toString());
            model.setAttraction(attraction);
            model.setAttachmentType(attachmentType);
            this.save(model);

            return filename; // You can return the filename for later reference
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: ", e);
        }
    }

    @Override
    public File save(File model) throws Exception {
        return fileDAO.save(model);
    }

    @Override
    public File edit(long id, File model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }

}
