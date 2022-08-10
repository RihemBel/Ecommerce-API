package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "image_item")
public class ImageItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private UUID id;


    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "avatar")
    private String avatar;

//    @JsonBackReference
//    @ManyToOne
//    @JsonIgnoreProperties(value = "imageItems", allowSetters = true)
//    private Item item;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public ImageItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageItem)) {
            return false;
        }
        return id != null && id.equals(((ImageItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageItem{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                "}";
    }
}