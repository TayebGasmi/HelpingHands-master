package tn.esprit.helpinghands.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import tn.esprit.helpinghands.entities.Notification;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.exception.FriendExist;
import tn.esprit.helpinghands.exception.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public interface IUserService {
    Optional<User> findByUsername(String username);

    void add(User s);

    User getUser(Long userId);

    User update(User s);

    List<User> getAll();

    User getById(long id);

    void remove(long id);


    User findByEmail(String email);

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
    void updateResetPasswordToken(String token, String email) throws UserNotFoundException;

    User getByResetPasswordToken(String token);

    void updatePassword(User customer, String newPassword);

    /////////////////////friends///////////////
    /*@Transactional
    //Transactional is required when executing an update/delete query.
    void makeAdmin(String username);*/

    void saveFriend(String username1, String username2) throws FriendExist;

    void deleteFriend(String username1, String username2);

    List<User> getMyFriends(User u);

    void markNotifAsRead(Long idNotif);

    void markNotifAsUnRead(Long idNotif);

    Set<User> getSuggestedUsers(User u);

    Set<User> getSuggestedUsers2(User u);

    List<User> FriendsInCommon(Long userId1, Long userId2);

    ////////////////notification//////////////
    List<Notification> findNotificationsByUser(Long userId);

    Notification addNotification(Notification notification, String username);

    void deleteNotification(Long notificationId);

    List<Notification> findAllNotifications();

    /*void createPasswordResetTokenForUser(User user, String token);

    SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user);

    SimpleMailMessage constructEmail(String subject, String body,
                                     User user);*/
}
