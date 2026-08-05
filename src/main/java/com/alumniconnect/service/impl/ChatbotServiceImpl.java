package com.alumniconnect.service.impl;

import com.alumniconnect.dto.ChatRequest;
import com.alumniconnect.dto.ChatResponse;
import com.alumniconnect.service.ChatbotService;
import com.alumniconnect.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alumniconnect.dto.JobRecommendationResponse;
import com.alumniconnect.dto.AlumniRecommendationResponse;
import com.alumniconnect.dto.MentorRecommendationResponse;
import com.alumniconnect.dto.JobSkillGapResponse;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

    private final RecommendationService recommendationService;

    @Override
    public ChatResponse chat(ChatRequest request) {

        String message = request.getMessage()
                .toLowerCase()
                .trim();

        ChatResponse response = new ChatResponse();

        if (message.contains("job")) {

            StringBuilder reply = new StringBuilder();

            reply.append("Recommended Jobs:\n\n");

            int count = 1;

            for (JobRecommendationResponse job :
                    recommendationService.recommendJobs()) {

                reply.append(count++)
                        .append(". ")
                        .append(job.getJobTitle())
                        .append(" - ")
                        .append(job.getCompany())
                        .append("\n");

            }

            response.setReply(reply.toString());

            return response;

        }

        if (message.contains("alumni")) {

            StringBuilder reply = new StringBuilder();

            reply.append("Recommended Alumni:\n\n");

            int count = 1;

            for (AlumniRecommendationResponse alumni :
                    recommendationService.recommendAlumni()) {

                reply.append(count++)
                        .append(". ")
                        .append(alumni.getFullName())
                        .append(" - ")
                        .append(alumni.getCompany())
                        .append("\n");

            }

            response.setReply(reply.toString());

            return response;

        }

        if (message.contains("mentor")) {

            StringBuilder reply = new StringBuilder();

            reply.append("Recommended Mentors:\n\n");

            int count = 1;

            for (MentorRecommendationResponse mentor :
                    recommendationService.recommendMentors()) {

                reply.append(count++)
                        .append(". ")
                        .append(mentor.getFullName())
                        .append(" - ")
                        .append(mentor.getCompany())
                        .append("\n");

            }

            response.setReply(reply.toString());

            return response;

        }

        if (message.contains("skill")) {

            StringBuilder reply = new StringBuilder();

            reply.append("Job Skill Gap Analysis:\n\n");

            for (JobSkillGapResponse job :
                    recommendationService.detectJobSkillGap()) {

                reply.append(job.getJobTitle())
                        .append(" (")
                        .append(job.getCompany())
                        .append(")\n");

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

        response.setReply(
                "Sorry, I didn't understand your question.\n\nType 'help' to see all available commands."
        );

        return response;

    }

}