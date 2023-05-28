/************* 회원 ***************/
--회원정보 보기(select pk)
select * from member where m_id='sy0';
select * from member where m_id='sy1';
select * from member where m_id='sy2';

--회원정보 수정(update pk)
update member set m_pass='1111',m_name='nxxx', m_phone='000-0000-0000', m_bday='1992/02/12', m_email='g@gmail.com', m_address='부산' where m_id='sy0';
update member set m_pass='2222',m_name='nyyy', m_phone='111-1111-1111', m_bday='1993/02/12', m_email='k@gmail.com', m_address='인천' where m_id='sy1';
update member set m_pass='3333',m_name='nzzz', m_phone='222-2222-2222', m_bday='1994/02/12', m_email='l@gmail.com', m_address='대구' where m_id='sy2';

--회원정보 삭제(delete pk)
delete member where  m_id='sy0';
delete member where  m_id='sy1';
delete member where  m_id='sy2';


/************* 제품 ***************/
-- 제품 전체 리스트
select * from product;
-- 제품 상세보기
select * from product where p_no=1;


/************* 카트 ***************/
-- 로그인 한 회원의 카트 리스트
select * from cart where m_id = 'sy0';
-- 로그인 한 회원의 카트 리스트(카트+제품)
select * from cart c join product p on c.p_no = p.p_no where m_id = 'sy0'; 
select * from cart c join product p on c.p_no = p.p_no where cart_no = 1; 
-- 로그인 한 회원의 카트리스트 삭제(cart_no) 
delete cart where cart_no = 1;
-- 로그인 한 회원의 카트리스트 전체 삭제(m_id)
delete cart where m_id = 'sy0';
-- 로그인 한 회원의 카트 1개 아이템 수량 변경(cart_no)
update cart set cart_qty = 3 where cart_no = 3;
-- 로그인 한 회원의 카트에 존재하는 제품의 수(제품 존재여부 판단)
select count(*) cnt from cart c join member m on c.m_id=m.m_id where m.m_id='sy0' and c.p_no=1;
-- 로그인 한 회원의 카트에 담기(카트에 이미 존재하는 제품의 수량만 수정)
update cart set cart_qty = cart_qty+1 where m_id = 'sy1' and p_no = 3;



/************* 주문 ***************/
-- 주문 전체 목록 보기 (회원아이디)
select * from orders where m_id = 'sy1';
-- 주문(주문아이템+제품) 전체 목록 보기 (회원아이디)
select * from orders o 
join order_item oi 
on o.o_no=oi.o_no
join product p
on oi.p_no = p.p_no
where m_id = 'sy1';
-- 주문 목록 (주문아이템+제품) 한 개보기 (회원아이디 + 주문번호)
select * 
from orders o
join order_item oi
on o.o_no = oi.o_no
join product p
on oi.p_no = p.p_no
where m_id = 'sy1' and o.o_no=2;
-- 주문 1건 삭제 (주문번호)
delete orders where o_no = 1;
-- 주문 전체 삭제 (회원아이디)
delete orders where m_id = 'sy1';


