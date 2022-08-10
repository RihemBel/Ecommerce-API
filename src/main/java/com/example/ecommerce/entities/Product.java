package com.example.ecommerce.entities;

import com.example.ecommerce.Config.Constants;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id",
        scope     = UUID.class)
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private UUID id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "has_variant")
    @JsonProperty("hasVariant")
    private Boolean hasVariant;
    @Column(name = "sku")
    @JsonProperty("sku")
    private String sku;
    @Column(name = "image")
    private String image;
    @NotNull
    @Column(name = "qttInStock", nullable = false, precision = 21, scale = 3)
    private BigDecimal qttInStock;

//    @JsonIgnoreProperties(value = "product",allowSetters = true)
    @ManyToOne
    //@JsonManagedReference
    private SubCategory subCategory;
    @ManyToOne
    @JsonIgnoreProperties(value = "product")
    //@JsonManagedReference
    private Mark mark;
//    @ManyToOne
//    @JsonIgnoreProperties(value = "product")
//    //@JsonManagedReference
//    private Category category;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "product")
    private Set<Item> item=new HashSet<>();
//    @ManyToMany(mappedBy = "product")
//    private Set<Panier> panier=new HashSet<>();

    @Column(name = "deleted")
    private Boolean deleted= false;

    @OneToMany(mappedBy = "product")
//    @JsonIgnoreProperties(value = "product",allowSetters = true)
            @JsonIgnore
    Set<ProductOrder> productOrder;

    public Boolean getIsDeleted() {
        return deleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Set<ProductOrder> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Set<ProductOrder> productOrder) {
        this.productOrder = productOrder;
    }

    public void setCategory(Category category) {
        category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }



    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getHasVariant() {
        return hasVariant;
    }

    public void setHasVariant(Boolean hasVariant) {
        this.hasVariant = hasVariant;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BigDecimal getQttInStock() {
        return qttInStock;
    }

    public void setQttInStock(BigDecimal qttInStock) {
        this.qttInStock = qttInStock;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", hasVariant=" + hasVariant +
                ", image='" + image + '\'' +
                ", qttInStock=" + qttInStock +
                ", subCategory=" + subCategory +
                ", mark=" + mark +
                ", item=" + item +
                '}';
    }
}
