CREATE TABLE registered_user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(255) NOT NULL,
    username VARCHAR(20) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(10) NOT NULL,
    salt UUID NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE INDEX idx_user_username ON registered_user (username);

INSERT INTO registered_user (fullname, username, user_password, user_role, salt) VALUES (
    'Ale Olivati',
    'aleolivati',
    '6tMvv7e337kvMK0YWCm4eENTgL4FHJW7N+p7PrUqeuKcwWR2gW9CwCAgbBl8arc/9w8C9N8poNqkVZ+/jnBzqw==',
    'admin',
    '15ae8926-abed-4b73-acb8-6b4d180d4184'
)
