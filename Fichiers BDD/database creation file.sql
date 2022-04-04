CREATE USER 'amboara'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON actuamboara.* TO 'amboara'@'localhost';

CREATE DATABASE actuamboara CHARACTER SET 'utf8';

USE actuamboara;

CREATE TABLE Rubrique (
    id SMALLINT UNSIGNED AUTO_INCREMENT,
    intitule VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE Redacteur (
    id SMALLINT UNSIGNED AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE Actu (
    id SMALLINT UNSIGNED AUTO_INCREMENT,
    theme VARCHAR(50) NOT NULL,
    titre VARCHAR(150) NOT NULL,
    breve_description VARCHAR(255),
    contenu TEXT NOT NULL,
    date_publication DATE NOT NULL,
    id_rubrique SMALLINT UNSIGNED,
    id_redacteur SMALLINT UNSIGNED,

    PRIMARY KEY (id),
    CONSTRAINT fk_id_rubrique_actu FOREIGN KEY (id_rubrique) REFERENCES Rubrique(id) ON DELETE SET NULL,
    CONSTRAINT fk_id_redacteur_actu FOREIGN KEY (id_redacteur) REFERENCES Redacteur(id) ON DELETE SET NULL
) ENGINE=INNODB;