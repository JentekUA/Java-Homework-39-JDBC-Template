package com.example.app.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@ToString
@FieldDefaults(level = PRIVATE)
public class CustomerEntity {
    Long id;
    String fullName;
    String email;
    String socialSecurityNumber;
}