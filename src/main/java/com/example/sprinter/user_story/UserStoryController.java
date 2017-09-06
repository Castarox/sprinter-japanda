package com.example.sprinter.user_story;


import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
@RequestMapping("/user_story")
public class UserStoryController {
    private final UserStoryService userStoryService;

    @Autowired
    private UserService userService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model) {
        UserStory userStory = userStoryService.findById(id);
        model.addAttribute("userStory", userStory);
        return "project";
    }

//    @PostMapping("/new")
//    String add()

}
