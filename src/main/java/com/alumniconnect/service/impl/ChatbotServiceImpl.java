package com.alumniconnect.service.impl;

import com.alumniconnect.dto.ChatRequest;
import com.alumniconnect.dto.ChatResponse;
import com.alumniconnect.service.ChatbotService;
import com.alumniconnect.service.RecommendationService;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.alumniconnect.dto.JobRecommendationResponse;
import com.alumniconnect.dto.AlumniRecommendationResponse;
import com.alumniconnect.dto.MentorRecommendationResponse;
import com.alumniconnect.dto.JobSkillGapResponse;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

    private final RecommendationService recommendationService;
    private final UserRepository userRepository;

    @Override
    public ChatResponse chat(ChatRequest request) {

        String message = request.getMessage().toLowerCase().trim();
        ChatResponse response = new ChatResponse();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == Role.STUDENT) {
            return handleStudentChat(message, response);
        } else if (user.getRole() == Role.ALUMNI) {
            return handleAlumniChat(message, response);
        } else if (user.getRole() == Role.ADMIN) {
            return handleAdminChat(message, response);
        }

        response.setReply("Sorry, I didn't understand your question.");
        return response;
    }

    private ChatResponse handleStudentChat(String message, ChatResponse response) {
        if (message.contains("job")) {
            StringBuilder reply = new StringBuilder();
            reply.append("Recommended Jobs:\n\n");
            int count = 1;
            for (JobRecommendationResponse job : recommendationService.recommendJobs()) {
                reply.append(count++).append(". ").append(job.getJobTitle())
                        .append(" - ").append(job.getCompany()).append("\n");
            }
            response.setReply(reply.toString());
            return response;
        }

        if (message.contains("alumni")) {
            StringBuilder reply = new StringBuilder();
            reply.append("Recommended Alumni:\n\n");
            int count = 1;
            for (AlumniRecommendationResponse alumni : recommendationService.recommendAlumni()) {
                reply.append(count++).append(". ").append(alumni.getFullName())
                        .append(" - ").append(alumni.getCompany()).append("\n");
            }
            response.setReply(reply.toString());
            return response;
        }

        if (message.contains("mentor")) {
            StringBuilder reply = new StringBuilder();
            reply.append("Recommended Mentors:\n\n");
            int count = 1;
            for (MentorRecommendationResponse mentor : recommendationService.recommendMentors()) {
                reply.append(count++).append(". ").append(mentor.getFullName())
                        .append(" - ").append(mentor.getCompany()).append("\n");
            }
            response.setReply(reply.toString());
            return response;
        }

        if (message.contains("skill")) {
            StringBuilder reply = new StringBuilder();
            reply.append("Job Skill Gap Analysis:\n\n");
            for (JobSkillGapResponse job : recommendationService.detectJobSkillGap()) {
                reply.append(job.getJobTitle()).append(" (").append(job.getCompany()).append(")\n");
                reply.append("Missing Skills: ");
                reply.append(String.join(", ", job.getMissingSkills()));
                reply.append("\n\n");
            }
            response.setReply(reply.toString());
            return response;
        }

        if (message.contains("help") || message.contains("command")) {
            StringBuilder reply = new StringBuilder();
            reply.append("🤖 Welcome to Alumni Connect Assistant!\n\n");
            reply.append("I can help you with the following:\n\n");
            reply.append("• Show recommended jobs\n");
            reply.append("• Show recommended alumni\n");
            reply.append("• Show mentors\n");
            reply.append("• Show skill gap\n\n");
            reply.append("Examples:\n");
            reply.append("\"Show recommended jobs\"\n");
            reply.append("\"Show recommended alumni\"\n");
            reply.append("\"Show mentors\"\n");
            reply.append("\"Show skill gap\"");
            response.setReply(reply.toString());
            return response;
        }

        response.setReply("Sorry, I didn't understand your question.\n\nType 'help' to see all available commands.");
        return response;
    }

    private ChatResponse handleAlumniChat(String message, ChatResponse response) {
        if (message.contains("alumni") || message.contains("mentor") || message.contains("skill")) {
            response.setReply("This feature is currently available only for students.");
            return response;
        }

        if (message.contains("job")) {
            response.setReply("You can manage your job postings in the 'My Jobs' section of your dashboard. Try posting a new job to help students!");
            return response;
        }
        
        if (message.contains("dashboard")) {
            response.setReply("Your dashboard provides an overview of your platform activity, including job postings and profile statistics.");
            return response;
        }

        if (message.contains("profile")) {
            response.setReply("You can view and edit your professional details, experience, and skills in the Profile section.");
            return response;
        }

        if (message.contains("help") || message.contains("command")) {
            StringBuilder reply = new StringBuilder();
            reply.append("🤖 Welcome to Alumni Connect Assistant!\n\n");
            reply.append("I can help you navigate your alumni tools:\n\n");
            reply.append("• Jobs management\n");
            reply.append("• Dashboard overview\n");
            reply.append("• Profile information\n");
            response.setReply(reply.toString());
            return response;
        }

        response.setReply("Sorry, I didn't understand your question.\n\nType 'help' to see all available commands.");
        return response;
    }

    private ChatResponse handleAdminChat(String message, ChatResponse response) {
        if (message.contains("alumni") || message.contains("mentor") || message.contains("skill")) {
            response.setReply("This feature is currently available only for students.");
            return response;
        }

        if (message.contains("job")) {
            response.setReply("You can view, edit, and moderate all active platform jobs from the Jobs section.");
            return response;
        }
        
        if (message.contains("dashboard")) {
            response.setReply("Your Admin Dashboard provides system-wide statistics including total students, alumni, and active jobs.");
            return response;
        }

        if (message.contains("statistic")) {
            response.setReply("Platform statistics are generated dynamically and displayed on your Dashboard homepage.");
            return response;
        }

        if (message.contains("help") || message.contains("command")) {
            StringBuilder reply = new StringBuilder();
            reply.append("🤖 Welcome to Alumni Connect Assistant!\n\n");
            reply.append("I can help you navigate administrative tools:\n\n");
            reply.append("• Dashboard overview\n");
            reply.append("• Jobs moderation\n");
            reply.append("• Platform statistics\n");
            response.setReply(reply.toString());
            return response;
        }

        response.setReply("Sorry, I didn't understand your question.\n\nType 'help' to see all available commands.");
        return response;
    }
}