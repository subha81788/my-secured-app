package com.subhashis.mysecureapp.repositories;

import com.subhashis.mysecureapp.models.Authority;
import com.subhashis.mysecureapp.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByAuthority(Authority authority);
}
