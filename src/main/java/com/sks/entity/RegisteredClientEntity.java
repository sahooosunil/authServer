package com.sks.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "oauth_registered_client")
@Data
public class RegisteredClientEntity {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(unique = true, nullable = false)
    private String clientId;

    private String clientSecret;
    private String clientName;

    @Lob
    private String redirectUris;

    private String scopes;
    private String authorizationGrantTypes;

    private Long accessTokenTtl;
    private Long refreshTokenTtl;
}
