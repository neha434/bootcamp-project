package com.springboot.ecommerceApplication.domain.product;

import com.springboot.ecommerceApplication.auditing.AuditInformation;
import com.springboot.ecommerceApplication.domain.user.Seller;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@SQLDelete(sql = "UPDATE PRODUCT SET isDeleted=true WHERE id=?")
@Where(clause = "isdeleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends AuditInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private Date createdTime;

    //    @NotNull
    @Size(max = 50)
    private String name;

    //    @NotNull
    private String description;

    private boolean isCancellable;
    private boolean isReturnable;

    @Size(max = 25)
//    @NotNull
    private String brand;
    private boolean isActive;
    private boolean isDeleted;


    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category productCategory;


    public Product() {

    }

    public Product(Category category, Seller sellerrId, String name, String brand, String description, boolean b, boolean cancellable, boolean returnable, boolean deleted) {
        this.seller = sellerrId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.isActive = b;
        this.isCancellable = cancellable;
        this.isReturnable = returnable;
        this.isDeleted = deleted;
        this.productCategory = category;

    }

    public Product(Integer id, String name, String description, String brand) {
        super();
        this.id=id;
        this.name=name;
        this.description=description;
        this.brand=brand;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<ProductVariation> productVariationList;

    public List<ProductVariation> getProductVariationList() {
        return productVariationList;
    }

    public void setProductVariationList(List<ProductVariation> productVariationList) {
        this.productVariationList = productVariationList;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReview> productReviewList;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private Seller seller;

//    public Date getCreatedTime() {
//        return createdTime;
//    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public Date setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return createdTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setCategory(Category watches) {
    }

    public Product(boolean isActive) {
        this.isActive = isActive;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductReview> getProductReviewsList() {
        return productReviewList;
    }

    public void setProductReviewsList(List<ProductReview> productReviewsList) {
        this.productReviewList = productReviewsList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isCancellable=" + isCancellable +
                ", isReturnable=" + isReturnable +
                ", brand='" + brand + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", productCategory=" + productCategory +
                ", productVariationList=" + productVariationList +
                ", productReviewList=" + productReviewList +
                ", seller=" + seller +
                '}';
    }
}
