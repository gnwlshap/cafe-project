use cafe_project;
commit;

-- 1. user
CREATE TABLE `user` (
    user_id varchar(20) PRIMARY KEY,
    user_password varchar(20) NOT NULL,
    user_name varchar(30) NOT NULL,
    user_address varchar(130) NOT NULL,
    user_phone varchar(13) UNIQUE NOT NULL,
    user_reg_date timestamp DEFAULT CURRENT_TIMESTAMP
);

-- 2. cafe
CREATE TABLE cafe (
    cafe_code int AUTO_INCREMENT PRIMARY KEY,
    cafe_name varchar(70) NOT NULL,
    cafe_address varchar(130) NOT NULL,
    cafe_phone varchar(13) UNIQUE NOT NULL,
    cafe_regist_date timestamp DEFAULT CURRENT_TIMESTAMP
) AUTO_INCREMENT = 100;
 
 -- 3. scrap : 좋아요
CREATE TABLE scrap (
    cafe_code INT  NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (cafe_code) REFERENCES cafe(cafe_code) ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE
);

-- 4. menu
CREATE TABLE menu (
    menu_code int AUTO_INCREMENT PRIMARY KEY,
    menu_name varchar(60) NOT NULL,
    menu_category varchar(50) NOT NULL,
    menu_price int NOT NULL,
    menu_info varchar(2000) NOT NULL,
    menu_regist_date timestamp DEFAULT CURRENT_TIMESTAMP
);

-- 5. notice
CREATE TABLE notice (
   notice_number INTEGER AUTO_INCREMENT PRIMARY KEY,
   user_id VARCHAR(20) NOT NULL CHECK (user_id = 'composecoffee'),
   notice_title VARCHAR(200) NOT NULL, 
   notice_content VARCHAR(9999) NOT NULL,
   notice_view INT NOT NULL default 1,
   notice_post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   notice_edit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (user_id) REFERENCES `user`(user_id)
)AUTO_INCREMENT = 1;

-- 6.  review
CREATE TABLE review (
    review_number INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    review_title VARCHAR(200) NOT NULL,
    review_content VARCHAR(9999) NOT NULL,
    review_view INT NOT NULL default 1,
    review_post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    review_edit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE
)AUTO_INCREMENT = 1;

-- 7. comment
CREATE TABLE `comment` (
    comment_seq int AUTO_INCREMENT PRIMARY KEY,
    review_number INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    comment_content TEXT NOT NULL,
    comment_post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    comment_edit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (review_number) REFERENCES review(review_number) ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE
) AUTO_INCREMENT = 1;

-- 8. order
CREATE TABLE `order` (
    order_code INT AUTO_INCREMENT PRIMARY KEY,
    cafe_code INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    review_written boolean NOT NULL DEFAULT FALSE,
    order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cafe_code) REFERENCES cafe(cafe_code) ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE
);

-- 9. cart
CREATE TABLE cart (
    user_id VARCHAR(20),
    cafe_code INT NOT NULL,
    menu_code INT NOT NULL,
    menu_count INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE,
    FOREIGN KEY (cafe_code) REFERENCES cafe(cafe_code) ON UPDATE CASCADE,
    FOREIGN KEY (menu_code) REFERENCES menu(menu_code) ON UPDATE CASCADE
);

-- 10. item
CREATE TABLE item (
    order_code INT NOT NULL,
    menu_code INT NOT NULL,
    item_count INT NOT NULL,
    FOREIGN KEY (order_code) REFERENCES `order`(order_code) ON UPDATE CASCADE,
    FOREIGN KEY (menu_code) REFERENCES menu(menu_code) ON UPDATE CASCADE
);

 -- 주문서 view
CREATE VIEW order_detail AS
SELECT 
    o.order_code, 
    o.cafe_code, 
    c.cafe_name, 
    o.user_id, 
    u.user_name, 
    i.menu_code, 
    m.menu_name, 
    i.item_count, 
    m.menu_price,
    (i.item_count * m.menu_price) AS item_total_price
FROM `order` o
JOIN item i ON o.order_code = i.order_code
JOIN menu m ON i.menu_code = m.menu_code
JOIN cafe c ON o.cafe_code = c.cafe_code
JOIN `user` u ON o.user_id = u.user_id;

CREATE VIEW view_order_summary AS
SELECT 
	o.order_code,
    o.cafe_code,
    c.cafe_name,
    o.user_id,
    u.user_name,
    i.menu_code,
    m.menu_name,
    i.item_count,
    m.menu_price,
    (i.item_count * m.menu_price) AS total_price
FROM `order` AS o
JOIN cafe AS c ON o.cafe_code = c.cafe_code
JOIN user AS u ON o.user_id = u.user_id
JOIN item AS i ON o.order_code = i.order_code
JOIN menu AS m ON i.menu_code = m.menu_code;

-- 메뉴 상세 내역
SELECT menu_name, item_count, menu_price, item_total_price FROM order_detail where order_code = 4;

-- 소계 쿼리문
SELECT order_code, SUM(item_total_price) AS total_price
FROM order_detail
GROUP BY order_code, user_id, user_name
having order_code = 4;

-- 장바구니 view 
CREATE VIEW cart_detail AS
SELECT 
  c.user_id, 
  m.menu_code,
  m.menu_price, 
  c.menu_count,
  m.menu_price * SUM(c.menu_count) as total_price
FROM cart c 
INNER JOIN menu m ON c.menu_code = m.menu_code 
GROUP BY c.user_id, m.menu_code;

-- 특정 사용자의 장바구니 총합계 
SELECT
 user_id,
 SUM(total_price) as cart_total
FROM cart_detail
WHERE user_id = '123'
GROUP BY
 user_id;
 
 
 -- 주문내역
CREATE VIEW order_history AS 
SELECT 
    o.order_code, 
    c.cafe_name, 
    o.order_time, 
    o.review_written,
    o.user_id
FROM 
    `order` o 
    JOIN cafe c ON o.cafe_code = c.cafe_code 
ORDER BY 
    o.order_time DESC; 