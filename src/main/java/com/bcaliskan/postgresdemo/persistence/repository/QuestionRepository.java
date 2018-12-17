package com.bcaliskan.postgresdemo.persistence.repository;

import com.bcaliskan.postgresdemo.persistence.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {


}