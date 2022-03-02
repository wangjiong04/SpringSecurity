create schema IF NOT EXISTS spring;

CREATE TABLE IF NOT EXISTS spring.users (
  id INT NOT NULL,
  username VARCHAR(45) NULL,
  password VARCHAR(256) NULL,
  enabled INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS spring.authorities (
  id INT NOT NULL,
  username VARCHAR(45) NULL,
  authority VARCHAR(45) NULL,
  PRIMARY KEY (id));
