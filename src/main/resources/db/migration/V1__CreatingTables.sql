-- Create roles table
CREATE TABLE roles (
                       id INTEGER PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

-- Create player table
CREATE TABLE player (
                        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                        nick_name VARCHAR(255),
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        photo_url VARCHAR(255),
                        telegram_id BIGINT,
                        balance INTEGER,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create player_roles junction table
CREATE TABLE player_roles (
                              player_id BIGINT,
                              role_id INTEGER,
                              PRIMARY KEY (player_id, role_id),
                              FOREIGN KEY (player_id) REFERENCES player(id),
                              FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create games table
CREATE TABLE games (
                       game_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       status VARCHAR(255),
                       pot INTEGER,
                       combination VARCHAR(255),
                       player_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (player_id) REFERENCES player(id)
);



-- Create indexes for performance
CREATE INDEX idx_player_telegram_id ON player(telegram_id);
CREATE INDEX idx_games_player_id ON games(player_id);
CREATE INDEX idx_player_roles_player_id ON player_roles(player_id);
CREATE INDEX idx_player_roles_role_id ON player_roles(role_id);