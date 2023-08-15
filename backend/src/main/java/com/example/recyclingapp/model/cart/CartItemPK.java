package com.example.recyclingapp.model.cart;


import com.example.recyclingapp.model.Product;
import com.example.recyclingapp.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItemPK implements Serializable {
    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartItemPK that = (CartItemPK) o;
        return Objects.equals(user, that.user) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, product);
    }
}
