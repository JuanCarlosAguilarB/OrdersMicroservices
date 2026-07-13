ALTER TABLE IF EXISTS sales.orders
    ADD COLUMN is_active boolean NOT NULL DEFAULT true;


ALTER TYPE sales.payment_status_enum RENAME VALUE 'Pending' TO 'PENDING';
ALTER TYPE sales.payment_status_enum RENAME VALUE 'Paid'    TO 'PAID';
ALTER TYPE sales.payment_status_enum RENAME VALUE 'Failed'  TO 'FAILED';


ALTER TYPE sales.order_status_enum RENAME VALUE 'Pending'     TO 'PENDING';
ALTER TYPE sales.order_status_enum RENAME VALUE 'Confirmed'   TO 'CONFIRMED';
ALTER TYPE sales.order_status_enum RENAME VALUE 'Processing'  TO 'PROCESSING';
ALTER TYPE sales.order_status_enum RENAME VALUE 'Cancelled'   TO 'CANCELLED';
ALTER TYPE sales.order_status_enum RENAME VALUE 'Completed'   TO 'COMPLETED';
