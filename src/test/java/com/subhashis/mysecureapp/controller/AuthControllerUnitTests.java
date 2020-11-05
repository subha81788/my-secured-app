package com.subhashis.mysecureapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhashis.mysecureapp.controllers.AuthController;
import com.subhashis.mysecureapp.jwt.JwtUtils;
import com.subhashis.mysecureapp.models.Authority;
import com.subhashis.mysecureapp.models.Role;
import com.subhashis.mysecureapp.models.User;
import com.subhashis.mysecureapp.repositories.RoleRepository;
import com.subhashis.mysecureapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
class AuthControllerUnitTests {

    AuthController authController;

    MockMvc mockMvc;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    PasswordEncoder encoder;

    @MockBean
    JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(authenticationManager, userRepository, roleRepository, encoder, jwtUtils);

        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void posTestSignupNewUserWithoutRoleStatus200() throws Exception {

        var user = new User("subha81788",
                "pa$$word@123",
                "subha81788@gmail.com");
                //new HashSet<>(List.of(new Role(Authority.USER), new Role(Authority.ADMIN))));

        given(userRepository.save(any(User.class))).willReturn(user);

        this.mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void posTestSignupNewUserWithRoleStatus200() throws Exception {

        var user = new User("subha81788",
                "pa$$word@123",
                "subha81788@gmail.com");
        user.setRoles(new HashSet<>(List.of(new Role(Authority.USER))));

        given(userRepository.save(any(User.class))).willReturn(user);

        var userStr = objectMapper.writeValueAsString(user);
        log.info("userAsString = " + userStr);

        this.mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }
}
