package com.example.hw.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class PersonRequestDto {
    String name;
    int age;
    String address;
    String job;
}
