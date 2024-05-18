package com.bilgeadam;

import com.bilgeadam.entity.Name;
import com.bilgeadam.entity.Post;
import com.bilgeadam.entity.User;
import com.bilgeadam.enums.EGender;
import com.bilgeadam.repository.PostRepository;
import com.bilgeadam.repository.UserRepository;
import com.bilgeadam.utility.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/*
    LibraryManagmentSystem adlı bir hibernate projesi olusturalım  ve dokumantasyondaki entityleri yazalım.

 */





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

//        EntityManagerFactory emf= EntityManagerFactoryUtility.getEntityManagerFactory();
//        EntityManager entityManager=emf.createEntityManager();
        /*
            1- bir user olustrup databse e kayıt edelim
         */
        User user= User.builder()
                .name(Name.builder().firstName("Mustafa")
                        .lastName("Öztürk").build())
                .username("mustafa222")
                .birthDate(LocalDate.parse("1987-10-12"))
                .postCount(18)
                .gender(EGender.MAN)
                .build();

        User user2= User.builder()
                .name(Name.builder().firstName("Özge")
                        .lastName("Keskin").build())
                .username("ozge")
                .gender(EGender.WOMAN)
                .postCount(8)
                .build();

        Post post=Post.builder().content("1. post").date(LocalDateTime.of(2024,3, 3,7,30)).user(user).build();
        Post post2=Post.builder().content("2. post").user(user).build();
        Post post3=Post.builder().content("3. post").user(user).build();

        UserRepository userRepository=new UserRepository();
        PostRepository postRepository=new PostRepository();

        userRepository.saveAll(List.of(user,user2));
        postRepository.saveAll(List.of(post,post2,post3));
      //  user.setPosts(List.of(post,post2,post3));
     //   userRepository.update(user);
//        userRepository.findAll().forEach(System.out::println);
//
//      Optional<User> user1  =userRepository.findById(1L);
//        System.out.println(user1.get());
        postRepository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);

//        entityManager=emf.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(user);
//        entityManager.persist(user2);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//
//
//        entityManager=emf.createEntityManager();
//            entityManager.getTransaction().begin();
//            entityManager.persist(post);
//            entityManager.persist(post2);
//            entityManager.persist(post3);
//              entityManager.getTransaction().commit();
//            entityManager.close();
//         emf.close();




           // entityManager.getTransaction().rollback();






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