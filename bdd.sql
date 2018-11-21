CREATE DATABASE db_subtitles;
USE db_subtitles;

CREATE TABLE tbl_subtitle (
  id INT(5) AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  language VARCHAR(255) NOT NULL,
  content TEXT,
  CONSTRAINT pk_subtitle PRIMARY KEY(id)
);

CREATE TABLE tbl_translation (
  id INT(5) AUTO_INCREMENT,
  language VARCHAR(255) NOT NULL,
  id_origin INT(5),
  content TEXT,
  CONSTRAINT pk_translation PRIMARY KEY(id),
  CONSTRAINT fk_subtitle_origin foreign key (id_origin) REFERENCES tbl_subtitle(id)
);
