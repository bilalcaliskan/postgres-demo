package com.bcaliskan.postgresdemo.persistence.service;

import com.bcaliskan.postgresdemo.exception.ResourceNotFoundException;
import com.bcaliskan.postgresdemo.persistence.entity.AnswerEntity;
import com.bcaliskan.postgresdemo.persistence.repository.AnswerRepository;
import com.bcaliskan.postgresdemo.persistence.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;


    public Page<AnswerEntity> getAllAnswers(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public List<AnswerEntity> getAllAnswers() {
        return answerRepository.findAll();
    }

    public List<AnswerEntity> getAnswers(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public boolean addAnswer(Long questionId, AnswerEntity answerEntity) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    answerEntity.setQuestion(question);
                    return Objects.nonNull(answerRepository.save(answerEntity));
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    public boolean updateAnswer(Long questionId, Long answerId, AnswerEntity answerEntity) {
        if(!questionRepository.existsById(questionId))
            throw new ResourceNotFoundException("Question not found with id " + questionId);

        final Optional<AnswerEntity> tmpEntity = answerRepository.findById(answerId);
        return tmpEntity
                .map(answer -> {
                    answer.setText(answerEntity.getText());
                    return Objects.nonNull(answerRepository.save(answer));
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

    public boolean deleteAnswer(Long questionId, Long answerId) {
        if(!questionRepository.existsById(questionId))
            throw new ResourceNotFoundException("Question not found with id " + questionId);

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return Objects.isNull(answerRepository.findById(answerId));
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

}
