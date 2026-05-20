package com.janhavi.ai_interview_platform.repository;

import com.janhavi.ai_interview_platform.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {
}