create table if not exists accounts (
    id            UUID PRIMARY KEY,
    user_id       UUID            NOT NULL,
    name          VARCHAR(100)    NOT NULL,
    balance       DECIMAL(19, 2)  DEFAULT 0.0,
    type          VARCHAR(50),
    currency      VARCHAR(10),

    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);