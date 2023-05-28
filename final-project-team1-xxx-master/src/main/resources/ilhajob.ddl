    drop table app cascade constraints;
    drop table awards cascade constraints;
    drop table blog cascade constraints;
    drop table blog_comment cascade constraints;
    drop table blog_cate cascade constraints;
    drop table corp cascade constraints;
    drop table corp_bookmark cascade constraints;
    drop table corp_image cascade constraints;
    drop table corp_tag cascade constraints;
    drop table cv cascade constraints;
    drop table edu cascade constraints;
    drop table exp cascade constraints;
    drop table manager cascade constraints;
    drop table message cascade constraints;
    drop table orders cascade constraints;
    drop table payment cascade constraints;
    drop table product cascade constraints;
    drop table recruit cascade constraints;
    drop table recruit_tag cascade constraints;
    drop table review cascade constraints;
    drop table tag cascade constraints;
    drop table userinfo cascade constraints;
    drop table recruit_scrap cascade constraints;
    
    drop sequence app_id_seq;
    drop sequence awards_id_seq;
    drop sequence blog_id_seq;
    drop sequence blog_comment_id_seq;
    drop sequence blog_cate_id_seq;
    drop sequence bookmark_id_seq;
    drop sequence corp_id_seq;
    drop sequence corp_image_id_seq;
    drop sequence corp_tag_id_seq;
    drop sequence cv_id_seq;
    drop sequence edu_id_seq;
    drop sequence exp_id_seq;
    drop sequence manager_id_seq;
    drop sequence message_id_seq;
    drop sequence orders_id_seq;
    drop sequence payment_id_seq;
    drop sequence product_id_seq;
    drop sequence recruit_id_seq;
    drop sequence recruit_tag_id_seq_gen;
    drop sequence review_id_seq;
    drop sequence tag_id_seq;
    drop sequence user_id_seq;
    drop sequence recruit_scrap_id_seq;
    
 create sequence app_id_seq start with 1 increment by  1;
 create sequence awards_id_seq start with 1 increment by  1;
 create sequence blog_id_seq start with 1 increment by  1;
 create sequence blog_comment_id_seq start with 1 increment by  1;
 create sequence blog_cate_id_seq start with 1 increment by  1;
 create sequence bookmark_id_seq start with 1 increment by  1;
 create sequence corp_id_seq start with 1 increment by  1;
 create sequence corp_image_id_seq start with 1 increment by  1;
 create sequence corp_tag_id_seq start with 1 increment by  1;
 create sequence cv_id_seq start with 1 increment by  1;
 create sequence edu_id_seq start with 1 increment by  1;
 create sequence exp_id_seq start with 1 increment by  1;
 create sequence manager_id_seq start with 1 increment by  1;
 create sequence message_id_seq start with 1 increment by  1;
 create sequence orders_id_seq start with 1 increment by  1;
 create sequence payment_id_seq start with 1 increment by  1;
 create sequence product_id_seq start with 1 increment by  1;
 create sequence recruit_id_seq start with 1 increment by  1;
 create sequence recruit_tag_id_seq_gen start with 1 increment by  50;
 create sequence review_id_seq start with 1 increment by  1;
 create sequence tag_id_seq start with 1 increment by  1;
 create sequence user_id_seq start with 1 increment by  1;
 create sequence recruit_scrap_id_seq start with 1 increment by  1;
 
    
    create table app (
       id number(19,0) not null,
        app_status number(1) DEFAUlT 0,
        app_cv_name varchar2(255 char),
        cv_id number(19,0),
        recruit_id number(19,0),
        app_create_date timestamp DEFAULT sysdate,
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table awards (
       id number(19,0) not null,
        awards_content varchar2(1000 char),
        awards_date timestamp not null,
        awards_name varchar2(255 char) not null,
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table blog (
       id number(19,0) not null,
        blog_content varchar2(1000 char) not null,
        blog_date timestamp,
        blog_image varchar2(255 char),
        blog_like number(10,0) not null,
        blog_read_count number(10,0) not null,
        blog_title varchar2(500 char) not null,
        blog_cate_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    create table blog_cate (
       id number(19,0) not null,
       blog_cate_name varchar2(20 char) not null,
       primary key (id)
    );
    
    create table blog_comment (
       id number(19,0) not null,
        comment_content varchar2(1000 char) not null,
        comment_date timestamp,
        blog_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table corp (
       id number(19,0) not null,
        corp_post_code number(10),
        corp_address varchar2(600 char),
        corp_address_detail varchar2(600 char),
        corp_business_no varchar2(255 char),
        corp_comment varchar2(1000 char),
        corp_est timestamp,
        corp_login_id varchar2(255 char) not null,
        corp_name varchar2(255 char),
        corp_password varchar2(255 char) not null,
        corp_phone varchar2(255 char),
        corp_sales varchar2(255 char),
        corp_size varchar2(255 char),
        corp_website varchar2(255 char),
        corp_welfare varchar2(600 char),
        corp_stored_file_name varchar2(255 char),
        rc_count number(1) DEFAULT 0,
        payment_status NUMBER(1) DEFAULT 0,
        update_status NUMBER(1) DEFAULT 0,
        job varchar2(255 char),
        role NUMBER(1) DEFAULT 2,
        primary key (id)
    );
 
    
    create table corp_bookmark (
       id number(19,0) not null,
        corp_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table corp_image (
       id number(19,0) not null,
        original_file_name varchar2(255 char),
        stored_file_name varchar2(255 char),
        corp_id number(19,0),
        primary key (id)
    );
 
    
    create table corp_tag (
       id number(19,0) not null,
        corp_id number(19,0),
        tag_id number(19,0),
        primary key (id)
    );
 
    
    create table cv (
       id number(19,0) not null,
        cv_description varchar2(1000 char) not null,
        cv_name VARCHAR2(90) DEFAULT 'my resume',
        cv_portfolio varchar2(255 char),
        cv_image varchar2(255 char),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table edu (
       id number(19,0) not null,
        edu_content varchar2(1000 char),
        edu_end_date timestamp not null,
        edu_major varchar2(255 char) not null,
        edu_name varchar2(255 char) not null,
        edu_score varchar2(255 char),
        edu_start_date timestamp not null,
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table exp (
       id number(19,0) not null,
        exp_content varchar2(1000 char),
        exp_corp_name varchar2(255 char) not null,
        exp_end_date timestamp not null,
        exp_position varchar2(255 char) not null,
        exp_start_date timestamp not null,
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table manager (
       id number(19,0) not null,
        manager_email varchar2(255 char),
        manager_name varchar2(255 char),
        manager_phone varchar2(255 char),
        manager_position varchar2(255 char),
        corp_id number(19,0),
        primary key (id)
    );
 
    
    create table message (
       id number(19,0) not null,
        message_contents varchar2(1000 char),
        message_date timestamp,
        message_title varchar2(255 char),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table orders (
       id number(19,0) not null,
        order_start_date timestamp DEFAULT sysdate,
        order_end_date timestamp,
        order_valid number(2) not null,
        corp_id number(19,0),
        product_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table payment (
       id number(19,0) not null,
        payment_date timestamp,
        payment_method varchar2(255 char),
        payment_price number(10,0) not null,
        corp_id number(19,0),
        orders_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table product (
       id number(19,0) not null,
        product_div varchar2(255 char),
        product_Period number(10,0) not null,
        product_name varchar2(255 char),
        product_price number(10,0) not null,
        primary key (id)
    );
 
    
    create table recruit (
       id number(19,0) not null,
        rc_content varchar2(1000 char),
        rc_date timestamp,
        rc_deadline timestamp,
        rc_position varchar2(255 char),
        rc_qualification varchar2(255 char),
        rc_read_count NUMBER(19) DEFAULT 0,
        rc_status number(1) DEFAUlT 0,
        rc_app_count number(1) DEFAUlT 0,
        rc_salary number(10,0),
        rc_title varchar2(600 char),
        corp_id number(19,0),
        primary key (id)
    );
 
    
    create table recruit_tag (
       id number(19,0) not null,
        recruit_id number(19,0),
        tag_id number(19,0),
        primary key (id)
    );
 
    
    create table review (
       id number(19,0) not null,
        review_content varchar2(1000 char) not null,
        review_grade number(10,0) not null,
        review_title varchar2(255 char) not null,
        corp_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
 
    
    create table tag (
       id number(19,0) not null,
        tag_name varchar2(60 char),
        primary key (id)
    );
 
     create table userinfo (
      id number(19,0) not null,
        role number(10,0) not null,
        sns_id varchar2(255 char),
        sns_type varchar2(255 char),
        state number(10,0) not null,
        user_address varchar2(600 char),
        user_address_detail varchar2(600 char),
        user_age number(10,0),
        user_email varchar2(255 char) not null,
        user_image varchar2(255 char),
        user_language varchar2(255 char),
        user_name varchar2(255 char),
        user_password varchar2(255 char) not null,
        user_phone varchar2(255 char),
        user_post_code number(10,0),
        user_sex varchar2(255 char),
        user_skills varchar2(300 char),
        payment_status NUMBER(1) DEFAULT 0,
        write_status NUMBER(1) DEFAULT 0,
        primary key (id)
    );
    
     create table recruit_scrap (
       id number(19,0) not null,
        recruit_id number(19,0),
        user_id number(19,0),
        primary key (id)
    );
    
    alter table app 
       add constraint FKhaptmc50bdgwogvgkytx3b0h 
       foreign key (cv_id) 
       references cv;
 
    
    alter table app 
       add constraint FK5lafg6ip10co6ijiim6og71v5 
       foreign key (recruit_id) 
       references recruit
       on delete set null;
 
    
    alter table app 
       add constraint FKmhrqu9rty0cx0pknfhusir8rw 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table awards 
       add constraint FKmdh5yuycy44a4ru9htlb47jkg 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table blog 
       add constraint FKi0cp2kqmhwbl6nworpv29cdf5 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table blog_comment 
       add constraint FKb9cpog8ie2cyapsyyt7gikpbl 
       foreign key (blog_id) 
       references blog;
 
    
    alter table blog_comment 
       add constraint FKs0scqibej2xooahxkfbpigcjp 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table corp_bookmark 
       add constraint FK61x9uo3q9e3642utnpdvwmn6w 
       foreign key (corp_id) 
       references corp;
 
    
    alter table corp_bookmark 
       add constraint FK2uqhc4c147pbrrym3quxijgqb 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table corp_image 
       add constraint FKi6oy66mmd9rvfn3uwkg9gm7e2 
       foreign key (corp_id) 
       references corp;
 
    
    alter table corp_tag 
       add constraint FKgpvuxcuuoykuq4rmsdisnc4vg 
       foreign key (corp_id) 
       references corp;
 
    
    alter table corp_tag 
       add constraint FKbnt8ihueusdygdkiuviv0qlt7 
       foreign key (tag_id) 
       references tag;
 
    
    alter table cv 
       add constraint FKcjg8evmuhy2vrchtgpfi7648c 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table edu 
       add constraint FKnmd3v4k608hdoov7n83d8apug 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table exp 
       add constraint FKm7trs6qo15jhoeta9hg8xgoj9 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table manager 
       add constraint FKnf66cq2r5a0ok0reveck54y8 
       foreign key (corp_id) 
       references corp;
 
    
    alter table message 
       add constraint FKdlq5w7vhyxs8k858iew0oiofg 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table orders 
       add constraint FKsdk475h4bgeoy27ldl61tqb8l 
       foreign key (corp_id) 
       references corp;
 
    
    alter table orders 
       add constraint FK787ibr3guwp6xobrpbofnv7le 
       foreign key (product_id) 
       references product;
 
    
    alter table orders 
       add constraint FKk1gxhkl6vt0gjuxasp1oxkyo8 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table payment 
       add constraint FKceeasa0grglbems2u2jkwb1dl 
       foreign key (corp_id) 
       references corp;
 
    
    alter table payment 
       add constraint FK2tuuxhx91545ge3pvefdhr9g7 
       foreign key (orders_id) 
       references orders;
 
    
    alter table payment 
       add constraint FK8effxotef77tj627999jtpxee 
       foreign key (user_id) 
       references userinfo;
 
    
    alter table recruit 
       add constraint FKsni00gi50fy4l932oapbdj2me 
       foreign key (corp_id) 
       references corp;
 
    
    alter table recruit_tag 
       add constraint FKelwb3nmj9ngwt4p959nb6478n 
       foreign key (recruit_id) 
       references recruit on delete cascade;
 
    
    alter table recruit_tag 
       add constraint FK70a55wyf8xfl8nyehxicoe8vu 
       foreign key (tag_id) 
       references tag;
 
    
    alter table review 
       add constraint FK4xmgyxigv7ukkjetswofpvqvn 
       foreign key (corp_id) 
       references corp;
 
 	
    alter table review 
       add constraint FKamsd01dhdcxmcoyfwhfrakb18 
       foreign key (user_id) 
       references userinfo;
       
    alter table recruit_scrap 
       add constraint FK6jj2rpr54ffao7g8jxxs1uu2d 
       foreign key (recruit_id) 
       references recruit;