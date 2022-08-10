package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private UUID id;
//    @Column(name="etat")
//    private String etat;
     @Column(name = "totalAmount")
     @JsonProperty("totalAmount")
      private Double totalAmount;
//    @JsonBackReference
    @ManyToOne
    private User user;
    @ManyToOne
    private ConfigLivraison configLivraison;
    @ManyToOne
    private Livraison livraison;

    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties(value = "order",allowSetters = true)
    Set<ProductOrder> productOrder;

//    @ManyToMany(mappedBy = "orders")
//    private Set<Item> items=new HashSet<>();

//    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = "orders")
//    private Set<OrderConfig> orderConfig = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @CreationTimestamp
    private Date created;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConfigLivraison getConfigLivraison() {
        return configLivraison;
    }

    public void setConfigLivraison(ConfigLivraison configLivraison) {
        this.configLivraison = configLivraison;
    }



    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Set<ProductOrder> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Set<ProductOrder> productOrder) {
        this.productOrder = productOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", user=" + user +
                ", configLivraison=" + configLivraison +
                ", livraison=" + livraison +
                '}';
    }
}
