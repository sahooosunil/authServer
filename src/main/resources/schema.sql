CREATE DATABASE oauth_demo;
USE DATABASE oauth_demo;
CREATE TABLE oauth_registered_client (
    id VARCHAR(255) PRIMARY KEY,
    client_id VARCHAR(255) UNIQUE NOT NULL,
    client_secret VARCHAR(255) NOT NULL,
    client_name VARCHAR(255) NOT NULL,
    authorization_grant_types VARCHAR(255),
    redirect_uris TEXT,
    scopes VARCHAR(255),
    access_token_ttl BIGINT,
    refresh_token_ttl BIGINT
);