CREATE TABLE users (
  user_id BIGINT AUTO_INCREMENT,
  name VARCHAR,
  surname VARCHAR,
  email VARCHAR NOT NULL UNIQUE,
  creation_date DATE NOT NULL,
  is_deleted BOOLEAN,
  PRIMARY KEY (user_id)
);
