-- Create database
CREATE DATABASE IF NOT EXISTS reimbursement_db;

-- Use the database
USE reimbursement_db;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Create reimbursements table
CREATE TABLE IF NOT EXISTS reimbursements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    description TEXT NOT NULL,
    submission_date DATETIME NOT NULL,
    approval_date DATETIME,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert a sample admin user (password: admin123)
INSERT INTO users (username, password, role) VALUES
('admin', '$2y$10$xYDN6wu.wI9AzLqzC/AEw.t8uhpCFAu4NWeWKjFuyAXseRWlvALr2', 'ADMIN'),
('user1', '$2y$10$xYDN6wu.wI9AzLqzC/AEw.t8uhpCFAu4NWeWKjFuyAXseRWlvALr2', 'EMPLOYEE'),
('user2', '$2y$10$xYDN6wu.wI9AzLqzC/AEw.t8uhpCFAu4NWeWKjFuyAXseRWlvALr2', 'MANAGER');

SELECT *
FROM users;

truncate table users;

SELECT *
FROM reimbursements;

Drop Table users;
Drop table reimbursements;

### SQL untuk menghapus foreign key;

ALTER TABLE reimbursements
DROP FOREIGN KEY FKlcwibw3156i503pohkpyplals;

## untuk melihat constraint_name
SHOW CREATE TABLE reimbursements;