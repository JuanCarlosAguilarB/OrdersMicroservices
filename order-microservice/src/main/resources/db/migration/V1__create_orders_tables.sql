CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_email VARCHAR(255) NOT NULL,
                        total_amount NUMERIC(19, 2) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        currency VARCHAR(10) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_orders_customer_email
    ON orders(customer_email);

CREATE INDEX idx_orders_status
    ON orders(status);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             sku VARCHAR(100) NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             quantity INTEGER NOT NULL CHECK (quantity > 0),
                             unit_price NUMERIC(19, 2) NOT NULL,
                             subtotal NUMERIC(19, 2) NOT NULL,

                             CONSTRAINT fk_order_items_order
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders(id)
                                     ON DELETE CASCADE
);

CREATE INDEX idx_order_items_order_id
    ON order_items(order_id);

CREATE INDEX idx_order_items_sku
    ON order_items(sku);
