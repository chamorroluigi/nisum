DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id VARCHAR(250)  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  token VARCHAR(100),
  isActive BOOLEAN
);



DROP TABLE IF EXISTS telefono;
  
CREATE TABLE telefono (
  id BIGINT  AUTO_INCREMENT  PRIMARY KEY,
  number INT ,
  citycode INT ,
  contrycode INT,
  userid VARCHAR(250),
  FOREIGN KEY (userid) REFERENCES usuario(id)
);
 