CREATE TABLE articles
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL,
    body TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)