package com.subhashis.mysecureapp.bootstraps;

import com.subhashis.mysecureapp.models.Authority;
import com.subhashis.mysecureapp.models.Role;
import com.subhashis.mysecureapp.models.User;
import com.subhashis.mysecureapp.repositories.RoleRepository;
import com.subhashis.mysecureapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        empty();
        seed();
    }

    public void empty() {
        mongoTemplate.dropCollection(Role.class);
        mongoTemplate.dropCollection(User.class);
        log.info("Dropped existing data from DB");
    }

    public void seed() {
        var roles = List.of(new Role(Authority.USER),
                new Role(Authority.MODERATOR),
                new Role(Authority.ADMIN));
        roleRepository.insert(roles);

        var user1 = new User("subhnath",
                encoder.encode("ilovecoding"),
                "subhashis.a.nath@capgemini.com",
                new HashSet<>(List.of(new Role(Authority.ADMIN))));

        //userRepository.insert(user1);

        log.info("Data initialized");
    }
}
