package com.bcaliskan.postgresdemo;

import static org.assertj.core.api.Assertions.assertThat;
import com.bcaliskan.postgresdemo.controller.AnswerController;
import com.bcaliskan.postgresdemo.controller.QuestionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private AnswerController answerController;

    @Autowired
    private QuestionController questionController;


    @Test
    public void contextLoads() throws Exception {
        assertThat(answerController).isNotNull();
        assertThat(questionController).isNotNull();
    }

}