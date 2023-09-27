package com.example.exospringc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String nbPhone;
//        private Integer age;
}
