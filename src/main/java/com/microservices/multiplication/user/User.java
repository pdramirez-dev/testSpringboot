package com.microservices.multiplication.user;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
     private Long id;
     private String alias;
}
