package com.example.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ProductOrderPk implements Serializable {

    private static final long serialVersionUID = -4409672651457881562L;



    @Column(name = "order_id")
    private UUID orderId;

    @Column(name="product_id")
    private UUID productId;

    public ProductOrderPk() {
    }

    public ProductOrderPk(UUID orderId, UUID productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductOrderPk{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
