package com.example.recyclingapp.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tl_cart_items")
public class CartItem {
    @JsonIgnore
    @EmbeddedId
    public CartItemPK cartItemPK;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime dateAdded;

    @Column(nullable = false)
    private int quantity;

    @PrePersist
    private void init() {
        this.dateAdded = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartItem that = (CartItem) o;
        return Objects.equals(cartItemPK.getUser().getId(), that.cartItemPK.getUser().getId()) &&
                Objects.equals(cartItemPK.getProduct().getId(), that.cartItemPK.getProduct().getId());
    }
}