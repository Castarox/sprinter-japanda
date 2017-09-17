package com.example.sprinter.project;

import com.example.sprinter.form.ProjectForm;
import com.example.sprinter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project findById(Long id) {
        return projectRepository.findOne(id);
    }

    public Project createProject(ProjectForm projectForm, User user) {
        String startDate = projectForm.getStartDate();
        String endDate = projectForm.getEndDate();
        String name = projectForm.getProjectName();
        Set<User> owners = new HashSet<>();
        owners.add(user);
        return new Project(name, owners, startDate, endDate, false);
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void remove(Long id) {
        projectRepository.delete(id);
    }

}
