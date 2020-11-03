package com.subhashis.mysecureapp.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(min = 3, max = 20)
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String username;

    @NotBlank
    @Size(min=5, max=15)
    @Transient
    private String password;

    @NotBlank
    @Email
    @Size(max = 50)
    @Indexed(unique = true)
    private String email;

    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
