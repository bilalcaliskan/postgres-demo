package com.bcaliskan.postgresdemo.configuration;

import com.bcaliskan.postgresdemo.persistence.repository.AnswerRepository;
import com.bcaliskan.postgresdemo.persistence.repository.QuestionRepository;
import com.bcaliskan.postgresdemo.persistence.service.AnswerService;
import com.bcaliskan.postgresdemo.persistence.service.QuestionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfiguration {

    @Bean
    public AnswerService answerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        return new AnswerService(answerRepository, questionRepository);
    }

    @Bean
    public QuestionService questionService(QuestionRepository questionRepository) {
        return new QuestionService(questionRepository);
    }

}
