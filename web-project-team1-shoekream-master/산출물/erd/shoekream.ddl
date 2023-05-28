DROP TABLE product_comment CASCADE CONSTRAINTS;
DROP TABLE delivery CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_item CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE userinfo CASCADE CONSTRAINTS;

CREATE TABLE userinfo(
		user_Id                       		VARCHAR2(20)		 NOT NULL,
		user_password                 		VARCHAR2(20)		 NOT NULL,
		user_name                     		VARCHAR2(20)		 NOT NULL,
		user_phone                    		VARCHAR2(20)		 NOT NULL,
		user_email                    		VARCHAR2(50)		 NOT NULL,
		user_address                  		VARCHAR2(100)		 NULL 
);


CREATE TABLE category(
		category_no                   		NUMBER(10)		 NULL ,
		category_name                 		VARCHAR2(20)		 NOT NULL
);


CREATE TABLE product(
		p_no                          		NUMBER(10)		 NULL ,
		p_name                        		VARCHAR2(50)		 NOT NULL,
		p_price                       		NUMBER(10)		 NOT NULL,
		p_image                       		VARCHAR2(100)	     NOT NULL,
		p_desc                        		VARCHAR2(300)		 NULL ,
		p_click_count                 		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		category_no                   		NUMBER(10)		 NULL 
);

CREATE TABLE orders(
		o_no                          		NUMBER(10)		 NULL ,
		o_desc                        		VARCHAR2(100)		 NULL ,
		o_date                        		DATE		 DEFAULT sysdate		 NULL ,
		o_price                       		NUMBER(10)		 NULL ,
		user_Id                       		VARCHAR2(20)		 NULL, 
        d_no                                NUMBER(10)           NULL
);

DROP SEQUENCE orders_o_no_SEQ;

CREATE SEQUENCE orders_o_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;


CREATE TABLE order_item(
		oi_no                         		NUMBER(10)		 NULL ,
		oi_qty                        		NUMBER(10)		 NULL ,
		o_no                          		NUMBER(10)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

DROP SEQUENCE order_item_oi_no_SEQ;

CREATE SEQUENCE order_item_oi_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TABLE cart(
		cart_no                       		NUMBER(10)		 NULL ,
		user_Id                       		VARCHAR2(20)		 NULL, 
		cart_qty                      		NUMBER(10)		 DEFAULT 0		 NULL ,
		p_no                          		NUMBER(10)		 NULL
);

DROP SEQUENCE cart_cart_no_SEQ;

CREATE SEQUENCE cart_cart_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TABLE board(
		board_no                      		NUMBER(10)		 NULL ,
		board_title                   		VARCHAR2(100)		 NOT NULL,
		board_content                 		VARCHAR2(200)		 NOT NULL,
		board_regdate                 		DATE		 DEFAULT sysdate		 NULL ,
		board_readcount               		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		board_group_no                		NUMBER(10)		 NOT NULL,
		board_step                    		NUMBER(10)		 NOT NULL,
		board_depth                   		NUMBER(10)		 DEFAULT 0		 NULL ,
		user_Id                       		VARCHAR2(20)		 NULL 
);

DROP SEQUENCE board_board_no_SEQ;

CREATE SEQUENCE board_board_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

DROP SEQUENCE board_board_depth_SEQ;

CREATE SEQUENCE board_board_depth_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TABLE delivery(
        d_no                                NUMBER(10)           NULL,
		d_address                     		VARCHAR2(100)		 NULL ,
		d_phone                       		VARCHAR2(20)		 NULL ,
		d_name                        		VARCHAR2(20)		 NULL ,
		user_Id                       		VARCHAR2(20)		 NULL 
);

DROP SEQUENCE delivery_d_no_SEQ;

CREATE SEQUENCE delivery_d_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;



CREATE TABLE product_comment(
        pc_no                               NUMBER(10)          NULL,
		pc_content                    		VARCHAR2(200)		 NOT NULL,
		pc_regdate                    		DATE		 DEFAULT sysdate		 NULL ,
		pc_mark                       		NUMBER(10)		 NOT NULL,
		p_no                          		NUMBER(10)		 NOT NULL ,
		writer                       		VARCHAR2(20)		 NULL 
);

DROP SEQUENCE product_comment_pc_no_SEQ;

CREATE SEQUENCE product_comment_pc_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;


ALTER TABLE userinfo ADD CONSTRAINT IDX_userinfo_PK PRIMARY KEY (user_Id);

ALTER TABLE category ADD CONSTRAINT IDX_category_PK PRIMARY KEY (category_no);

ALTER TABLE product ADD CONSTRAINT IDX_product_PK PRIMARY KEY (p_no);
ALTER TABLE product ADD CONSTRAINT IDX_product_FK0 FOREIGN KEY (category_no) REFERENCES category (category_no) ON DELETE CASCADE;

ALTER TABLE orders ADD CONSTRAINT IDX_orders_PK PRIMARY KEY (o_no);
ALTER TABLE orders ADD CONSTRAINT IDX_orders_FK0 FOREIGN KEY (user_Id) REFERENCES userinfo (user_Id) ON DELETE CASCADE;
ALTER TABLE orders ADD CONSTRAINT IDX_orders_FK1 FOREIGN KEY (d_no) REFERENCES delivery (d_no) ON DELETE CASCADE;

ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_PK PRIMARY KEY (oi_no);
ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_FK0 FOREIGN KEY (p_no) REFERENCES product (p_no) ON DELETE CASCADE;
ALTER TABLE order_item ADD CONSTRAINT IDX_order_item_FK1 FOREIGN KEY (o_no) REFERENCES orders (o_no) ON DELETE CASCADE;

ALTER TABLE cart ADD CONSTRAINT IDX_cart_PK PRIMARY KEY (cart_no);
ALTER TABLE cart ADD CONSTRAINT IDX_cart_FK0 FOREIGN KEY (p_no) REFERENCES product (p_no) ON DELETE CASCADE;
ALTER TABLE cart ADD CONSTRAINT IDX_cart_FK1 FOREIGN KEY (user_Id) REFERENCES userinfo (user_Id) ON DELETE CASCADE;

ALTER TABLE board ADD CONSTRAINT IDX_board_PK PRIMARY KEY (board_no);
ALTER TABLE board ADD CONSTRAINT IDX_board_FK0 FOREIGN KEY (user_Id) REFERENCES userinfo (user_Id) ON DELETE CASCADE;

ALTER TABLE delivery ADD CONSTRAINT IDX_delivery_PK PRIMARY KEY (d_no);
ALTER TABLE delivery ADD CONSTRAINT IDX_delivery_FK0 FOREIGN KEY (user_Id) REFERENCES userinfo (user_Id) ON DELETE CASCADE;

ALTER TABLE product_comment ADD CONSTRAINT IDX_product_reply_PK PRIMARY KEY (pc_no);
ALTER TABLE product_comment ADD CONSTRAINT IDX_product_reply_FK0 FOREIGN KEY (p_no) REFERENCES product (p_no) ON DELETE CASCADE;
ALTER TABLE product_comment ADD CONSTRAINT IDX_product_reply_FK1 FOREIGN KEY (writer) REFERENCES userinfo (user_Id) ON DELETE CASCADE;

