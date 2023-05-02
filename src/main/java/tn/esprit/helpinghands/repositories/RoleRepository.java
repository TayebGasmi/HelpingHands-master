package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.ERole;
import tn.esprit.helpinghands.entities.Role;
//import tn.esprit.kaddemproject.entities.ERole;
//import tn.esprit.kaddemproject.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
