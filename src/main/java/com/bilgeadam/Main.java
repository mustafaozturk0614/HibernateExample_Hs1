package com.bilgeadam;

import com.bilgeadam.entity.User;
import com.bilgeadam.enums.EGender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
      bir database olusturalım ==>  hs1UserDb adında

      daha sonra java tarafında bu databse baglantı ayaarlarnı yapalım
      birde user  entitysi olusturalım
      first name,lastname, username,password ,gender, address,age,postCount
      projeyi çalıstıralım

<property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.highlight_sql" value="true" />
   */
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("userexample");
        EntityManager entityManager=emf.createEntityManager();
        /*
            1- bir user olustrup databse e kayıt edelim
         */
           User user= User.builder()
                   .firstName("Mustafa")
                   .lastName("Öztürk")
                   .username("mustafa222")
                   .birthDate(LocalDate.parse("1987-10-12"))
                   .postCount(18)
                   .gender(EGender.MAN)
                   .build();

        User user2= User.builder()
                .firstName("Özge")
                .lastName("Keskin")
                .username("ozge")
                .gender(EGender.WOMAN)
                .postCount(8)
                .build();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.flush();
            entityManager.persist(user2);
            entityManager.getTransaction().commit();
        }catch (Exception e){
           // entityManager.getTransaction().rollback();
        }

        

        /*
        1-CriteriaQuery
        2-Hql,Jpql
        3-NatvieQuery
        4-NamedQuery

         */

        /* findAll

         */

        System.out.println("==========================================");

      //  findAllUser(entityManager);


        // findbyusername
     //   System.out.println("=====>" + findByUsername(entityManager,"ozge"));   ;

    /*
     findbyId
     ismi M ile baslayanları bul
     post countu 10dan buyuk olanları getir
     */




            entityManager.close();
            emf.close();


    }

    private static Optional<User> findByUsername(EntityManager entityManager, String myUsername) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        //select * from User where username = mustafa'
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"),myUsername));
        User user=null;
        try {
            // olusturdugumu queriyi database uzerinde calıstırıp bize veri donduruypr
            user=entityManager.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException exception){
            System.out.println("Kullanıcı bulunamadı");
        }

        return  Optional.ofNullable(user);
    }

    private static void findAllUser(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        List<User> userList= entityManager.createQuery(criteriaQuery).getResultList();
        userList.forEach(System.out::println);
    }


}