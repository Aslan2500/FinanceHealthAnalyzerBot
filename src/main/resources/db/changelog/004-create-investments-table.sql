create table if not exists investments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    amount_invested DECIMAL(19, 2),

    CONSTRAINT fk_invest_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);