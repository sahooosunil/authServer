package com.sks.controller;

import com.sks.dto.RegisteredClientDto;
import com.sks.service.CustomRegisteredClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client-api")
public class ClientRegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final CustomRegisteredClientRepository clientRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody RegisteredClientDto dto) {
        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(dto.getClientId())
                .clientSecret(passwordEncoder.encode(dto.getClientSecret()))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(dto.getRedirectUri())
                .scope(dto.getScope())
                .build();
        clientRepository.save(client);  // <-- save() is called here
        return ResponseEntity.ok("Client registered");
    }
}
