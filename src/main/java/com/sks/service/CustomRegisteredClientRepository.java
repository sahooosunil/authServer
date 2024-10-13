package com.sks.service;

import com.sks.entity.RegisteredClientEntity;
import com.sks.repo.RegisteredClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository  {
    private final RegisteredClientRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {
        RegisteredClientEntity entity = new RegisteredClientEntity();
        entity.setClientId(registeredClient.getClientId());
        entity.setClientSecret(passwordEncoder.encode(registeredClient.getClientSecret()));
        entity.setClientName(registeredClient.getClientName());
        entity.setScopes(String.join(",", registeredClient.getScopes()));
        entity.setRedirectUris(String.join(",", registeredClient.getRedirectUris()));
        entity.setAuthorizationGrantTypes(String.join(",", registeredClient.getAuthorizationGrantTypes().stream()
                .map(AuthorizationGrantType::getValue).toList()));
        entity.setAccessTokenTtl(registeredClient.getTokenSettings().getAccessTokenTimeToLive().toSeconds());
        entity.setRefreshTokenTtl(registeredClient.getTokenSettings().getRefreshTokenTimeToLive().toSeconds());
        repository.save(entity);
    }

    @Override
    public RegisteredClient findById(String clientId) {
        return findByClientId(clientId);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<RegisteredClientEntity> optionalEntity = repository.findByClientId(clientId);
        return optionalEntity.map(entity ->
                RegisteredClient.withId(entity.getId())
                        .clientId(entity.getClientId())
                        .clientSecret(entity.getClientSecret())
                        .clientName(entity.getClientName())
                        .scopes(scopes -> scopes.addAll(Arrays.asList(entity.getScopes().split(","))))
                        .redirectUris(uris -> uris.addAll(Arrays.asList(entity.getRedirectUris().split(","))))
                        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                        .authorizationGrantTypes(grants -> grants.addAll(
                                Arrays.stream(entity.getAuthorizationGrantTypes().split(","))
                                        .map(AuthorizationGrantType::new).toList()))
                        .build()
        ).orElse(null);
    }
}
