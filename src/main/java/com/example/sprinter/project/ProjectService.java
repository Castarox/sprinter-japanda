package com.example.sprinter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project findById(Long id) {
        return projectRepository.findOne(id);
    }

    public void add(Project project) {
        projectRepository.save(project);
    }

    public void update(Project project) {
        projectRepository.save(project);
    }

    public void remove(Long id) {
        projectRepository.delete(id);
    }

}
