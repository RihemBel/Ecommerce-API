//package com.example.ecommerce.entities;
//
//import com.fasterxml.jackson.annotation.*;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//@Entity
//@Table(name = "panier")
////@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
////        property  = "panier_id",
////        scope     = UUID.class)
//public class Panier {
//    @Id
//    @GeneratedValue (strategy = GenerationType.AUTO)
//    @Column(name="panier_id")
//    private UUID id;
//    @OneToOne
//    private User user;
//    @OneToOne
//    private Order order;
//
//    @ManyToMany
//    @JoinTable(name = "product_panier",
//            joinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "panier_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
//    private Set<Product> product=new HashSet<>();
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public Set<Product> getProduct() {
//        return product;
//    }
//
//    public void setProduct(Set<Product> product) {
//        this.product = product;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Panier)) return false;
//        Panier panier = (Panier) o;
//        return id == panier.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    @Override
//    public String toString() {
//        return "Panier{" +
//                "id=" + id +
//                ", user=" + user +
//                ", order=" + order +
//                ", product=" + product +
//                '}';
//    }
//}
