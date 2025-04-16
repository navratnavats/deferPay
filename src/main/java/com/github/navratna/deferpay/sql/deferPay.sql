
use deferpay;


-- USERS TABLE
CREATE TABLE users (
                       id int PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(150) UNIQUE NOT NULL,
                       phone VARCHAR(20) UNIQUE NOT NULL,
                       username VARCHAR(36) UNIQUE NOT NULL,
                       credit_limit DECIMAL(10, 2) DEFAULT 10000,
                       available_credit_limit DECIMAL(10, 2) DEFAULT 10000,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- MERCHANTS TABLE
CREATE TABLE merchants (
                           id int PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           email VARCHAR(150) UNIQUE NOT NULL,
                           phone VARCHAR(20) UNIQUE NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCTS TABLE
CREATE TABLE products (
                          id int PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          cost DECIMAL(10, 2) NOT NULL,
                          quantity INT NOT NULL,
                          merchant_id CHAR(36) REFERENCES merchants(id),
                          is_active BOOLEAN DEFAULT TRUE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ORDERS TABLE
CREATE TABLE orders (
                        id int PRIMARY KEY,
                        user_id CHAR(36) REFERENCES users(id),
                        merchant_id CHAR(36) REFERENCES merchants(id),
                        amount DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(20) CHECK (status IN ('PLACED', 'PAID', 'BNPL', 'CANCELLED')) NOT NULL,
                        payment_type VARCHAR(20) CHECK (payment_type IN ('PAYNOW', 'BNPL')) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ORDER ITEMS TABLE
CREATE TABLE order_items (
                             id int PRIMARY KEY,
                             order_id CHAR(36) REFERENCES orders(id),
                             product_id CHAR(36) REFERENCES products(id),
                             product_name VARCHAR(100),
                             price_per_unit DECIMAL(10, 2),
                             quantity INT NOT NULL
);

-- BNPL TABLE
CREATE TABLE bnpl (
                              id int PRIMARY KEY,
                              user_id CHAR(36) REFERENCES users(id),
                              order_id CHAR(36) UNIQUE REFERENCES orders(id),
                              amount DECIMAL(10, 2),
                              remaining_amount DECIMAL(10, 2),
                              status VARCHAR(20) CHECK (status IN ('ACTIVE', 'PAID', 'OVERDUE', 'DEFAULTED')),
                              due_date DATE NOT NULL,
                              paid_date DATE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PAYMENTS TABLE
CREATE TABLE payments (
                          id int PRIMARY KEY,
                          user_id CHAR(36) REFERENCES users(id),
                          order_id CHAR(36) REFERENCES orders(id),
                          amount DECIMAL(10, 2),
                          type VARCHAR(30) CHECK (type IN ('PAYNOW', 'BNPL_REPAYMENT')),
                          status VARCHAR(20) CHECK (status IN ('SUCCESS', 'FAILED')),
                          paid_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- INDEXES
CREATE INDEX idx_user_id ON orders(user_id);
CREATE INDEX idx_order_id ON order_items(order_id);
CREATE INDEX idx_user_credit ON users(available_credit_limit);
CREATE INDEX idx_transaction_due ON bnpl(due_date);
