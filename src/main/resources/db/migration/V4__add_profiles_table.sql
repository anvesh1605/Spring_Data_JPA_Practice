CREATE TABLE profiles (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          bio VARCHAR(255),
                          phoneNumber VARCHAR(255),
                          dateOfBirth DATE,
                          loyalty_points INT NOT NULL DEFAULT 0,
                          user_id BIGINT NOT NULL,
                          CONSTRAINT fk_user_profile FOREIGN KEY (user_id) REFERENCES users(id)
);
