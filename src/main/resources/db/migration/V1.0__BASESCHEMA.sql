DROP TABLE IF EXISTS `users`;

DROP TABLE IF EXISTS `role`;

DROP TABLE IF EXISTS WORKSHOP;

DROP TABLE IF EXISTS SERVICES;

DROP TABLE IF EXISTS BOOKINGS;

DROP TABLE IF EXISTS BOOKING_DETAILS;

DROP TABLE IF EXISTS ADDRESS;

DROP TABLE IF EXISTS VEHICLES;


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
    updated_at timestamp NULL,
    fcm_key varchar(255)
);

INSERT INTO users (id, first_name, last_name, mobile_number, username, password, failed_password_attempts, is_account_expired, is_account_locked, is_credential_expired, is_enabled, role_id, created_at) VALUES
(unhex('026c11faf85011eaadc10242ac120002'), 'Super Admin', null, null, 'superadmin', '$2a$09$PlM3plDgOffQSHd7AsR//e5qpDmBjLsQUIEMAjd98JWxruKVENhCq', 0, FALSE, FALSE, FALSE, TRUE, 1, now());

DROP TABLE IF EXISTS `spring_session`;

CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT `SPRING_SESSION_PK` PRIMARY KEY (`PRIMARY_ID`)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
   SESSION_PRIMARY_ID CHAR(36) NOT NULL,
   ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
   ATTRIBUTE_BYTES BLOB NOT NULL,
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID);

CREATE TABLE VEHICLE_TYPES (
        id BINARY(16) PRIMARY KEY,
        brand varchar(100) not null,
        model varchar(50) not null,
        vehicle_type varchar(50) not null

);

CREATE TABLE ADDRESS(
    id binary(16) PRIMARY KEY,
    pin_code varchar(12) not null ,
    city varchar(50) not null ,
    state varchar(50) not null ,
    country varchar(50) not null ,
    latitude double not null,
    longitude double not null,
    address1 varchar(50) not null,
    address2 varchar(50)
);

CREATE TABLE WORKSHOP (
    id BINARY(16) PRIMARY KEY,
    workshop_name varchar(100) not null,
    owner_id binary(16),
    address_id binary(16),
    FOREIGN KEY (owner_id) REFERENCES users(id),
    FOREIGN KEY (address_id) REFERENCES ADDRESS(id),
    vehicles_service_ability int not null
);

create table SERVICES (
    id binary(16) primary key,
    service_name varchar(50) not null,
    slots_needed int not null,
    service_price decimal not null,
    description varchar(500)
);

create table BOOKINGS (
    id binary(16) PRIMARY KEY,
    user_id binary(16) not null,
    workshop_id binary(16) not null,
    service_id binary(16) not null,
    vehicle_type_id binary(16) not null,
    vehicle_number varchar(20) not null,
    foreign key (user_id) references USERS(id),
    foreign key (workshop_id) references WORKSHOP(id),
    foreign key (service_id) references SERVICES(id),
    foreign key (vehicle_type_id) references VEHICLE_TYPES(id)
);

create table BOOKING_DETAILS(
    id binary(16) primary key,
    booking_id binary(16) not null,
    is_custom_service BOOLEAN not null,
    pickup_at_door_step BOOLEAN not null,
    pickup_date_time DATETIME,
    WALKIN_DATE_TIME DATETIME,
    foreign key (booking_id) references BOOKINGS(id)
);

CREATE TABLE SLOTS_BOOKED (
    id binary(16) primary key,
    slot_date DATE not null,
    slot1_booked_count int not null,
    slot2_booked_count int not null,
    workshop binary(16) not null,
    foreign key (workshop) references WORKSHOP(id)
);
