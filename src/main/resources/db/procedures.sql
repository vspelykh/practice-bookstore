CREATE OR REPLACE FUNCTION get_books_by_category(category_pattern varchar, sub_category_pattern varchar)
    RETURNS TABLE
            (
                title           VARCHAR,
                author          VARCHAR,
                cover_image_url VARCHAR,
                price           DECIMAL
            )
    language plpgsql

AS
$$
BEGIN
    RETURN QUERY
        SELECT b.title, a.author, b.cover_image_url, b.price
        FROM books AS b
                 JOIN authors a on a.id = b.author_id
                 JOIN sub_categories sc on sc.id = b.sub_category_id
                 JOIN categories c on c.id = sc.category_id
        WHERE category = category_pattern
          AND sub_category = sub_category_pattern;
END;
$$