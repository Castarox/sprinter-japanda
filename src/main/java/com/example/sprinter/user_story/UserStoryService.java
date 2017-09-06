package com.example.sprinter.user_story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryService {

    private UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    UserStory findById(Long id) {
        return userStoryRepository.findOne(id);
    }

    public void add(UserStory userStory) {
        userStoryRepository.save(userStory);
    }

    public void update(UserStory userStory) {
        userStoryRepository.save(userStory);
    }

    public void remove(Long id) {
        userStoryRepository.delete(id);
    }


}
