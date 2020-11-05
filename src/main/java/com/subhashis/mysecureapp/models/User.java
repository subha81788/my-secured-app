package com.subhashis.mysecureapp.models;

import com.subhashis.mysecureapp.validations.PasswordPolicy;
import com.subhashis.mysecureapp.validations.UniqueEmail;
import com.subhashis.mysecureapp.validations.UniqueUsername;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    @NotBlank(message = "Please provide an username")
    @Size(min = 3, max = 20)
    @UniqueUsername
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String username;

    @PasswordPolicy
    private String password;

    @NotBlank(message = "Please provide an email")
    @Email
    @Size(max = 50)
    @UniqueEmail
    @Indexed(unique = true)
    private String email;

    private Roles roles = new Roles();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, Set<Role> roles) {
        this(username, password, email);
        this.roles.setRoles(roles);
    }

    public User(String username, String password, String email, Roles roles) {
        this(username, password, email);
        this.roles = roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles.setRoles(roles);
    }
}
