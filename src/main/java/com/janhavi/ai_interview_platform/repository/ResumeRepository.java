package com.janhavi.ai_interview_platform.repository;

import com.janhavi.ai_interview_platform.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository
        extends JpaRepository<Resume, Long> {
}