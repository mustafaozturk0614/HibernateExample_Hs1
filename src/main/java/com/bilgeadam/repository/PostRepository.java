package com.bilgeadam.repository;

import com.bilgeadam.entity.Post;
import com.bilgeadam.entity.User;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PostRepository extends Repository<Post,Long> {
    public PostRepository() {
        super(new Post());
    }

    public List<Post> findPostsByUserId(User user) {
        openSession();
        String hql="select p from Post p where p.user.id="+user.getId();
        TypedQuery<Post> typedQuery=getEm().createQuery(hql,Post.class);
        List<Post> posts=typedQuery.getResultList();
        closeSession();
        return posts;


    }
}
