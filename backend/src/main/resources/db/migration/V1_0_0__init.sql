--- Clean DB ---
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;

CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    code VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255)        NOT NULL
);

CREATE TABLE product
(
    id             SERIAL PRIMARY KEY,
    category_id    INTEGER REFERENCES category (id),
    code           VARCHAR(255) NOT NULL,
    name           VARCHAR(255) NOT NULL,
    description    TEXT,
    unit_price     DECIMAL(19, 2),
    image_url      VARCHAR(255),
    active         BOOLEAN      NOT NULL,
    units_in_stock INTEGER      NOT NULL,
    date_created   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_product_category ON product (category_id);


-- CATEGORY
INSERT INTO category (name, code)
VALUES ('Electronics', 'ELECTRONICS');
INSERT INTO category (name, code)
VALUES ('Books', 'BOOKS');