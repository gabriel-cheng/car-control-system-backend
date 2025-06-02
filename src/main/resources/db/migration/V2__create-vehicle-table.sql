CREATE TABLE vehicle(
    vehicle_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    description TEXT NOT NULL,
    plate TEXT NOT NULL,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    color TEXT NOT NULL,
    client_id TEXT NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY(client_id)
    REFERENCES client(client_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);