package br.ufes.inf.eventu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import br.ufes.inf.eventu.app.services.interfaces.FileService;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("attractionId") Long attractionId,
            @RequestParam("attachmentType") AttachmentType attachmentType) throws Exception {

        String savedFilename = fileService.uploadFile(file, attractionId, attachmentType);
        return "File uploaded successfully: " + savedFilename;
    }
}
