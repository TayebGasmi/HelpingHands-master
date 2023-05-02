package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Notification;

import java.util.List;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification, Long> {
    public List<Notification> findByUserUsername(String username);

    @Query("select n from Notification n where n.user.id=:id ORDER BY n.createdAt DESC")
    List<Notification> userNotification(@Param("id") Long id);
}
