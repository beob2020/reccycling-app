package com.example.recyclingapp.service;

import com.example.recyclingapp.exceptions.CartItemAlreadyExistException;
import com.example.recyclingapp.exceptions.CartItemDoesNotExistException;
import com.example.recyclingapp.model.cart.CartItem;
import com.example.recyclingapp.repo.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemByUserIdAndProductId(Long userId, Long productId) {
        for (CartItem cartItem : getAllCartItems()) {
            if (userId.equals(cartItem.cartItemPK.getUser().getId()) && productId.equals(cartItem.cartItemPK.getProduct().getId())) {
                return cartItem;
            }
        }
        throw new CartItemDoesNotExistException("Cart item with user id: "
                + userId + " and product id: " + productId +
                " does not exist.");
    }

    public CartItem addCartItem(CartItem cartItem) {
        for (CartItem item : getAllCartItems()) {
            if (item.equals(cartItem)) {
                throw new CartItemAlreadyExistException("Cart item w/ user id " + cartItem.cartItemPK.getUser().getId()
                        + " and product id " + cartItem.cartItemPK.getProduct().getId() + " already exists.");
            }
        }
        return this.cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        Optional<CartItem> matchedItem = getAllCartItems().stream()
                .filter(item -> item.equals(cartItem))
                .findFirst();

        if (matchedItem.isPresent()) {
            CartItem itemToUpdate = matchedItem.get();
            itemToUpdate.setQuantity(cartItem.getQuantity());
            return cartItemRepository.save(itemToUpdate);
        }
        throw new CartItemDoesNotExistException("Cart item w/ user id " + cartItem.cartItemPK.getUser().getId() + " and product id " +
                cartItem.cartItemPK.getProduct().getId() + " does not exist.");
    }

    public void deleteCartItemWithUserIdAndProductId(Long userId, Long productId) {
        for (CartItem item : getAllCartItems()) {
            if (userId.equals(item.cartItemPK.getUser().getId())
                    && productId.equals(item.cartItemPK.getProduct().getId())) {
                cartItemRepository.delete(item);
                return;
            }
        }
        throw new CartItemDoesNotExistException("Cart item w/ user id " + userId + " and product id " + productId + " does not exist.");
    }
}