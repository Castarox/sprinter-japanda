package com.example.sprinter.user;

import com.example.sprinter.UserDetail.UserDetail;
import com.example.sprinter.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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


}