package com.bcaliskan.postgresdemo.controller;

import com.bcaliskan.postgresdemo.persistence.entity.AnswerEntity;
import com.bcaliskan.postgresdemo.persistence.service.AnswerService;
import com.bcaliskan.postgresdemo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;


    @GetMapping(value = "/answers-page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<AnswerEntity> getAllAnswers(Pageable pageable){
        return answerService.getAllAnswers(pageable);
    }

    @GetMapping(value = "/answers-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnswerEntity>> getAllAnswersByList(){
        return ResponseEntity.ok().body(answerService.getAllAnswers());
    }

    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<List<AnswerEntity>> getAnswersByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(answerService.getAnswers(questionId));
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<Map<String, Object>> addAnswer(@PathVariable Long questionId,
                                                         @Valid @RequestBody AnswerEntity answer) {
        if (answerService.addAnswer(questionId, answer))
            return new ResponseEntity<>(JsonUtil.constructJSON("addAnswer", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("addAnswer", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<Map<String, Object>> updateAnswer(@PathVariable Long questionId,
                                                            @PathVariable Long answerId,
                                                            @Valid @RequestBody AnswerEntity answerRequest) {
        if (answerService.updateAnswer(questionId, answerId, answerRequest))
            return new ResponseEntity<>(JsonUtil.constructJSON("updateAnswer", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("updateAnswer", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<Map<String, Object>> deleteAnswer(@PathVariable Long questionId,
                                                            @PathVariable Long answerId) {
        if (answerService.deleteAnswer(questionId, answerId))
            return new ResponseEntity<>(JsonUtil.constructJSON("deleteAnswer", true), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtil.constructJSON("deleteAnswer", false, "Internal error occured"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
