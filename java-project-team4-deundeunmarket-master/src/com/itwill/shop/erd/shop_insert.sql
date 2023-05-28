/*
member insert
*/
insert into member(m_id, m_pass, m_name, m_phone, m_bday, m_email, m_address) values('sy0', '0000', '안시홍', '010-7894-4545', sysdate, 'sy0@gmail.com', '서울시 송파구');
insert into member(m_id, m_pass, m_name, m_phone, m_bday, m_email, m_address) values('sy1', '1111', '홍수연', '010-1647-9978', sysdate, 'sy1@gmail.com', '경기도 광주시');
insert into member(m_id, m_pass, m_name, m_phone, m_bday, m_email, m_address) values('sy2', '2222', '김시은', '010-1254-6654', sysdate, 'sy2@gmail.com', '서울시 광진구');

/*
product insert
*/
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '닭도리탕', 12000, '/images/닭도리탕_작은.jpg', '물떡과 감자수제비를 더해 풍성하게');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '떡볶이', 10000, '/images/떡볶이_작은.jpg', '자꾸 생각나는 매콤 달콤함');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '막국수', 11000, '/images/막국수_작은.jpg', '매콤한 소스와 동치미 육수의 조화');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '삼선짬뽕', 10000, '/images/삼선짬뽕_작은.jpg', '해산물이 푸짐한 백리향의 삼선짬뽕!');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '샤브샤브', 14000, '/images/샤브샤브_작은.jpg', '매장의 노하우로 완성');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '스테이크', 20000, '/images/스테이크_작은.jpg', '쫄깃한 식감, 풍부한 육즙');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '김치우동', 11000, '/images/김치우동_작은.jpg', '집에서 맛보는 인기 메뉴');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '전복버터밥', 10000, '/images/전복버터밥_작은.jpg', '코 끝을 찌르는 고소한 풍미');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '칼국수', 13000, '/images/칼국수_작은.jpg', '온 가족이 즐기는 푸짐한 칼국수');
insert into product(p_no, p_name, p_price, p_image, p_desc) values(product_p_no_SEQ.nextval, '크림파스타', 10000, '/images/크림파스타_작은.jpg', '매콤한 매력의 크림 파스타');

/*
cart insert
*/
--sy0
insert into cart(cart_no, cart_qty, m_id, p_no) values(cart_cart_no_SEQ.nextval, 1, 'sy0', 1);
insert into cart(cart_no, cart_qty, m_id, p_no) values(cart_cart_no_SEQ.nextval, 2, 'sy0', 2);

--sy1
insert into cart(cart_no, cart_qty, m_id, p_no) values(cart_cart_no_SEQ.nextval, 3, 'sy1', 3);
insert into cart(cart_no, cart_qty, m_id, p_no) values(cart_cart_no_SEQ.nextval, 4, 'sy1', 4);

/*
order insert
*/
--sy0
insert into orders(o_no, o_name, o_desc, o_date, o_price, o_address, o_loc, o_payment, m_id) values(orders_o_no_SEQ.nextval, '이동근','전복버터밥 외 7개', sysdate, 80000, '서울시 강남구 대치동','문 앞에 놔주세요', '계좌이체', 'sy0');
insert into order_item(oi_no, oi_qty, p_no, o_no) values(order_item_oi_no_SEQ.nextval, 1, 1, orders_o_no_SEQ.currval);
insert into order_item(oi_no, oi_qty, p_no, o_no) values(order_item_oi_no_SEQ.nextval, 2, 2, orders_o_no_SEQ.currval);

--sy1
insert into orders(o_no, o_name, o_desc, o_date, o_price, o_address, o_loc, o_payment, m_id) values(orders_o_no_SEQ.nextval, '한지민','크림파스타 외 3개', sysdate, 90000, '경기도 고양시 덕양구 화정동','벨 울리지 말아주세요', '카드', 'sy1');
insert into order_item(oi_no, oi_qty, p_no, o_no) values(order_item_oi_no_SEQ.nextval, 3, 3, orders_o_no_SEQ.currval);
insert into order_item(oi_no, oi_qty, p_no, o_no) values(order_item_oi_no_SEQ.nextval, 4, 4, orders_o_no_SEQ.currval);