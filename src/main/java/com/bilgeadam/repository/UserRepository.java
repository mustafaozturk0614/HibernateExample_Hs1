package com.bilgeadam.repository;

import com.bilgeadam.entity.User;

public class UserRepository extends Repository<User,Long> {

    public UserRepository() {
        super(new User());
    }
}
