package com.cursosdedesarrollo.springbootrestjpamysql.onetoone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @PostMapping("/api/unoauno")
    public User postUser (@RequestBody User user){
           return this.userRepository.save(user);
    }
    @GetMapping("/api/unoauno/{id}")
    public User getUser (@PathVariable Long id){
        return this.userRepository.findById(id).orElse(new User());
    }
    @DeleteMapping("/api/unoauno/{id}")
    public User deleteUser (@PathVariable Long id){
        User user = this.userRepository.findById(id).orElse(new User());
        this.userRepository.delete(user);
        return user;
    }
    @PostMapping("/api/unoauno/{id}/userprofile")
    public User postUserProfile (@PathVariable Long id,  @RequestBody UserProfile userProfile){
        User user = this.userRepository.findById(id).orElse(new User());
        //log.info(user.toString());
        //this.userProfileRepository.save(userProfile);
        //log.info(userProfile.toString());
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        this.userProfileRepository.save(userProfile);
        this.userRepository.save(user);
        //log.info(user.toString());
        return user;
    }
    @GetMapping("/api/unoauno/details")
    public List<UserProfile> getUserProfiles (){

        return this.userProfileRepository.findAll();
    }
    @PostMapping("/api/unoauno/details")
    public UserProfile postUser (@RequestBody UserProfile userProfile){
        log.info(userProfile.toString());
        return this.userProfileRepository.save(userProfile);
    }

    @GetMapping("/api/unoauno/details/{id}")
    public UserProfile getUserProfile (@PathVariable Long id){
        return this.userProfileRepository.findById(id).orElse(new UserProfile());
    }

}
