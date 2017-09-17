package com.example.sprinter.user_story;

import com.example.sprinter.form.UserStoryForm;
import com.example.sprinter.project.Project;
import com.example.sprinter.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;

    private final ProjectService projectService;

    @Autowired
    public UserStoryService(UserStoryRepository userStoryRepository, ProjectService projectService) {
        this.userStoryRepository = userStoryRepository;
        this.projectService = projectService;
    }

    public UserStory findById(Long id) {
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

    public UserStory createUserStory(UserStoryForm userStoryForm, @PathVariable Long project_id) {
        String name = userStoryForm.getUserStoryName();
        String description = userStoryForm.getDescription();
        String priority = userStoryForm.getPriority();
        Project project = projectService.findById(project_id);
        return new UserStory(name, description, priority, project);
    }


}
