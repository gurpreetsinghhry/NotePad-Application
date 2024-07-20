package com.mycompany.notepad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.notepad.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectEntity, String> {
}