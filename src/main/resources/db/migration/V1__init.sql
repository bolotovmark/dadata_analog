-- CREATE TABLE movies (
--     id SERIAL PRIMARY KEY,
--     ogrn VARCHAR(20),
--     inn VARCHAR(20),
--     kpp VARCHAR(20),
--     full_name TEXT,
--     short_name TEXT,
--     capital DOUBLE PRECISION,
--     status VARCHAR(10),
--     reg_date DATE,
--     address TEXT,
--     founders JSONB,
--     registration_info JSONB
-- );

CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    ogrn VARCHAR(20),
    inn VARCHAR(20),
    kpp VARCHAR(20),
    full_name TEXT,
    short_name TEXT
);