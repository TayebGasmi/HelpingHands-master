package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Friend;
import tn.esprit.helpinghands.entities.User;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsBySenderAndReceiver(User sender, User receiver);

    boolean existsByReceiverAndSender(User sender,User receiver);

    List<Friend> findBySender(User user);
    List<Friend> findByReceiver(User user);
}
