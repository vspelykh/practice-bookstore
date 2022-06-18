DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS sub_categories;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS covers;

CREATE TABLE categories
(
    id       SERIAL PRIMARY KEY,
    category VARCHAR UNIQUE NOT NULL
);

CREATE TABLE sub_categories
(
    id           SERIAL PRIMARY KEY,
    category_id  INTEGER        NOT NULL,
    sub_category VARCHAR UNIQUE NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE publishers
(
    id        SERIAL PRIMARY KEY,
    publisher VARCHAR UNIQUE NOT NULL
);

CREATE TABLE authors
(
    id     SERIAL PRIMARY KEY,
    author VARCHAR UNIQUE NOT NULL
);

CREATE TABLE languages
(
    id       SERIAL PRIMARY KEY,
    language VARCHAR UNIQUE NOT NULL
);

CREATE TABLE covers
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR UNIQUE NOT NULL
);

CREATE TABLE books
(
    id                 SERIAL PRIMARY KEY,
    vendor_code        INTEGER NOT NULL,
    title              VARCHAR NOT NULL,
    author_id          INTEGER NOT NULL,
    publisher_id       INTEGER NOT NULL,
    pages              INTEGER NOT NULL,
    sub_category_id    INTEGER NOT NULL,
    language_id        INTEGER NOT NULL,
    cover_id           INTEGER NOT NULL,
    year_of_publishing INTEGER NOT NULL,
    price              DECIMAL NOT NULL,
    cover_image_url    VARCHAR NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers ON DELETE CASCADE,
    FOREIGN KEY (sub_category_id) REFERENCES sub_categories ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages ON DELETE CASCADE,
    FOREIGN KEY (cover_id) REFERENCES covers ON DELETE CASCADE
);

CREATE UNIQUE INDEX books_unique_author_title_idx ON books (author_id, title);

INSERT INTO categories (category)
VALUES ('Health'),
       ('Business'),
       ('Computing'),
       ('Romance'),
       ('Religion'),
       ('Sport'),
       ('Fantasy'),
       ('Thriller');

INSERT INTO sub_categories(sub_category, category_id)
VALUES ('Medicine', 1),
       ('Animals', 1),
       ('Sales', 2),
       ('Leadership', 2),
       ('Math', 3),
       ('Physics', 3),
       ('Historical', 4),
       ('Romantic', 4),
       ('Modern', 5),
       ('Ancient', 5),
       ('Football', 6),
       ('Tennis', 6),
       ('Low', 7),
       ('Magic', 7),
       ('Crime', 8),
       ('Psychological', 8);

INSERT INTO publishers (publisher)
VALUES ('Ranok Publishing House Ltd.'),
       ('Svichado Publishers Ltd.'),
       ('Znannia Publishing House');

INSERT INTO authors (author)
VALUES ('Samuel Shem'),
       ('Jerome Groopman'),
       ('Emma Jane Unsworth'),
       ('John Brooks'),
       ('Brené Brown'),
       ('Agatha Christie'),
       ('Sir Arthur Conan Doyle');

INSERT INTO languages (language)
VALUES ('Ukrainian'),
       ('English'),
       ('Italian');

INSERT INTO covers (type)
VALUES ('Hard'),
       ('Soft');
--
INSERT INTO books (vendor_code, title, author_id, publisher_id, pages, sub_category_id,
                   language_id, cover_id, year_of_publishing, price, cover_image_url)
VALUES ('1', 'Morphology of Humans', 3, 1, 700, 1, 1, 2, 2015, 500, 'random_url1'),
       ('2', 'Outliers: The Story of Success', 4, 3, 250, 5, 2, 1, 2020, 999, 'random_url2'),
       ('3', 'It Ends With Us', 6, 2, 450, 8, 1, 1, 2010, 250, 'random_url3'),
       ('4', 'The Confessions', 5, 3, 550, 5, 2, 1, 2009, 350, 'random_url4'),
       ('5', 'Mohawk Saint: Catherine Tekakwitha and the Jesuits', 5, 3, 510, 6, 2, 1, 2009, 350, 'random_url5'),
       ('6', 'Moneyball: The Art of Winning', 1, 3, 690, 11, 2, 2, 2000, 1999, 'random_url6'),
       ('7', 'The Body in the Library: A Puzzle', 6, 3, 789, 16, 2, 1, 1970, 2000, 'random_url7'),
       ('8', 'The Silent Patient', 7, 1, 550, 15, 1, 1, 1995, 666, 'random_url8');