ALTER TABLE client
    ADD COLUMN surname TEXT;

UPDATE client
    SET surname = 'Not Defined';

ALTER TABLE client
    ALTER COLUMN surname SET NOT NULL;

ALTER TABLE client
    RENAME COLUMN name TO firstname;