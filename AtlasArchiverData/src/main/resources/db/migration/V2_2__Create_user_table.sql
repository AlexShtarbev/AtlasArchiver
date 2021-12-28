CREATE DATABASE IF NOT EXISTS atlas_archiver;

CREATE TABLE IF NOT EXISTS users (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    UNIQUE (email)
);
