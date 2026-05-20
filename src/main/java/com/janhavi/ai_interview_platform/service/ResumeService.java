package com.janhavi.ai_interview_platform.service;

import com.janhavi.ai_interview_platform.entity.Resume;
import com.janhavi.ai_interview_platform.entity.ResumeAnalysis;
import com.janhavi.ai_interview_platform.repository.ResumeAnalysisRepository;
import com.janhavi.ai_interview_platform.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResumeAnalysisRepository
            resumeAnalysisRepository;

    public String uploadResume(
            MultipartFile file,
            String email
    ) throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                        + "/uploads/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath =
                uploadDir + file.getOriginalFilename();

        file.transferTo(new File(filePath));

        Resume resume = Resume.builder()
                .fileName(file.getOriginalFilename())
                .filePath(filePath)
                .uploadedBy(email)
                .build();

        resumeRepository.save(resume);

        // PDF TEXT EXTRACTION

        File pdfFile = new File(filePath);

        PDDocument document =
                Loader.loadPDF(pdfFile);

        PDFTextStripper pdfStripper =
                new PDFTextStripper();

        String extractedText =
                pdfStripper.getText(document);

        document.close();

        // SKILL DETECTION

        List<String> detectedSkills =
                new ArrayList<>();

        String lowerText =
                extractedText.toLowerCase();

        String[] skills = {
                "java",
                "spring boot",
                "react",
                "mysql",
                "postgresql",
                "docker",
                "aws",
                "javascript",
                "python",
                "html",
                "css",
                "hibernate"
        };

        for (String skill : skills) {

            if (lowerText.contains(skill)) {
                detectedSkills.add(skill);
            }
        }

        // ATS SCORE

        int atsScore =
                detectedSkills.size() * 10;

        if (atsScore > 100) {
            atsScore = 100;
        }

        ResumeAnalysis analysis =
                ResumeAnalysis.builder()
                        .extractedText(extractedText)
                        .skills(
                                String.join(
                                        ", ",
                                        detectedSkills
                                )
                        )
                        .atsScore(atsScore)
                        .build();

        resumeAnalysisRepository.save(analysis);

        return "Resume Uploaded & Analyzed";
    }
}