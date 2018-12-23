package com.bcaliskan.postgresdemo.web.controller;

import com.bcaliskan.postgresdemo.controller.AnswerController;
import com.bcaliskan.postgresdemo.persistence.entity.AnswerEntity;
import com.bcaliskan.postgresdemo.persistence.entity.QuestionEntity;
import com.bcaliskan.postgresdemo.persistence.service.AnswerService;
import com.bcaliskan.postgresdemo.persistence.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AnswerService answerService;

    @Mock
    private QuestionService questionService;


    @Test
    public void shouldAnswersReturnMessage() throws Exception {
        this.mockMvc.perform(get("/answers-list")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createAnswer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionEntity questionEntity = new QuestionEntity(Long.valueOf(1), "Title1", "Description1");
        AnswerEntity answerEntity = new AnswerEntity(Long.valueOf(1), "Answer1", questionEntity);

        log.info("Posting question before posting answer...");
        mockMvc.perform(post("/questions")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(questionEntity)))
                .andExpect(status().isOk());

        log.info("Posting answer to the question...");
        mockMvc.perform(post("/questions/1/answers")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(answerEntity)))
                .andExpect(status().isOk());
    }


}
