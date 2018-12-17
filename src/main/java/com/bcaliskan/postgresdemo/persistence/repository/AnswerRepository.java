package com.bcaliskan.postgresdemo.persistence.repository;

import com.bcaliskan.postgresdemo.persistence.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findByQuestionId(Long questionId);

}