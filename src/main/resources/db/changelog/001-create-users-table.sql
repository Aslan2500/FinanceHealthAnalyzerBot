create table if not exists users (
    id UUID          PRIMARY KEY,
    telegram_id      BIGINT          NOT NULL UNIQUE,
    name             VARCHAR(100),
    created_at       TIMESTAMP       DEFAULT NOW()
);