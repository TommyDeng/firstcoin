--条目主表
drop table if exists fc_recommendation;
create table fc_recommendation(
    item_id bigint not null, --条目ID主键
    ore_id tinyint not null, --oreID
    unique_code uuid not null, --记录唯一标示
    prod_id varchar(50), 
    prod_url varchar(1000),
    prod_name varchar(500),
    prod_pic varchar(1000),
    merchant_id varchar(10),
    merchant_name varchar(50),
    brand_id varchar(10),
    brand_name varchar(50),
    price decimal(20, 2),
    price_txt varchar(100),
    price_status varchar(10),
    description varchar(5000),
    update_time timestamp,
    batch_no varchar(50),
    create_time timestamp default sysdate
);
--联合主键(ore id和条目id)
alter table fc_recommendation add primary key (item_id, ore_id);