--liquibase formatted sql

--changeset nsgrigorjev:1
ALTER TABLE users
ADD COLUMN password VARCHAR(128);

