package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Attachment;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
