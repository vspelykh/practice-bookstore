DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS wishlist_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
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
    description        TEXT    NOT NULL,
    amount             INTEGER NOT NULL,
    cover_image_url    VARCHAR NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers ON DELETE CASCADE,
    FOREIGN KEY (sub_category_id) REFERENCES sub_categories ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages ON DELETE CASCADE,
    FOREIGN KEY (cover_id) REFERENCES covers ON DELETE CASCADE
);

CREATE UNIQUE INDEX books_unique_author_title_idx ON books (author_id, title);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    surname  VARCHAR NOT NULL,
    email    VARCHAR NOT NULL,
    role     VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE carts
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE
);


CREATE TABLE cart_items
(
    id       SERIAL PRIMARY KEY,
    cart_id  INTEGER NOT NULL,
    book_id  INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books ON DELETE CASCADE
);

CREATE TABLE wishlist_items
(
    id      SERIAL PRIMARY KEY,
    book_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE
);

CREATE TABLE orders
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER   NOT NULL,
    address VARCHAR   NOT NULL,
    status  VARCHAR   NOT NULL,
    post    VARCHAR   NOT NULL,
    date    TIMESTAMP NOT NULL,
    comment VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE
);

CREATE TABLE order_items
(
    id       SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    user_id  INTEGER NOT NULL,
    book_id  INTEGER NOT NULL,
    price    INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books ON DELETE CASCADE
);

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
                   language_id, cover_id, year_of_publishing, price, description, amount, cover_image_url)
VALUES ('1', 'Morphology of Humans', 3, 1, 700, 1, 1, 2, 2015, 500,
        'This work has been selected by scholars as being culturally important, and is part of the knowledge base of civilization as we know it. This work was reproduced from the original artifact, and remains as true to the original work as possible. Therefore, you will see the original copyright references, library stamps (as most of these works have been housed in our most important libraries around the world), and other notations in the work.This work is in the public domain in the United States of America, and possibly other nations.',
        120,
        'https://images-na.ssl-images-amazon.com/images/I/51XDxaU7GqL._SX326_BO1,204,203,200_.jpg'),
       ('2', 'Outliers: The Story of Success', 4, 3, 250, 5, 2, 1, 2020, 999,
        'In this stunning new book, Malcolm Gladwell takes us on an intellectual journey through the world of "outliers"--the best and the brightest, the most famous and the most successful. He asks the question: what makes high-achievers different?

His answer is that we pay too much attention to what successful people are like, and too little attention to where they are from: that is, their culture, their family, their generation, and the idiosyncratic experiences of their upbringing. Along the way he explains the secrets of software billionaires, what it takes to be a great soccer player, why Asians are good at math, and what made the Beatles the greatest rock band.

Brilliant and entertaining, Outliers is a landmark work that will simultaneously delight and illuminate.', 60,
        'https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/9/7/9780141043029-2.jpg'),
       ('3', 'It Ends With Us', 6, 2, 450, 8, 1, 1, 2010, 250,
        'It Ends With Us is the story of Lily who witnessed her mother getting abused by her father all her life and feeling helpless to do anything about it. Determined to live her life on her terms, she moves to Boston and it is here that she meets Dr. Ryle Kincaid.',
        150,
        'https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/8/1/817vqet828l.jpg'),
       ('4', 'The Confessions', 5, 3, 550, 10, 2, 1, 2009, 350,
        'The Confession is a 2010 legal thriller novel by John Grisham, his second novel to be published in 2010 (the previous was Theodore Boone: Kid Lawyer). The novel is about the murder of a high school cheerleader and how an innocent man was arrested for it.',
        123,
        'https://images-na.ssl-images-amazon.com/images/I/71p8O18Y3NL.jpg'),
       ('5', 'Mohawk Saint: Catherine Tekakwitha and the Jesuits', 5, 3, 510, 9, 2, 1, 2009, 350,
        'On October 21, 2012, Pope Benedict XVI canonized Saint Kateri Tekakwitha as the first Native North American saint. Mohawk Saint is a work of history that situates her remarkable life in its seventeenth century setting, a time of wars, epidemics, and cultural transformations for the Indian
peoples of the northeast. The daughter of a Algonquin mother and an Iroquois father, Catherine/Saint Kateri Tekakwitha (1656-1680) has become known over the centuries as a Catholic convert so holy that, almost immediately upon her death, she became the object of a cult. Today she is revered as a
patron saint by Native Americans and the patroness of ecology and the environment by Catholics more generally, the first Native North American proposed for sainthood.'
           , 0,
        'https://images-na.ssl-images-amazon.com/images/I/51z3ttFOOdL._SX331_BO1,204,203,200_.jpg'),
       ('6', 'Moneyball: The Art of Winning', 1, 3, 690, 11, 2, 2, 2000, 1999,
        'Moneyball is a quest for the secret of success in baseball. In a narrative full of fabulous characters and brilliant excursions into the unexpected, Michael Lewis follows the low-budget Oakland A''s, visionary general manager Billy Beane, and the strange brotherhood of amateur baseball theorists.'
           , 10,
        'https://images-na.ssl-images-amazon.com/images/I/41muYYGwiOL._SX331_BO1,204,203,200_.jpg'),
       ('7', 'The Body in the Library: A Puzzle', 6, 3, 789, 16, 2, 1, 1970, 2000,
        'Read the story and assemble the puzzle (not the same as the picture shown), then using the clues in the booklet and the finished puzzle - work out how Miss Marple solved the crime!',
        123,
        'https://m.media-amazon.com/images/I/51VQGWGQHSL._AC_SY450_.jpg'),
       ('8', 'The Silent Patient', 7, 1, 550, 15, 1, 1, 1995, 666,
        'Alicia Berenson’s life is seemingly perfect. A famous painter married to an in-demand fashion photographer, she lives in a grand house with big windows overlooking a park in one of London’s most desirable areas. One evening her husband Gabriel returns home late from a fashion shoot, and Alicia shoots him five times in the face, and then never speaks another word.

Alicia’s refusal to talk, or give any kind of explanation, turns a domestic tragedy into something far grander, a mystery that captures the public imagination and casts Alicia into notoriety. The price of her art skyrockets, and she, the silent patient, is hidden away from the tabloids and spotlight at the Grove, a secure forensic unit in North London.
', 45,
        'data:image/jpeg;base64,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');

INSERT INTO users (id, name, surname, email, role, password)
VALUES (1, 'Vladyslav', 'Pelykh', 'user@ukr.net', 'USER', 'userpass'),
       (2, 'Administrator', 'Adminovich', 'admin@gmail.com', 'ADMIN', 'adminpass');

INSERT INTO carts (user_id)
VALUES (1);

INSERT INTO cart_items (cart_id, book_id, quantity)
VALUES (1, 2, 3),
       (1, 5, 4);


