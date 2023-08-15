package com.example.recyclingapp.model;

import com.example.recyclingapp.model.cart.CartItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tl_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="user_id" )
    private Long Id;

    @Column(unique = true, nullable = false, length = 35)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(unique = true, nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String address;

    @Column(nullable = false, length = 128)
    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "cartItemPK.user", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
