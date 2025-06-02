ALTER TABLE client
    DROP COLUMN phone RESTRICT;

CREATE TABLE phone(
    phone_id TEXT PRIMARY KEY NOT NULL,
    phone TEXT NOT NULL,
    client_id TEXT NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY(client_id)
        REFERENCES client(client_id)
);