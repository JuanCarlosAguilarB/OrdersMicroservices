CREATE TABLE catalogue.product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    brand VARCHAR(100),
    category_id BIGINT,
    price NUMERIC(19,2),
    sku VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    is_active BOOLEAN DEFAULT true,
    version INT DEFAULT 0
);

CREATE INDEX idx_product_category_id ON catalogue.product(category_id);
CREATE UNIQUE INDEX idx_product_sku ON catalogue.product(sku);
