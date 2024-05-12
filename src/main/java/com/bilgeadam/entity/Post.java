package com.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Builder.Default
    private LocalDateTime date=LocalDateTime.now();

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "my_user_id",nullable = false)
    private User user;



}
