package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category  {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "category")
    private Set<SubCategory> subCategory = new HashSet<>();

//    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = "category")
//    @JsonBackReference
//    private Set<Product> product=new HashSet<>();

    @Column(name = "image")
    private String image;


//    public Set<Product> getProduct() {
//        return product;
//    }
//
//    public void setProduct(Set<Product> product) {
//        this.product = product;
//    }

    @Column(name = "deleted")
    private Boolean deleted= false;

    public Boolean getIsDeleted() {
        return deleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    public Category() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Set<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category(UUID id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id == category.id;
    }
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("server", server).append("date", date).toString();
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", subCategory=" + subCategory +
                ", image=" + image +
                '}';
    }
}
