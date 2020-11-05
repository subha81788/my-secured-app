package com.subhashis.mysecureapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection="roles")
public class Role implements Serializable {
    @Id
    private String id;

    @JsonProperty("name")
    @Field("name")
    private Authority authority;

    public Role(Authority authority) {
        this.authority = authority;
    }
}
