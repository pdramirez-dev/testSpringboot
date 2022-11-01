package com.microservices.multiplication.challenge;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor

public class ChallengeAttempt {
    private Long id;
    private Long userId;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}
