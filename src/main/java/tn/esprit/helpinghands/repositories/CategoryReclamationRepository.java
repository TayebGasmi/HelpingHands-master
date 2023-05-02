package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.CategoryReclamation;

@Repository
public interface CategoryReclamationRepository extends JpaRepository<CategoryReclamation, Long>{
}
