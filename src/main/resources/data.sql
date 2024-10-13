
INSERT INTO oauth_registered_client (
    id, client_id, client_secret, client_name,
    authorization_grant_types, redirect_uris, scopes,
    access_token_ttl, refresh_token_ttl
) VALUES (
    '1e73b4ca-5b77-4e8b-9b7a-4459c64d4f3e',
    'my-client',
    '{bcrypt}password'
    'My OAuth Client',
    'authorization_code,client_credentials,refresh_token',
    'http://localhost:3000/callback,http://localhost:3000/login',
    'read,write',
    3600,
    86400
);