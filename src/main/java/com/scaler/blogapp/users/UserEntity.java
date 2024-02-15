package com.scaler.blogapp.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity(name = "users")
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String username;

    @Column(name = "email", nullable = false)
    @NonNull
    private String email;

    @Column(name = "bio", nullable = true)
    @Nullable
    private String bio;

    @Column(name = "image", nullable = true)
    @Nullable
    private String image;


}
