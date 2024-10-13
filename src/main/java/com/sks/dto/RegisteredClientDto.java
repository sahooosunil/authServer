package com.sks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredClientDto {
    private String clientId;
    private String clientSecret;
    private String clientName; // Optional, if you want to store client name
    private String authorizationGrantTypes; // Comma-separated list of grant types
    private String redirectUri; // Redirect URI
    private String scope; // Comma-separated list of scopes
    private Long accessTokenTtl; // Access token time-to-live in seconds
    private Long refreshTokenTtl; // Refresh token time-to-live in seconds
}
