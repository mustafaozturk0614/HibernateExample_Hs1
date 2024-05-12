package com.bilgeadam.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtility {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtility() {
    }

    public  static  EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory==null){
            entityManagerFactory= Persistence.createEntityManagerFactory("userexample");
        }
        return entityManagerFactory;

    }
}
