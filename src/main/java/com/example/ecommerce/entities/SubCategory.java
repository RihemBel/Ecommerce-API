package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "subCategory")
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    private Category category;
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "subCategory")
    private Set<Product> product=new HashSet<>();
    @OneToMany(mappedBy = "subCategory")
    private Set<Item> item=new HashSet<>();

    public SubCategory() {

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubCategory)) return false;
        SubCategory that = (SubCategory) o;
        return getId() == that.getId();
    }

    public SubCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", image='" + image + '\'' +
                ", product=" + product +
                ", item=" + item +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
