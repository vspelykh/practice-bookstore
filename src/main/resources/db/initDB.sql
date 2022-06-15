DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS sub_categories;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS covers;

DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS categories_seq;
DROP SEQUENCE IF EXISTS sub_categories_seq;
DROP SEQUENCE IF EXISTS languages_seq;
DROP SEQUENCE IF EXISTS covers_seq;

CREATE SEQUENCE global_seq START WITH 1;
CREATE SEQUENCE categories_seq START WITH 1;
CREATE SEQUENCE sub_categories_seq START WITH 1;
CREATE SEQUENCE languages_seq START WITH 1;
CREATE SEQUENCE covers_seq START WITH 1;


CREATE TABLE categories
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('categories_seq'),
    category VARCHAR UNIQUE NOT NULL
);
CREATE TABLE sub_categories
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('sub_categories_seq'),
    category_id  INTEGER        NOT NULL,
    sub_category VARCHAR UNIQUE NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE publishers
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    publisher VARCHAR UNIQUE NOT NULL
);

CREATE TABLE authors
(
    id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    author VARCHAR UNIQUE NOT NULL
);

CREATE TABLE languages
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('languages_seq'),
    language VARCHAR UNIQUE NOT NULL
);

CREATE TABLE covers
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('covers_seq'),
    type VARCHAR UNIQUE NOT NULL
);

CREATE TABLE books
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    vendor_code     INTEGER NOT NULL,
    title           VARCHAR NOT NULL,
    author_id       INTEGER NOT NULL,
    publisher_id    INTEGER NOT NULL,
    pages           INTEGER NOT NULL,
    sub_category_id INTEGER NOT NULL,
    language_id     INTEGER NOT NULL,
    cover_id        INTEGER NOT NULL,
    year            INTEGER NOT NULL,
    price           DECIMAL NOT NULL,
    cover_image_url VARCHAR NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers ON DELETE CASCADE,
    FOREIGN KEY (sub_category_id) REFERENCES sub_categories ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages ON DELETE CASCADE,
    FOREIGN KEY (cover_id) REFERENCES covers ON DELETE CASCADE
);

CREATE UNIQUE INDEX books_unique_author_title_idx ON books (author_id, title);