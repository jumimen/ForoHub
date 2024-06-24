CREATE TABLE users(
    id BIGINT NOT NULL auto_increment,
    login VARCHAR(10) NOT NULL,
    password VARCHAR(300),
    PRIMARY KEY(id)
);