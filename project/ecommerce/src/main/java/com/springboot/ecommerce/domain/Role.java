package com.springboot.ecommerce.domain;

import com.springboot.ecommerce.domain.user.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String authority;


    @ManyToMany(mappedBy = "roleList",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    //@Column(nullable = true)
    private List<User> UsersList;

    public List<User> getUsersList() {
        return UsersList;
    }

    public void setUsersList(List<User> appUsersList) {
        this.UsersList = UsersList;
    }

    public  Role(){

    }

    public Role(Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}