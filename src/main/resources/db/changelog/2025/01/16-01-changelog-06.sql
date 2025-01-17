-- liquibase formatted sql

-- changeset k_korneeev:1726477659745-1

ALTER TABLE transaction
    ADD IF NOT EXISTS amount DOUBLE PRECISION;
ALTER TABLE transaction
    ADD IF NOT EXISTS transaction_time TIMESTAMP;