package com.microservices.multiplication.challenge;

import lombok.Value;

@Value
public class ChallengeAttemptDTO {
    int factorA, factorB;
    String userAlais;
    int guess;


}
