--- Clean DB ---
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;

--- Create DB ---
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

CREATE TABLE role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    role_id      INTEGER REFERENCES role (id),
    username     VARCHAR(255) UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    first_name   VARCHAR(255)        NOT NULL,
    last_name    VARCHAR(255)        NOT NULL,
    phone        VARCHAR(255),
    address      VARCHAR(255),
    city         VARCHAR(255),
    zip_code     VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_role ON users (role_id);

-- ADD CONSTANT DATA CATEGORY
INSERT INTO category (name, code)
VALUES ('Electronics', 'ELECTRONICS');
INSERT INTO category (name, code)
VALUES ('Books', 'BOOKS');

-- ADD CONSTANT DATA ROLE
INSERT INTO role (name)
VALUES ('ROLE_USER');
INSERT INTO role (name)
VALUES ('ROLE_ADMIN');


-- ADD DEVELOPMENT DATA PRODUCT
INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1234', 'iPhone 5s',
        'Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera', 500,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1235', 'Dell Inspiron', 'Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors',
        700, 'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (2, 'P1236', 'Samsung Galaxy Tab 4',
        'Samsung Galaxy Tab 4 T231 Tablet (7-inch, 8GB, WiFi, 3G, Voice Calling), Black', 400,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (2, 'P1237', 'One Plus One', 'OnePlus One (Sandstone Black, 64GB)', 350,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (2, 'P1238', 'Nexus 9', 'HTC Google Nexus 9 (8.9-inch, 16GB, WiFi, LTE, Voice Calling), Indigo Black', 450,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (2, 'P1239', 'Lenovo Yoga', 'Lenovo Yoga 8 Tablet (8-inch, 16GB, WiFi, 3G, Voice Calling), Silver', 300,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1240', 'HP Notebook', 'HP 15-r063tu Notebook (4th Gen Ci3/ 4GB/ 500GB/ Free DOS) (K8T73PA)', 550,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1241', 'Lenovo Thinkpad', 'Lenovo Thinkpad X1 Carbon 20A80056IG Laptop (4th Gen Ci5/ 4GB/ 256GB/ Win8.1)',
        750, 'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1242', 'Dell Inspiron', 'Dell Inspiron 15 3542 Notebook (4th Gen Ci5/ 4GB/ 1TB/ Win8.1/ 2GB Graph)', 650,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1243', 'Apple MacBook',
        'Apple MacBook Pro MD101HN/A 13-inch Laptop (Core i5/4GB/500GB/Mac OS Mavericks/Intel HD Graphics)', 850,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1244', 'HP Envy', 'HP Envy 15-k006tx Notebook (4th Gen Ci5/ 8GB/ 1TB/ Win8.1/ 2GB Graph/ Touch)', 950,
        'assets/images/products/placeholder.png', TRUE, 100);

INSERT INTO product (category_id, code, name, description, unit_price, image_url, active, units_in_stock)
VALUES (1, 'P1245', 'Dell Inspiron', 'Dell Inspiron 15 3542 Notebook (4th Gen Ci7/ 8GB/ 1TB/ Win8.1/ 2GB Graph)', 1050,
        'assets/images/products/placeholder.png', TRUE, 100);

