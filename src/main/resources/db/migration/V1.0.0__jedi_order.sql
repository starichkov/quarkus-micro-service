CREATE TABLE jedi_order
(
    id    BIGSERIAL   NOT NULL,
    name  VARCHAR(64) NOT NULL UNIQUE,
    title VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);