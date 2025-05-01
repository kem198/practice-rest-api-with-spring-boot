DROP TABLE IF EXISTS example_table;

CREATE TABLE
    IF NOT EXISTS example_table (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO
    example_table (name)
VALUES
    ('Example 1'),
    ('Example 2'),
    ('Example 3');
