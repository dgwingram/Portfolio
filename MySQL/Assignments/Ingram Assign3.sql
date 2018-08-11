/*
	Programmer:		Daniel Ingram
    Description:	Editing a Database name my_guitar_shop which stores data on sales and products of instruments.
*/
USE my_guitar_shop;

-- 1.a)
SELECT product_code, product_name, list_price, discount_percent
FROM products;
-- 1.b)
SELECT product_code, product_name, list_price, discount_percent
FROM products
ORDER BY list_price DESC;

-- 2)
SELECT product_name, list_price, date_added
FROM products 
WHERE list_price BETWEEN 500 AND 2000
ORDER BY date_added DESC;

-- 3)
SELECT category_name, product_name, list_price
FROM products p
	INNER JOIN categories c
	ON p.category_id = c.category_id
ORDER BY category_name, product_name ASC;

-- 4)
SELECT last_name, first_name, order_date, product_name, item_price, discount_amount, quantity
FROM customers c
	INNER JOIN orders o
		ON o.customer_id = o.customer_id
	INNER JOIN order_items i
		ON i.order_id = o.order_id
	INNER JOIN products p
		ON p.product_id = i.product_id
ORDER BY last_name, order_date, product_name; 

-- 5)
SELECT category_name, product_id
FROM categories c
	LEFT JOIN products p
    ON p.category_id = c.category_id
GROUP BY category_name
HAVING product_id  IS NULL;

-- 6)
SELECT count(order_id) AS order_count, sum(tax_amount) AS tax_total
FROM orders;
select * from orders;

-- 7)
SELECT category_name, count(product_id) AS product_count,max(list_price) AS most_expensive_product
FROM products p
	INNER JOIN categories c
	ON p.category_id = c.category_id
GROUP BY category_name DESC;

-- 8)
SELECT email_address,
		COUNT(o.order_id),
        SUM((item_price-discount_amount)*quantity) AS order_total
FROM customers c
	INNER JOIN orders o
		ON o.customer_id = c.customer_id
	INNER JOIN order_items i
		on i.order_id = o.order_id
GROUP BY email_address
HAVING COUNT(o.order_id)>1
ORDER BY order_total DESC;

-- 9)
SELECT email_address,
		COUNT(o.order_id),
        SUM((item_price-discount_amount)*quantity) AS order_total
FROM customers c
	INNER JOIN orders o
		ON o.customer_id = c.customer_id
	INNER JOIN order_items i
		on i.order_id = o.order_id
WHERE item_price>400
GROUP BY email_address
HAVING COUNT(o.order_id)>1
ORDER BY order_total DESC;

-- 10)
SELECT product_name, list_price
FROM products
WHERE list_price > 
	(
    SELECT AVG(list_price)
    FROM products
    )
ORDER BY list_price DESC;

-- 11)
INSERT INTO categories VALUES
	(default, 'Brass');

-- 12)
CREATE TABLE categories_BACKUP AS
SELECT *
FROM categories;

UPDATE categories
SET category_name = 'Woodwinds'
WHERE category_id = 5;

-- 13)
DELETE FROM categories
WHERE category_id = 5;


-- 14)
INSERT INTO customers (email_address, password, first_name, last_name) VALUES
	('comp53@mts.com','','Daniel','Ingram');

-- 15)
UPDATE customers
SET password = 'cpa15'
WHERE email_address = 'comp53@mts.com';

-- 16)
UPDATE customers
SET password = 'reset';
SELECT * FROM customers;

-- 17)
CREATE INDEX last_name_ix
ON customers (last_name);
