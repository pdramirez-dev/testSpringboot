package com.microservices.multiplication.challenge;

import com.microservices.multiplication.user.User;

public class ChallengeServiceImpl  implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        boolean isCorrect = attemptDTO.getGuess() ==
                attemptDTO.getFactorA()*attemptDTO.getFactorB();
        // We don't use identifeiers for now

        User user = new User(null,attemptDTO.getUserAlais());
        ChallengeAttempt checkedAttempt = new ChallengeAttempt(
                null,user.getId(),
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect
        );
        return checkedAttempt;
    }
}
