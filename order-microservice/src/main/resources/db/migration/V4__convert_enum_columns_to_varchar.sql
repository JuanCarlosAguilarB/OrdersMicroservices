-- Convierte las columnas enum a VARCHAR para compatibilidad con R2DBC.
-- R2DBC envía los valores como varchar y PostgreSQL rechazaba la asignación
-- desde varchar a tipos enum personalizados (sales.payment_status_enum, etc.)

ALTER TABLE sales.orders
    ALTER COLUMN order_status TYPE varchar(50) USING order_status::text;

ALTER TABLE sales.orders
    ALTER COLUMN payment_status TYPE varchar(50) USING payment_status::text;

ALTER TABLE sales.orders
    ALTER COLUMN currency TYPE varchar(50) USING currency::text;

-- Restaurar el default de currency que se pierde al cambiar el tipo
ALTER TABLE sales.orders
    ALTER COLUMN currency SET DEFAULT 'USD';

ALTER TABLE sales.orders RENAME COLUMN order_id TO id;