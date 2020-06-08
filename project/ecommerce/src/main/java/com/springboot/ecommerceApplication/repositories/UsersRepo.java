package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.HibernateUtil;
import com.springboot.ecommerceApplication.domain.user.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public UsersRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findByEmails(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.equal(root.get("email"), email);
        criteriaQuery.where(predicate);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        System.out.println("########################################" + query.getSingleResult());
        return query.getSingleResult();

    }

 }
