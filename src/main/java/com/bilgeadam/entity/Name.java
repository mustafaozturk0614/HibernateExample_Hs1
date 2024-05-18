package com.bilgeadam.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;
}
