package tn.esprit.helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.Friend;
import tn.esprit.helpinghands.entities.Notification;
import tn.esprit.helpinghands.exception.FriendExist;
import tn.esprit.helpinghands.repositories.FriendRepository;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.exception.UserNotFoundException;
import tn.esprit.helpinghands.repositories.NotificationRepository;
import tn.esprit.helpinghands.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService,IUserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    NotificationRepository notificationRepository;
    /*@Autowired
    private PasswordTokenRepository passwordTokenRepository;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Environment env;*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);

    }
    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    @Override
    public void add(User s) {userRepository.save(s);
    }
    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public User update(User s) {return userRepository.save(s);}
    @Override
    public List<User> getAll() {return (List<User>) userRepository.findAll();
    }
    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("User not found with email " + email);
        }

    }
    /*@Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }*/
   /* @Override
    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?token=" + token;
        String message = messages.getMessage("message.resetPassword",
                null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }
    @Override
    public SimpleMailMessage constructEmail(String subject, String body,
                                            User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperties().getProperty("support.email"));
        return email;
    }
   @Override
   public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user) {
       String url = contextPath + "/user/changePassword?token=" + token;
       String message = "Reset your password";
       return constructEmail(message, url, user);
   }
    @Override
    public SimpleMailMessage constructEmail(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("support@helpinghands.com");
        return email;
    }*/
    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User customer = userRepository.findByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            userRepository.save(customer);
        } else {
            throw new UserNotFoundException();
        }
    }
    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
    @Override
    public void updatePassword(User customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        userRepository.save(customer);
    }
    /*@Override
    public List<User> allAdmins(){
        List<User> users = userRepository.findAll();
        List<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getRoles().getname().equals("ADMIN")) {
                result.add(u);
            }
        }
        return result;
    }*/



/////////////////////friends///////////////
    /*@Override
    @Transactional //Transactional is required when executing an update/delete query.
    public void makeAdmin(String username) {
        userRepository.makeAdmin(username);

    }*/
    @Override
    public void saveFriend(String username1, String username2) throws FriendExist {


        Friend friend = new Friend();
        User user1 = userRepository.findByUsername(username1).orElse(null);
        User user2 = userRepository.findByUsername(username2).orElse(null);
        User firstuser = user1;
        User seconduser = user2;
        if( !(friendRepository.existsBySenderAndReceiver(firstuser,seconduser)) && (user1.getId() != user2.getId()) && (user2 != null) ){
            friend.setCreatedAt(new Date());
            friend.setSender(firstuser);
            friend.setReceiver(seconduser);
            friendRepository.save(friend);
            Notification notif = new Notification();
            notif.setCreatedAt(new Date());
            notif.setMessage(firstuser.getUsername() +  " Started following you !");
            notif.setRead(false);
            notif.setUser(seconduser);
            notificationRepository.save(notif);
        }
        else {
            throw new FriendExist("Error processing friend request !");
        }
    }
    @Override
    public void deleteFriend(String username1, String username2){
        User user1 = userRepository.findByUsername(username1).orElse(null);
        User user2 = userRepository.findByUsername(username2).orElse(null);
        Friend friend = new Friend();
        List<Friend> myFriends = getMyFriends2(user1);
        for (Friend f : myFriends) {
            if (f.getSender().getId() == user1.getId() && (f.getReceiver().getId() == user2.getId()) ) {
                friend = f;
            }
        }
        friendRepository.delete(friend);
    }
    @Override
    public List<User> getMyFriends(User u){
        List<Friend> allFriends = friendRepository.findAll();
        Set<User> myFriends = new HashSet<>();
        for (Friend f : allFriends) {
            if (f.getSender().getId() == u.getId() ) {
                myFriends.add(f.getReceiver());
            }
        }
        List<User> friends = new ArrayList<>(myFriends);
        return friends;
    }


    public List<Friend> getMyFriends2(User u){
        List<Friend> allFriends = friendRepository.findAll();
        List<Friend> myFriends = new ArrayList<>();
        for (Friend f : allFriends) {
            if (f.getSender().getId() == u.getId() ) {
                myFriends.add(f);
            }
        }
        return myFriends;
    }
    @Override
    public void markNotifAsRead(Long idNotif) {
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        notification.setRead(true);
        notificationRepository.save(notification);

    }
    @Override
    public void markNotifAsUnRead(Long idNotif) {
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        notification.setRead(false);
        notificationRepository.save(notification);

    }
    @Override
    public Set<User> getSuggestedUsers(User u) {
        List<Friend> allFriends = friendRepository.findAll();
        List<User> myFriends = getMyFriends(u);
        Set<User> suggestedFriends = new HashSet<>();


        for (Friend f : allFriends) {
            for (User myFriend : myFriends) {
                if ( (f.getSender().getId() == myFriend.getId()) && (f.getReceiver().getId() != u.getId()) && (!(myFriends.contains(f.getReceiver()))) ) {
                    suggestedFriends.add(f.getReceiver());
                }
            }
        }

        return suggestedFriends;
    }
    @Override
    public Set<User> getSuggestedUsers2(User u) {
        List<Friend> allFriends = friendRepository.findAll();
        List<User> myFriends = getMyFriends(u);
        Set<User> suggestedFriends = new HashSet<>();
        int count = 0;


        for (Friend f : allFriends) {
            for (User myFriend : myFriends) {
                if ( (f.getSender().getId() == myFriend.getId()) && (f.getReceiver().getId() != u.getId()) && (!(myFriends.contains(f.getReceiver()))) && (count != 8)) {
                    suggestedFriends.add(f.getReceiver());
                    count++;
                }
            }
        }

        return suggestedFriends;
    }
    @Override
    public List<User> FriendsInCommon(Long userId1, Long userId2) {
        User u1 = userRepository.findById(userId1).orElse(null);
        User u2 = userRepository.findById(userId1).orElse(null);
        List<Friend> myFriendList = getMyFriends2(u1);
        Set<User> FriendsInCommon = new HashSet<>();

        for (Friend f : myFriendList) {
            List<Friend> externalList = getMyFriends2(f.getReceiver());

            for (Friend f2: externalList) {
                System.err.println("Sender:" + f2.getSender().getId() + "----Receiver:" +f2.getReceiver().getId()+"\n------------------\n" );
                if (f2.getReceiver().getId() == userId2) {
                    FriendsInCommon.add(f2.getSender());
                }
            }
        }
        List<User> commonFriends = new ArrayList<>(FriendsInCommon);
        return commonFriends;
    }

    ////////////////notification//////////////
    @Override
    public List<Notification> findNotificationsByUser(Long userId) {
        return notificationRepository.userNotification(userId);
    }

    @Override
    public Notification addNotification(Notification notification, String username) {
        User user = userRepository.findByUsername(username).get();
        notification.setRead(false);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notif = notificationRepository.findById(notificationId).orElse(null);
        notificationRepository.delete(notif);

    }

    @Override
    public List<Notification> findAllNotifications() {
        // TODO Auto-generated method stub
        return notificationRepository.findAll();
    }


}
