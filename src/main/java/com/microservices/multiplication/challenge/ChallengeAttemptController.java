package com.microservices.multiplication.challenge;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChallengeAttemptController {
    private final  ChallengeService challengeService;

    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody ChallengeAttemptDTO challengeAttemptDTO){
        return  ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO));
    }
}
