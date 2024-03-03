CREATE TABLE IF NOT EXISTS users (
                                     username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS authorities (
                                           username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
    );

-- Check if the index exists before attempting to create it
SELECT COUNT(1) INTO @indexCount FROM information_schema.statistics WHERE table_schema = DATABASE() AND table_name = 'authorities' AND index_name = 'ix_auth_username';

-- Create the index only if it doesn't exist
SET @indexSql = IF(@indexCount = 0, 'CREATE INDEX ix_auth_username ON authorities (username, authority)', 'SELECT 1');
PREPARE stmt FROM @indexSql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


