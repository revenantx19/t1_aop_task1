-- liquibase formatted sql

-- changeset k_korneeev:1726477659741-1
CREATE SEQUENCE IF NOT EXISTS account_seq START WITH 1 INCREMENT BY 50;

-- changeset k_korneeev:1726477659741-2
CREATE TABLE IF NOT EXISTS account
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);
