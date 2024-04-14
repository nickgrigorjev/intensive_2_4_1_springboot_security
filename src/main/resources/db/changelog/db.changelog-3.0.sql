--liquibase formatted sql

--changeset nsgrigorjev:1
CREATE TABLE role
(
    id INTEGER PRIMARY KEY,
    role_name VARCHAR(32)
);

--changeset nsgrigorjev:2
CREATE TABLE users_roles
(
    user_id BIGINT REFERENCES users(id),
    role_id INT REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);

