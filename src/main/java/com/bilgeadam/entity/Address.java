package com.bilgeadam.entity;

import com.bilgeadam.enums.EAddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;

    @Enumerated(EnumType.STRING)
    private EAddressType addressType;

    @ManyToMany

     @JoinTable(name = "kullanıcı_adress",
     joinColumns= @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "adres_id")
     )
    List<User> users;

}
