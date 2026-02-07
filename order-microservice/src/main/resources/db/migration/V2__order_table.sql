
-------------------- order enums ---------------------------------
CREATE TYPE SALES.ORDER_STATUS_ENUM AS ENUM(
	'Pending',
	'Confirmed',
	'Processing',
	'Cancelled',
	'Completed'
);

CREATE TYPE SALES.PAYMENT_STATUS_ENUM AS ENUM('Pending', 'Paid', 'Failed');
CREATE TYPE SALES.CURRENCY_ENUM AS ENUM('USD', 'EUR', 'GBP', 'MXN');

-------------------- order ---------------------------------
CREATE TABLE SALES.ORDERS (
                              ORDER_ID BIGSERIAL PRIMARY KEY,
                              ORDER_NUMBER VARCHAR(50) NOT NULL UNIQUE,
                              CUSTOMER_ID BIGINT NOT NULL,
                              ORDER_DATE TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                              ORDER_STATUS SALES.ORDER_STATUS_ENUM NOT NULL, --- enum
                              SUBTOTAL_AMOUNT NUMERIC(18, 2) NOT NULL,
                              DISCOUNT_AMOUNT NUMERIC(18, 2) NOT NULL DEFAULT 0,
                              TAX_AMOUNT NUMERIC(18, 2) NOT NULL DEFAULT 0,
                              SHIPPING_AMOUNT NUMERIC(18, 2) NOT NULL DEFAULT 0,
                              TOTAL_AMOUNT NUMERIC(18, 2) NOT NULL,
                              CURRENCY SALES.CURRENCY_ENUM NOT NULL DEFAULT 'USD', --- enum
                              PAYMENT_METHOD VARCHAR(50),
                              PAYMENT_STATUS SALES.PAYMENT_STATUS_ENUM, --- enum
                              SHIPPING_ADDRESS_ID UUID,
                              BILLING_ADDRESS_ID UUID,
                              NOTES TEXT,
                              SOURCE VARCHAR(50),
                              IP_ADDRESS INET,
                              CREATED_AT TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                              UPDATED_AT TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                              CREATED_BY VARCHAR(100),
                              UPDATED_BY VARCHAR(100),
    -- Control de concurrencia optimista
                              VERSION INTEGER NOT NULL DEFAULT 1,
                              IS_DELETED BOOLEAN NOT NULL DEFAULT FALSE,
                              DELETED_AT TIMESTAMPTZ,
                              CONSTRAINT CK_ORDERS_AMOUNTS_POSITIVE CHECK (
                                  SUBTOTAL_AMOUNT >= 0
                                      AND DISCOUNT_AMOUNT >= 0
                                      AND TAX_AMOUNT >= 0
                                      AND SHIPPING_AMOUNT >= 0
                                      AND TOTAL_AMOUNT >= 0
                                  )
    -- we are using enums now
    --                                       CONSTRAINT ck_orders_status
    --                                       CHECK (order_status IN ('Pending', 'Confirmed', 'Processing', 'Cancelled', 'Completed')),
    --
    --                               CONSTRAINT ck_orders_payment_status
    --                                   CHECK (payment_status IS NULL OR payment_status IN ('Pending', 'Paid', 'Failed')),
    --
    --                               CONSTRAINT ck_orders_currency
    --                                   CHECK (currency IN ('USD', 'EUR', 'GBP', 'MXN')),
);

-------------------- order index ---------------------------------
CREATE INDEX IX_ORDERS_CUSTOMER ON SALES.ORDERS (CUSTOMER_ID);

CREATE INDEX IX_ORDERS_STATUS ON SALES.ORDERS (ORDER_STATUS);

CREATE INDEX IX_ORDERS_DATE ON SALES.ORDERS (ORDER_DATE DESC);

CREATE INDEX IX_ORDERS_ACTIVE ON SALES.ORDERS (ORDER_DATE)
    WHERE
	IS_DELETED = FALSE;