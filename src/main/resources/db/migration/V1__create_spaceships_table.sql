-- V1__create_spaceships_table.sql

CREATE TABLE spaceships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    cost_in_credits INTEGER,
    length DECIMAL(10,2),
    max_atmosphering_speed INTEGER,
    crew INTEGER,
    passengers INTEGER,
    cargo_capacity BIGINT,
    consumables VARCHAR(255),
    hyperdrive_rating DECIMAL(3,1),
    mglt INTEGER,
    starship_class VARCHAR(255),
    films VARCHAR(255),
    created TIMESTAMP,
    edited TIMESTAMP,
    url VARCHAR(255)
);
