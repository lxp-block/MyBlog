package com.lxpnow.blog.Service;


import com.lxpnow.blog.entity.User;
import com.lxpnow.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void saveUser(User user){
        user.setUserImage("/images/userImage/defaultUserImage.png");
        user.setUserRole(1);
        String password=user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userMapper.saveUser(user);
    }

    public boolean isUsernameExist(String username){
        User user = userMapper.getUserByUsername(username);
        return user!=null;
    }

    public boolean isEmailExist(String userEmail){
        User user=userMapper.getUserByEmail(userEmail);
        return user!=null;
    }

    public void admin(User user){
        user.setUserRole(0);
        user.setUserImage("/images/userImage/defaultAdminImage.png");
        String password=user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userMapper.saveUser(user);
    }

}
