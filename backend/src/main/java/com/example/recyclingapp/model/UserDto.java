package com.example.recyclingapp.model;


import com.example.recyclingapp.model.cart.CartItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "user_id")
    private List<CartItem> cartItems;
}
