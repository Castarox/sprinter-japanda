package com.example.sprinter.project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByOwnerIdOrderByEndDateDesc(long ownerId);
}
