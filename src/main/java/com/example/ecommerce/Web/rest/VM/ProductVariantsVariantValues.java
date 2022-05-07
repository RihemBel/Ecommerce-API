package com.example.ecommerce.Web.rest.VM;

import java.util.List;

public class ProductVariantsVariantValues {

    String variantName;
    List<String> variantValueNames;

    public ProductVariantsVariantValues() {
    }

    public ProductVariantsVariantValues(String variantName, List<String> variantValueNames) {
        this.variantName = variantName;
        this.variantValueNames = variantValueNames;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public List<String> getVariantValueNames() {
        return variantValueNames;
    }

    public void setVariantValueNames(List<String> variantValueNames) {
        this.variantValueNames = variantValueNames;
    }
}
