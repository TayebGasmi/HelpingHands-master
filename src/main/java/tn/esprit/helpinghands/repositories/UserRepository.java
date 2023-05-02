package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Role;
import tn.esprit.helpinghands.entities.User;
//import tn.esprit.kaddemproject.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT c FROM User c WHERE c.email = ?1")
     User findByEmail(String email);

     User findByResetPasswordToken(String token);

    /*@Modifying
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

    @Modifying
    @Query("update User set role = 'ADMIN' where username = :username")
    void makeAdmin(@Param("username") String username);*/

}
