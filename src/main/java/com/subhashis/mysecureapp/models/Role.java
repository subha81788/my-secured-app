package com.subhashis.mysecureapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection="roles")
public class Role {
    @Id
    private String id;

    @Field("name")
    private Authority authority;

    public Role(Authority authority) {
        this.authority = authority;
    }
}
