package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long>{
}
