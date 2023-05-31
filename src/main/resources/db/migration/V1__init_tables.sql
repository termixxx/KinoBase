CREATE TABLE profile
(
    id       SERIAL8 PRIMARY KEY,
    login    TEXT   NOT NULL UNIQUE,
    password TEXT   NOT NULL,
    email    TEXT   NOT NULL,
    nickname TEXT   NOT NULL,
    role     TEXT[] NOT NULL
);

CREATE TABLE person
(
    id         SERIAL8 PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    position   TEXT NOT NULL
);


CREATE TABLE content
(
    id           SERIAL8 PRIMARY KEY,
    genres       TEXT[]      NOT NULL,
    countries    TEXT[]      NOT NULL,
    title        TEXT UNIQUE NOT NULL,
    description  TEXT        NULL,
    type         TEXT        NOT NULL CHECK ( type IN ('MOVIE', 'TV_SHOW', 'ANIME') ),
    release_date DATE        NULL,
    poster_url   TEXT        NULL
);

CREATE TABLE person_content
(
    content_id BIGINT REFERENCES content (id) NOT NULL,
    person_id  BIGINT REFERENCES person (id)  NOT NULL,
    UNIQUE (person_id, content_id)
);


CREATE TABLE rating
(
    content_id BIGINT REFERENCES content (id)              NOT NULL,
    profile_id BIGINT REFERENCES profile (id)              NOT NULL,
    value      INTEGER CHECK ( value >= 1 AND value <= 10) NOT NULL,
    UNIQUE (content_id, profile_id)
);

CREATE TABLE comment
(
    id         SERIAL8 PRIMARY KEY,
    content_id BIGINT REFERENCES content (id) NOT NULL,
    profile_id BIGINT REFERENCES profile (id) NOT NULL,
    message    TEXT                           NULL,
    added_at   DATE DEFAULT NOW()             NOT NULL
);


CREATE TABLE view
(
    content_id BIGINT REFERENCES content (id) NOT NULL,
    profile_id BIGINT REFERENCES profile (id) NOT NULL,
    favorite   BOOLEAN DEFAULT false          NOT NULL,
    condition  TEXT    DEFAULT 'WATCHED'      NOT NULL,
    added_at   DATE    DEFAULT NOW()          NOT NULL,
    UNIQUE (content_id, profile_id)
);
