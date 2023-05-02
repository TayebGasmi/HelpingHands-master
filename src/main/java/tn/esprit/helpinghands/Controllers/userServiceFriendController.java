package tn.esprit.helpinghands.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tn.esprit.helpinghands.entities.Notification;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.exception.FriendExist;
import tn.esprit.helpinghands.repositories.UserRepository;
import tn.esprit.helpinghands.security.UserPrincipal;
import tn.esprit.helpinghands.serviceImpl.IUserService;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
//import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class userServiceFriendController {
    @Autowired
    private IUserService userService;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/notifications")
    public List<Notification> findNotificationsByUser( @AuthenticationPrincipal User u) {
        return userService.findNotificationsByUser(u.getId());
    }
    @PostMapping("/notification/save/{username}")
    public Notification addNotification(@RequestBody Notification notification,@PathVariable(name="username") String username) {
        return userService.addNotification(notification, username);
    }
    @DeleteMapping("/notification/delete/{notificationId}")
    public void deleteNotification(@PathVariable(name="notificationId") Long notificationId) {
        userService.deleteNotification(notificationId);
    }

    @GetMapping("/notifications/all")
    public List<Notification> findAllNotifications() {
        return userService.findAllNotifications();
    }
    /////////////////////////////////////////
    @PostMapping("/friend/follow/{username2}")
    public void saveFriend(@PathVariable(value="username2") String username2, @AuthenticationPrincipal User u) throws FriendExist {
        userService.saveFriend(u.getUsername(), username2);
    }
    @DeleteMapping("/friend/unfollow/{username2}")
    public void deleteFriend(@PathVariable(value="username2") String username2, @AuthenticationPrincipal User u){
        userService.deleteFriend(u.getUsername(), username2);
    }



    @GetMapping("/friends")
    public List<User> getMyFriends( @ApiIgnore User u, @ApiIgnore @AuthenticationPrincipal UserPrincipal user){
        u = userService.findByUsername(user.getUsername()).orElse(null);
        return userService.getMyFriends(u);
    }
    @GetMapping("/friends2")
    public List<User> getMyFriends2(@RequestParam Long userId){
        User u = userRepository.findById(userId).orElse(null);
        return userService.getMyFriends(u);
    }

    @PutMapping("/notification/read")
    public void markNotifAsRead(@RequestBody Long idNotif) {
        userService.markNotifAsRead(idNotif);
    }

    @PutMapping("/notification/unread")
    public void markNotifAsUnRead(@RequestBody Long idNotif) {
        userService.markNotifAsUnRead(idNotif);
    }
    @GetMapping("/suggestions")
    public Set<User> getSuggestedUsers( @AuthenticationPrincipal User user) {
        User u = userService.getUser(user.getId());
        return userService.getSuggestedUsers(u);
    }

    @GetMapping("/suggestions2")
    public Set<User> getSuggestedUsers2( @AuthenticationPrincipal User user) {
        User u = userService.getUser(user.getId());
        return userService.getSuggestedUsers2(u);
    }

    @GetMapping("/common-friends")
    public List<User> FriendsInCommon( @AuthenticationPrincipal User user,@RequestParam Long userId2) {
        Long userId1 = user.getId();
        return userService.FriendsInCommon(userId1, userId2);
    }

}
