package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "productOrder")
public class ProductOrder implements Serializable {


    @EmbeddedId
    private ProductOrderPk productOrderPk;

/*    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;*/

    @Column(name = "nbProd")
    private int nbProd;

    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    @MapsId("orderId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @MapsId("productId")
    Product product;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderPk productOrderPk, int nbProd, Double price, Order order, Product product) {
        this.productOrderPk = productOrderPk;
        this.nbProd = nbProd;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public ProductOrderPk getProductOrderPk() {
        return productOrderPk;
    }

    public void setProductOrderPk(ProductOrderPk productOrderPk) {
        this.productOrderPk = productOrderPk;
    }

    public int getNbProd() {
        return nbProd;
    }

    public void setNbProd(int nbProd) {
        this.nbProd = nbProd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
