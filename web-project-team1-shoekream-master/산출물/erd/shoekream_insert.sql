/**********************userinfo insert************************/
insert into userinfo(user_Id,user_password,user_name,user_phone,user_email,user_address) values('cream1','1111','크림1','1111','cream1@korea.com','aaaa');
insert into userinfo(user_Id,user_password,user_name,user_phone,user_email,user_address) values('cream2','2222','크림2','2222','cream2@korea.com','bbbb');
insert into userinfo(user_Id,user_password,user_name,user_phone,user_email,user_address) values('cream3','3333','크림3','3333','cream3@korea.com','cccc');

/**********************category insert************************/
desc category;
insert into category(category_no,category_name) values(1,'운동화');
insert into category(category_no,category_name) values(2,'구두');
insert into category(category_no,category_name) values(3,'부츠');
insert into category(category_no,category_name) values(4,'슬리퍼');

delete from category where category_no = 4;

/**********************product insert************************/
desc product;

insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(1, 'Golden Goose Superstar', 380000, 'Sneakers01.png','White Silver Heel', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(2, 'Jordan 1 Elevate Low', 165000, 'Sneakers02.png','White and Wolf Grey', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(3, 'Nike Air Force 1 07 Low', 131000, 'Sneakers03.png','White', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(4, 'Adidas Superstar Core', 109000, 'Sneakers04.png','Black White', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(5, 'Asics Jog 100 S Sheet Rock', 74000, 'Sneakers05.png','2E Wide', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(6, 'Converse x Ader Error Chuck ', 216000, 'Sneakers06.png','White', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(7, 'Jordan 1 High Golf', 210000, 'Sneakers07.png','White Black', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(8, 'Jordan 1 Retro High OG Chicago 2022', 410000, 'Sneakers08.png','RED and BLACK', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(9, 'Maison Mihara Yasuhiro ', 415000, 'Sneakers09.png','Black White', 0,1);


insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(10, 'New Balance 2002R', 177000, 'Sneakers10.png','Grey', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(11, 'Nike Air Force 1', 162000, 'Sneakers11.png','BROWN BLACK', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(12, 'Nike Dunk Low Retro', 124000, 'Sneakers12.png','Black', 0,1);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(13, 'Nike x Supreme Air Force', 229000, 'Sneakers13.png','White', 0,1);


insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(21, 'Chanel Open Shoes Lambskin', 1850000, 'Shoes01.png','Calfskin  Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(22, 'Chanel Open Shoes Suede Kidskin', 1640000, 'Shoes02.png','Calfskin  Beige Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(23, 'Dior Jadior Slingback Pumps', 1200000, 'Shoes03.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(24, 'Gucci GG Marmont Leather', 1168000, 'Shoes04.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(25, 'Gucci Horsebit Mid-Heel Slingback', 1400000, 'Shoes05.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(26, 'Gucci Leather Mid-Heel Pumps', 970000, 'Shoes06.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(27, 'Marni Jacquard Marnigram', 443000, 'Shoes07.png','Black Lily White', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(28, 'Prada Brushed Leather', 901000, 'Shoes08.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(29, 'Prada Heeled Brushed', 800000, 'Shoes09.png','Black', 0,2);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(210, 'Balenciaga Trooper', 1230000, 'Shoes10.png','Black', 0,2);





insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(31, 'Chanel Boots Caoutchouc', 28880000, 'Boots01.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(32, 'Moon Boot Icon Low Nylon  ', 90000, 'Boots02.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(33, 'UGG Classic Clear Mini  ', 70000, 'Boots03.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(34, 'UGG Classic Mini II', 104000, 'Boots04.png','Chestnut', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(35, 'UGG Classic Short II ', 159000, 'Boots05.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(36, 'Ader Error x Zara', 400000, 'Boots06.png','Brown', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(37, 'Balenciaga XL Army ', 1599000, 'Boots07.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(38, 'Clarks Wallabee ', 210000, 'Boots08.png','Black', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(39, 'Crocs Classic Lined Bone ', 68000, 'Boots09.png','White', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(310, 'Crocs Classic Lined White ', 59000, 'Boots10.png','White', 0,3);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(311, 'Timberland x Stussy ',460000, 'Boots11.png','Black', 0,3);




insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(41, 'Dior Dway Mule Cotton', 847000, 'Slippers01.png','Deep Blue', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(42, 'Hermes Oran Sandal Calfskin Gold', 839000, 'Slippers02.png','Gold  Siver', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(43, 'Oofos OOlala OOmega', 105000, 'Slippers03.png','White', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(44, 'Polo Ralph Lauren Kayleigh ', 92000, 'Slippers04.png','SNUFF', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(45, 'UGG Coquette Slipper Chestnut', 91000, 'Slippers05.png','SNUFF', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(46, 'Adidas Adilette 22 Carbon ', 55000, 'Slippers06.png','CARBON and ALUMINIUM', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(47, 'Adidas Yeezy Resin 2022', 120000, 'Slippers07.png','RESIN', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(48, 'Birkenstock Boston Soft ', 250000, 'Slippers08.png','TAUPE', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(49, 'Fear of God x Birkenstock ', 568000, 'Slippers09.png','Taupe', 0,4);
insert into product(p_no,p_name,p_price,p_image,p_desc,p_click_count,category_no) values(410, 'Hermes Izmir Sandal Calfskin', 989000, 'Slippers10.png','Noir', 0,4);





/*** 상품 update******/
update product set p_name='(new 신상) 어메이징 부츠',p_price=1000000,p_image='Boots01.jpg',p_desc='기타등등',P_click_count=0,category_no=1 where p_no =1;

/*상품 삭제*/
delete from product where p_no = 311;
/*상품 카테고리로 삭제*/
delete from product where category_no = 4;
/*상품 찾기*/
select * from product where p_no = 1;

/*상품 전체 리스트*/
select * from product;

/**********************cart insert************************/
insert into cart(cart_no,user_Id,cart_qty,p_no) values (cart_cart_no_SEQ.nextval,'cream1',1,1);
insert into cart(cart_no,user_Id,cart_qty,p_no) values (cart_cart_no_SEQ.nextval,'cream1',2,2);
insert into cart(cart_no,user_Id,cart_qty,p_no) values (cart_cart_no_SEQ.nextval,'cream2',3,3);
insert into cart(cart_no,user_Id,cart_qty,p_no) values (cart_cart_no_SEQ.nextval,'cream2',7,1);
insert into cart(cart_no,user_Id,cart_qty,p_no) values (cart_cart_no_SEQ.nextval,'cream3',5,5);

/**********************delivery insert************************/
insert into delivery(d_no,d_address,d_phone,d_name,user_Id) values (delivery_d_no_SEQ.nextval,'cream1_home','1111-1111','집','cream1');
insert into delivery(d_no,d_address,d_phone,d_name,user_Id) values (delivery_d_no_SEQ.nextval,'cream1_office','1111-2222','회사','cream1');
insert into delivery(d_no,d_address,d_phone,d_name,user_Id) values (delivery_d_no_SEQ.nextval,'cream2_home','2222-1111','집','cream2');
insert into delivery(d_no,d_address,d_phone,d_name,user_Id) values (delivery_d_no_SEQ.nextval,'cream2_office','2222-2222','회사','cream2');
insert into delivery(d_no,d_address,d_phone,d_name,user_Id) values (delivery_d_no_SEQ.nextval,'cream3_home','3333-1111','집','cream3');

/**********************orders insert************************/
insert into orders(o_no,o_desc,o_date,o_price,user_Id,d_no) values (orders_o_no_SEQ.nextval,'shoes1외1종',sysdate-2,300000,'cream1',2);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,2);

insert into orders(o_no,o_desc,o_date,o_price,user_Id,d_no) values (orders_o_no_SEQ.nextval,'shoes3',sysdate-2,300000,'cream2',3);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,3);

insert into orders(o_no,o_desc,o_date,o_price,user_Id,d_no) values (orders_o_no_SEQ.nextval,'shoes4외 1종',sysdate-1,400000,'cream2',4);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,2,orders_o_no_SEQ.currval,4);

insert into orders(o_no,o_desc,o_date,o_price,user_Id,d_no) values (orders_o_no_SEQ.nextval,'shoes2외 3종',sysdate,1900000,'cream2',4);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,2);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,2,orders_o_no_SEQ.currval,5);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,6);

insert into orders(o_no,o_desc,o_date,o_price,user_Id,d_no) values (orders_o_no_SEQ.nextval,'shoes3외 1종',sysdate,1100000,'cream3',5);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,1,orders_o_no_SEQ.currval,3);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_SEQ.nextval,2,orders_o_no_SEQ.currval,4);


/**********************product_reply insert************************/
--상품 추가
insert into product_reply(pr_no,pr_content,pr_regdate,pr_group_no,pr_step,pr_depth,p_no,user_Id) values (1,'신발 감사',sysdate,1,1,1,1,'cream1');

--상품 리뷰 삭제
delete from product_reply where pr_no = 1;

--상품 리뷰 수정
update product_reply set pr_content = '사이즈가 좋아요!' where pr_no=1;

--상품 리뷰 하나 상세보기
select * from product_reply pr join product p on pr.p_no=p.p_no where pr.p_no=1;

/**********************board insert************************/
insert into board(board_no,board_title,board_content,board_regdate,board_readcount,board_group_no,board_step,board_depth,user_id)
values (board_board_no_SEQ.nextval,'게시판테스트','게시판insert를 해보자',sysdate,0,board_board_no_SEQ.currval,1,0,'cream1');

commit;
desc order_item;


