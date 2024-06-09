package com.codingWallah.NotePadApplication.repository;

import com.codingWallah.NotePadApplication.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
