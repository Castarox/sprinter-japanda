package com.example.sprinter.user;

import com.example.sprinter.UserDetail.UserDetail;
import com.example.sprinter.form.*;
import com.example.sprinter.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getByLogin(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        UserDetail userDetail = user.getUserDetail();
        userDetail.setPassword(newPassword);
        user.setUserDetail(userDetail);
        save(user);
    }

    public void updateUserProjects(User user, Project project) {
        Set<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        save(user);

    }

    public User getUpdatedUser(User user) {
        return this.getByLogin(user.getEmail());
    }


    public User saveNewUser(RegistrationForm registrationForm) {
        User newUser = new User();
        newUser.setId(0L);
        newUser.setName(registrationForm.getName());
        newUser.setEmail(registrationForm.getEmail());
        newUser.setPassword(registrationForm.getPassword());
        newUser.setSurname(registrationForm.getSurname());
        newUser = save(newUser);
        return newUser;
    }
}