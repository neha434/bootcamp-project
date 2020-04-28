package com.springboot.ecommerceApplication.domain.user;

import com.springboot.ecommerceApplication.domain.Role;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Email
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private  String image;
    private Integer count= 0;
//
//    @Column(name = "enabled")
//    private boolean enabled;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public User(){

    }

    //@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*])(?=.{8,15})", message = "Invalid Password")
    private String password;
    private Boolean isDeleted;
    private Boolean isActive;
   private Date deactivatedTime;

    public Date getDeactivatedTime() {
        return deactivatedTime;
    }

    public void setDeactivatedTime(Date deactivatedTime) {
        this.deactivatedTime = deactivatedTime;
    }

    @ManyToMany(fetch = FetchType.EAGER)//
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roleList;
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }


    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addressesList;

    public List<Address> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(List<Address> addressesList) {
        this.addressesList = addressesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public boolean isActive() {
        return isActive ;
    }

//  public void setEnabled(boolean enabled){
//        this.enabled = enabled;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", roleList=" + roleList +
                ", addressesList=" + addressesList +
                '}';
    }
}




