CREATE TABLE user_session(
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    token UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE INDEX idx_user_token ON user_session (token);
CREATE INDEX idx_user_id ON user_session (user_id);