DELETE FROM categories;
DELETE FROM sub_categories;
DELETE FROM publishers;
DELETE FROM authors;
DELETE FROM languages;
DELETE FROM covers;
DELETE FROM books;

ALTER SEQUENCE global_seq RESTART WITH 1;
ALTER SEQUENCE categories_seq RESTART WITH 1;
ALTER SEQUENCE sub_categories_seq RESTART WITH 1;
ALTER SEQUENCE languages_seq RESTART WITH 1;
ALTER SEQUENCE covers_seq RESTART WITH 1;

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

INSERT INTO books (vendor_code, title, author_id, publisher_id, pages, sub_category_id,
                   language_id, cover_id, year, price, cover_image_url)
VALUES ('11', 'Morphology of Humans', 6, 1, 700, 1, 1, 2, 2015, 500, 'random_url1'),
       ('12', 'Outliers: The Story of Success', 7, 3, 250, 5, 2, 1, 2020, 999, 'random_url2'),
       ('13', 'It Ends With Us', 9, 2, 450, 8, 1, 1, 2010, 250, 'random_url3'),
       ('14', 'The Confessions', 8, 3, 550, 5, 2, 1, 2009, 350, 'random_url4'),
       ('15', 'Mohawk Saint: Catherine Tekakwitha and the Jesuits', 8, 3, 510, 6, 2, 1, 2009, 350, 'random_url5'),
       ('16', 'Moneyball: The Art of Winning', 4, 3, 690, 11, 2, 2, 2000, 1999, 'random_url6'),
       ('17', 'The Body in the Library: A Puzzle', 9, 3, 789, 16, 2, 1, 1970, 2000, 'random_url7'),
       ('18', 'The Silent Patient', 10, 1, 550, 15, 1, 1, 1995, 666, 'random_url8');