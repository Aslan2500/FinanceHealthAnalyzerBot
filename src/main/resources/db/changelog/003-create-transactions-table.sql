create table if not exists transactions (
    id             UUID PRIMARY KEY,
    user_id        UUID NOT NULL,
    account_id     UUID NOT NULL,
    type           VARCHAR(20) NOT NULL,
    amount         DECIMAL(19, 2) NOT NULL,
    timestamp      TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_tx_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_tx_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);