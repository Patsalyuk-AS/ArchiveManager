DROP TABLE IF EXISTS Documents;
DROP TABLE IF EXISTS Boxes;

CREATE TABLE Boxes (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(50) NOT NULL,
  CODE VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE Documents (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(50) NOT NULL,
  CODE VARCHAR(20) NOT NULL UNIQUE,
  BOX INT,
  foreign key (BOX) references Boxes(ID)
);

