ALTER TABLE user_account MODIFY userID BIGINT NOT NULL;
ALTER TABLE user_account MODIFY username varchar(255) NOT NULL;
ALTER TABLE user_account MODIFY password varchar(255) NOT NULL;
ALTER TABLE user_account ADD COLUMN email varchar(255) NOT NULL;
