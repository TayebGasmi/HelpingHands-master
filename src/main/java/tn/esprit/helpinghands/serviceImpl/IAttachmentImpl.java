package tn.esprit.helpinghands.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Attachment;
import tn.esprit.helpinghands.entities.Reclamation;
import tn.esprit.helpinghands.repositories.AttachmentRepository;

import java.io.IOException;

@Service
@Slf4j
public class IAttachmentImpl implements IAttachment {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public Attachment addAttachmentToReclamation(MultipartFile file)  {
        try {
            Attachment attachment = new Attachment();
            attachment.setData(file.getBytes());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            log.info("Attachment created");
            return attachmentRepository.save(attachment);
        }catch (IOException e){
            log.error("Error during attachment creation", e);
            return null;
        }
    }
}
