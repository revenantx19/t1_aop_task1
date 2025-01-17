-- liquibase formatted sql

-- changeset k_korneeev:1726477659748-1

ALTER TABLE data_source_error_log
    ADD IF NOT EXISTS stack_trace VARCHAR (255);
ALTER TABLE data_source_error_log
    ADD IF NOT EXISTS message VARCHAR (255);
ALTER TABLE data_source_error_log
    ADD IF NOT EXISTS method_signature VARCHAR (255);