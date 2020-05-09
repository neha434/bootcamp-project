package com.springboot.ecommerceApplication.domain.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    private String  name;

    private String parent;
//    @OneToMany(mappedBy = "parentCategory",cascade = CascadeType.ALL)
//    private List<Category>
//
//    @ManyToOne()
//    @JoinColumn(name = "parent_id")
//    private Category parentCategory;

    @OneToMany(mappedBy = "productCategory",cascade = CascadeType.ALL)
    private List<Product> productList;

    public Category(String name) {
        this.name=name;
    }

    public Category() {

    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}