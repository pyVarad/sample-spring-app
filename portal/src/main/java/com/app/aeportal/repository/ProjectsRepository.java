package com.app.aeportal.repository;

import com.app.aeportal.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
}
