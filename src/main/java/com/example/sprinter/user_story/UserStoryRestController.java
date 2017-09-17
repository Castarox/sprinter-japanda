package com.example.sprinter.user_story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("user")
@RequestMapping("projects/user_story")
public class UserStoryRestController {
    private final UserStoryService userStoryService;

    @Autowired
    public UserStoryRestController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @PostMapping("/{id}")
    Boolean removeUserStory(@PathVariable Long id){
        userStoryService.remove(id);
        return true;
    }

}
