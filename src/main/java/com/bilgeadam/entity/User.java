package com.bilgeadam.entity;


import com.bilgeadam.enums.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_user")

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true,updatable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private EGender gender;



    private int postCount;

    private LocalDate birthDate;

    @Transient
    private short age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Address> addresses;

}
