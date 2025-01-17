-- liquibase formatted sql

-- changeset k_korneeev:1726477659744-1
CREATE SEQUENCE IF NOT EXISTS transaction_seq START WITH 1 INCREMENT BY 50;

-- changeset k_korneeev:1726477659744-2
CREATE TABLE IF NOT EXISTS transaction
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);
