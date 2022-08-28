CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL NOT NULL UNIQUE PRIMARY KEY,
  name varchar(50) DEFAULT NULL,
  surname varchar(50) DEFAULT NULL,
  date_of_birth DATE
);

CREATE TABLE IF NOT EXISTS friendships (
  friendship_id SERIAL NOT NULL UNIQUE PRIMARY KEY,
  first_user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  second_user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  created_datetime TIMESTAMP NOT NULL DEFAULT now(),
  updated_datetime TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS posts (
  post_id SERIAL NOT NULL UNIQUE PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  text TEXT NOT NULL,
  created_datetime TIMESTAMP NOT NULL DEFAULT now(),
  updated_datetime TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS likes (
  like_id SERIAL NOT NULL UNIQUE PRIMARY KEY,
  post_id INTEGER NOT NULL REFERENCES posts(post_id) ON DELETE CASCADE,
  user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  created_datetime TIMESTAMP NOT NULL DEFAULT now(),
  updated_datetime TIMESTAMP NOT NULL DEFAULT now()
);

DROP FUNCTION IF EXISTS _get_users_friends;

CREATE OR REPLACE FUNCTION _get_users_friends()
  RETURNS TABLE (
    user_id BIGINT,
    friends BIGINT
)
AS $$
  SELECT first_user_id, count(first_user_id)
  FROM friendships
  GROUP BY first_user_id;
$$
LANGUAGE 'sql';

DROP FUNCTION IF EXISTS get_users_friends;

CREATE OR REPLACE FUNCTION get_users_friends(total_friends BIGINT)
  RETURNS TABLE (
    user_id BIGINT,
    friends BIGINT
)
AS $$
  SELECT *
  FROM _get_users_friends()
  WHERE friends > total_friends;
$$
LANGUAGE 'sql';


DROP FUNCTION IF EXISTS _get_users_likes;

CREATE OR REPLACE FUNCTION _get_users_likes(datetime_con TIMESTAMP)
  RETURNS TABLE (
    user_id BIGINT,
    total_likes BIGINT
)
AS $$
  SELECT user_id, count(user_id)
  FROM likes
  WHERE updated_datetime=datetime_con
  GROUP BY user_id;
$$
LANGUAGE 'sql';


DROP FUNCTION IF EXISTS get_users_likes;

CREATE OR REPLACE FUNCTION get_users_likes(datetime_con TIMESTAMP, total_likes_con BIGINT)
  RETURNS TABLE (
    user_id BIGINT,
    total_likes BIGINT
)
AS $$
  SELECT *
  FROM _get_users_likes(datetime_con)
  WHERE total_likes > total_likes_con;
$$
LANGUAGE 'sql';

