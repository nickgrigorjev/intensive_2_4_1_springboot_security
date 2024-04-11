--liquibase formatted sql

--changeset nsgrigorjev:1
CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    age SMALLINT CHECK (age > 0)
);
--rollback DROP TABLE users;