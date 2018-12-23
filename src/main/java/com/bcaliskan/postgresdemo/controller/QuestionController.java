package com.bcaliskan.postgresdemo.controller;

import com.bcaliskan.postgresdemo.persistence.entity.QuestionEntity;
import com.bcaliskan.postgresdemo.persistence.service.QuestionService;
import com.bcaliskan.postgresdemo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/questions-page")
    public Page<QuestionEntity> getQuestions(Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }

    @GetMapping("/questions-list")
    public ResponseEntity<List<QuestionEntity>> getQuestions() {
        return ResponseEntity.ok().body(questionService.getAllQuestions());
    }

    @PostMapping("/questions")
    public ResponseEntity<Map<String, Object>> createQuestion(@Valid @RequestBody QuestionEntity questionEntity) {
        if (questionService.createQuestion(questionEntity))
            return new ResponseEntity<>(JsonUtil.constructJSON("createQuestion", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("createQuestion", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/questions/{questionId}")
    public ResponseEntity<Map<String, Object>> updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionEntity questionRequest) {
        if (questionService.updateQuestion(questionId, questionRequest))
            return new ResponseEntity<>(JsonUtil.constructJSON("updateQuestion", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("updateQuestion", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        if (questionService.deleteQuestion(questionId))
            return new ResponseEntity<>(JsonUtil.constructJSON("deleteQuestion", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("deleteQuestion", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
