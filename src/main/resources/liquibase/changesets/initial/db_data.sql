--liquibase formatted sql

--changeset root:schema.create.add.authentication_data author:nikita.biloshytskyi contexts: init, data
    INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
        VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
        /*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
        'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

    INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
        VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
        /*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
        'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

--changeset root:schema.create.add.authority_data author:nikita.biloshytskyi contexts: init, data
    INSERT INTO AUTHORITY(ID, NAME) VALUES (1, 'GAME_CREATE');
    INSERT INTO AUTHORITY(ID, NAME) VALUES (2, 'GAME_INFO_READ');
    INSERT INTO AUTHORITY(ID, NAME) VALUES (3, 'GAME_PLAY');

    --changeset root:schema.create.add.users_data author:nikita.biloshytskyi contexts: init
    INSERT INTO USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
      VALUES (1, 'admin', /*kalah_game_admin*/'$2a$08$zlSijmL7Ci3gSznPpiJa0uMZH6qEa5zw6S3XlU7tLlv8iaVWj/3dO', FALSE, FALSE, FALSE, TRUE);

    INSERT INTO USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
      VALUES (2, 'read-only', /*read_only_pass*/'$2a$08$boKsJkkWv0p0E./8FaZbjOGq9zGAArTqGV/oin3MDGaaK6BsLTdRe', FALSE, FALSE, FALSE, TRUE);

    INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 1);
    INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 2);
    INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 3);

    INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 2);
