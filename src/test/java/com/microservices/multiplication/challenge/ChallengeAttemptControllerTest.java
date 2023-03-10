package com.microservices.multiplication.challenge;

import com.microservices.multiplication.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {
    @MockBean
    private ChallengeService challengeService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<ChallengeAttemptDTO> jsonRequestAttemp;
    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttemp;


    @Test
    public void postValidResult() throws Exception {
        // given
        User user = new User(1L, "john");
        Long attemptId = 5L;
        ChallengeAttemptDTO attemptDTO =
                new ChallengeAttemptDTO(50, 70, "john", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user.getId(),
                50, 70, 3500, true);
        given(challengeService.
                verifyAttempt(eq(attemptDTO)))
                .willReturn(expectedResponse);
        MockHttpServletResponse response = mvc.perform(
                post("/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttemp.write(attemptDTO).getJson())
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultAttemp.write(expectedResponse).getJson());


    }

    @Test
    public void postInvalidResult() throws Exception {
        // given an attempt  with invalid input data
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(2000,-70,"john",1);
        // when
        MockHttpServletResponse response = mvc.perform(post("/attempts").contentType(MediaType.APPLICATION_JSON).content(jsonRequestAttemp.write(attemptDTO).getJson())).andReturn().getResponse();
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

}
