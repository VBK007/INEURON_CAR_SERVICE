DROP TABLE IF EXISTS `users`;

DROP TABLE IF EXISTS `role`;

CREATE TABLE role (
    id int PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255)
);

INSERT INTO role (id, name, description)
VALUES (1, 'SUPER_ADMIN', 'SUPER ADMIN'),
       (2, 'CUSTOMER', 'CUSTOMER');

CREATE TABLE users (
    id binary(16) PRIMARY KEY,
    first_name varchar(255) NOT NULL,
    last_name varchar(255),
    mobile_number varchar(15),
    username varchar(50) NOT NULL,
    password char(60),
    failed_password_attempts int NOT NULL,
    is_account_expired boolean NOT NULL,
    is_account_locked boolean NOT NULL,
    is_credential_expired boolean NOT NULL,
    is_enabled boolean NOT NULL,
    role_id int,
    FOREIGN KEY (role_id) REFERENCES role(id),
    last_login_date timestamp NULL DEFAULT NULL,
    last_password_changed_at timestamp NULL DEFAULT NULL,
    last_locked_out_at timestamp NULL DEFAULT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by binary(16),
    updated_by binary(16),
    updated_at timestamp NULL
);

INSERT INTO users (id, first_name, last_name, mobile_number, username, password, failed_password_attempts, is_account_expired, is_account_locked, is_credential_expired, is_enabled, role_id, created_at) VALUES
(unhex('026c11faf85011eaadc10242ac120002'), 'Super Admin', null, null, 'superadmin', '$2a$09$PlM3plDgOffQSHd7AsR//e5qpDmBjLsQUIEMAjd98JWxruKVENhCq', 0, FALSE, FALSE, FALSE, TRUE, 1, now());

DROP TABLE IF EXISTS `spring_session`;

CREATE TABLE spring_session (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON spring_session (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON spring_session (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON spring_session (PRINCIPAL_NAME);

DROP TABLE IF EXISTS `spring_session_attributes`;

CREATE TABLE spring_session_attributes (
   SESSION_PRIMARY_ID CHAR(36) NOT NULL,
   ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
   ATTRIBUTE_BYTES BLOB NOT NULL,
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON spring_session_attributes (SESSION_PRIMARY_ID);
