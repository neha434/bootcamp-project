package com.springboot.ecommerceApplication.domain;

import com.springboot.ecommerceApplication.domain.user.User;
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
    private List<User> UsersList;
//    @Fetch(value = FetchMode.SUBSELECT)

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", appUsersList=" + UsersList +
                '}';
    }
}
