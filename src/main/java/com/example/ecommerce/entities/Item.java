package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id",
        scope     = UUID.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id")
    private UUID id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "sku")
    private String sku;
    @ManyToOne
    private Product product;
    //zeyda
    @NotNull
    @Column(name = "qttInStock", nullable = false, precision = 21, scale = 3)
    private BigDecimal qttInStock;
    @Column(name="price")
    private Double price;
//    @OneToMany(mappedBy = "item")
//    private Set<ImageItem> image = new HashSet<>();


    @Column(name = "image")
    private String image;
//    @ManyToMany
//    @JoinTable(name = "item_panier",
//            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
//            inverseJoinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "panier_id"))
//    private Set<Panier> panier=new HashSet<>();


//    @ManyToMany
//    @JoinTable(name = "item_order",
//    joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
//    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
//    private Set<Order> orders=new HashSet<>();


    @Column(name = "deleted")
    private Boolean deleted= false;

    public Boolean getIsDeleted() {
        return deleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQttInStock() {
        return qttInStock;
    }

    public void setQttInStock(BigDecimal qttInStock) {
        this.qttInStock = qttInStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", product=" + product +
                ", qttInStock=" + qttInStock +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}
