package com.springboot.ecommerceApplication.repositories;


import com.springboot.ecommerceApplication.domain.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {

//    default User getStudentByName(String name){
//        Criteria crit = em.unwrap(Session.class).createCriteria(User.class);
//        crit.add(Restrictions.like("firstName", "riya%"));
//        List<User> students = crit.list();
//        for(User user: students)
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+user.getFirstName());
//
//            return students.get(0);}

    User findByEmail(String email);

    Optional<User> findById(Integer id);

    List<User> findByIsActive(boolean value);


//    @Query(
//            value = "Update User set isActive = true WHERE deactivatedTime >(NOW() - INTERVAL 24 HOUR)",
//            nativeQuery = true)
//    void ActivateUsers();


}