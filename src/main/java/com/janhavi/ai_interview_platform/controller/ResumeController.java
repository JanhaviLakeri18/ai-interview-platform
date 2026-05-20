package com.janhavi.ai_interview_platform.controller;

import com.janhavi.ai_interview_platform.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email
    ) throws IOException {

        return resumeService.uploadResume(
                file,
                email
        );
    }
}