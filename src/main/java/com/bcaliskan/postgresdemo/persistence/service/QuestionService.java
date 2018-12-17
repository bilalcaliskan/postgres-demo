package com.bcaliskan.postgresdemo.persistence.service;

import com.bcaliskan.postgresdemo.exception.ResourceNotFoundException;
import com.bcaliskan.postgresdemo.persistence.entity.QuestionEntity;
import com.bcaliskan.postgresdemo.persistence.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.Objects;


@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;


    public Page<QuestionEntity> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    public boolean createQuestion(@Valid @RequestBody QuestionEntity question) {
        return Objects.nonNull(questionRepository.save(question));
    }

    public boolean updateQuestion(Long questionId, QuestionEntity questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return Objects.nonNull(questionRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    public boolean deleteQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return Objects.isNull(questionRepository.findById(questionId));
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

}
