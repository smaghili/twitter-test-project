SET SCHEMA 'user_schema';
CREATE TABLE IF NOT EXISTS users(
  id SERIAL PRIMARY KEY ,
  username varchar(50),
  password varchar(50)
);

CREATE TABLE IF NOT EXISTS tweet(
  id SERIAL PRIMARY KEY ,
  tweet_text varchar,
  user_id INT references users(id)
);