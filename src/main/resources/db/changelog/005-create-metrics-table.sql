create table if not exists finance_metrics (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    metric_key VARCHAR(100) NOT NULL,
    metric_value DECIMAL(19, 4),
    updated_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_metric_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);