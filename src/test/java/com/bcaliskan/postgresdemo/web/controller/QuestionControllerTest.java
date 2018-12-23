package com.bcaliskan.postgresdemo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.bcaliskan.postgresdemo.persistence.entity.QuestionEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldQuestionsReturnMessage() throws Exception {
        this.mockMvc.perform(get("/questions-list")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createQuestion() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionEntity questionEntity = new QuestionEntity(Long.valueOf(1), "Title1", "Description1");

        mockMvc.perform(post("/questions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(questionEntity)))
                .andExpect(status().isOk());
    }

}
