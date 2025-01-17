-- liquibase formatted sql

-- changeset k_korneeev:1726477659747-1
CREATE SEQUENCE IF NOT EXISTS data_source_error_log_seq START WITH 1 INCREMENT BY 50;

-- changeset k_korneeev:1726477659747-2
CREATE TABLE IF NOT EXISTS data_source_error_log
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_data_source_error_log PRIMARY KEY (id)
);
