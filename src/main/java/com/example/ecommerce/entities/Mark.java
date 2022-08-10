package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mark")
public class Mark  {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String  image;

    @OneToMany(mappedBy = "mark" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "mark")
    @JsonBackReference
    private Set<Product> product= new HashSet<>();

    private Boolean deleted= false;

    public Boolean getIsDeleted() {
        return deleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return id == mark.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", product=" + product +
                '}';
    }
}
