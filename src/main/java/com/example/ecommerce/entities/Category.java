package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "category")
//    private Set<SubCategory> subCategory = new HashSet<>();
    @OneToMany(mappedBy = "Category")
    private Set<Product> product=new HashSet<>();
    @Column(name = "image")
    private String image;

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

//    public Set<SubCategory> getSubCategory() {
//        return subCategory;
//    }
//
//    public void setSubCategory(Set<SubCategory> subCategory) {
//        this.subCategory = subCategory;
//    }

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
