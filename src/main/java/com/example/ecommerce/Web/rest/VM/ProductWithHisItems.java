package com.example.ecommerce.Web.rest.VM;

import com.example.ecommerce.entities.Item;
import com.example.ecommerce.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductWithHisItems {

    Product product;
    UUID productId;
    Item item;
    List<Item> itemList = new ArrayList<>();

    public ProductWithHisItems() {
    }

    public ProductWithHisItems(Product product, List<Item> itemList) {
        this.product = product;
        this.itemList = itemList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public ProductWithHisItems(UUID productId, Item item) {
        this.productId = productId;
        this.item = item;
    }
}
