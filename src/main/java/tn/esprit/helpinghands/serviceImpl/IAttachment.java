package tn.esprit.helpinghands.serviceImpl;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Attachment;
import tn.esprit.helpinghands.entities.Reclamation;

public interface IAttachment {
Attachment addAttachmentToReclamation(MultipartFile file);
}
