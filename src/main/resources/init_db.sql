CREATE TABLE products (
    product_id BIGINT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE roles (
    role_id BIGINT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_id)
);

INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

CREATE TABLE users (
    user_id BIGINT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    salt VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE users_roles (
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
    CONSTRAINT UC_UserRole UNIQUE (user_id, role_id)
);

CREATE TABLE orders (
    order_id BIGINT UNSIGNED AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (order_id),
    CONSTRAINT FK_OrderUser
        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE orders_products (
    order_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

CREATE TABLE shopping_carts (
    shopping_cart_id BIGINT UNSIGNED AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (shopping_cart_id),
    CONSTRAINT FK_ShoppingCartUser
        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE shopping_carts_products (
    shopping_cart_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts(shopping_cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);
