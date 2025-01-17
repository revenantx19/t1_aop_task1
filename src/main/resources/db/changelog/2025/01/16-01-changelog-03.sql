-- liquibase formatted sql

-- changeset k_korneeev:1726477659742-1
ALTER TABLE account
    ADD IF NOT EXISTS account_type VARCHAR(255);
ALTER TABLE account
    ADD IF NOT EXISTS balance DOUBLE PRECISION;


