-- Total number of books per publisher:
SELECT publisher AS publisher_name, COUNT(*) AS total_books
FROM books
         JOIN publishers p on p.id = books.publisher_id
GROUP BY publisher;

-- Total number of books per category:
SELECT c.category, count(*) as total_books
from books
         inner join sub_categories sc on sc.id = books.sub_category_id
         inner join categories c on c.id = sc.category_id
GROUP BY c.category;

-- Total number of books per language:
SELECT language, COUNT(*) AS total_books
FROM books
         JOIN languages l on l.id = books.language_id
GROUP BY language;

-- Percentage of books per publisher:
SELECT publisher                                                           AS publisher_name,
       ROUND((COUNT(publisher) * 100.0 / (SELECT COUNT(*) From books)), 2) as percentage
FROM books
         JOIN publishers p on p.id = books.publisher_id
GROUP BY publisher;