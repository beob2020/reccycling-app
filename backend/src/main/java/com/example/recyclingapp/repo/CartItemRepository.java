package com.example.recyclingapp.repo;

import com.example.recyclingapp.model.cart.CartItem;
import com.example.recyclingapp.model.cart.CartItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemPK> {


}
