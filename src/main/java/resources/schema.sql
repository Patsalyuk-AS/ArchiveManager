DROP TABLE IF EXISTS Documents;
DROP TABLE IF EXISTS Boxes;

CREATE TABLE Bankcards (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  card_number VARCHAR(18) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  exp_date VARCHAR(5) NOT NULL,
  cvi VARCHAR(3) NOT NULL,
  pin INT NOT NULL,
  type VARCHAR(6) NOT NULL
);

CREATE TABLE Balances (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance NUMERIC DEFAULT 0,
  currency VARCHAR(3) NOT NULL,
  card_id INT NOT NULL,
    foreign key (card_id) references Bankcards(id)
);