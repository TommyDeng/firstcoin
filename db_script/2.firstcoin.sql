--创建数据库实例
CREATE DATABASE firstcoin;

--日志表：用户登录记录
DROP TABLE IF EXISTS LOG_VISIT;
CREATE TABLE LOG_VISIT(
    ID serial PRIMARY KEY,
    NAME VARCHAR,
    REMARK VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT now()
); 


--条目主表
drop table if exists recommendation;
create table recommendation(
    item_id varchar not null, --条目ID主键
    ore_id varchar not null, --oreID
    unique_code uuid not null, --记录唯一标示
    prod_id varchar, 
    prod_url varchar,
    prod_name varchar,
    prod_pic varchar,
    merchant_id varchar,
    merchant_name varchar,
    brand_id varchar,
    brand_name varchar,
    price varchar, --decimal(20, 2),
    price_txt varchar,
    price_status varchar,
    description varchar,
    update_time varchar,--timestamp,
    batch_no varchar,
    create_time timestamp default now()
);
--联合主键(ore id和条目id)
alter table recommendation add primary key (item_id, ore_id);


--条目主表
drop table if exists recommendation_history;
create table recommendation_history(
    item_id varchar not null, --条目ID主键
    ore_id varchar not null, --oreID
    unique_code uuid not null, --记录唯一标示
    prod_id varchar, 
    prod_url varchar,
    prod_name varchar,
    prod_pic varchar,
    merchant_id varchar,
    merchant_name varchar,
    brand_id varchar,
    brand_name varchar,
    price decimal(20, 2),
    price_txt varchar,
    price_status varchar,
    description varchar,
    update_time timestamp,
    batch_no varchar,
    create_time timestamp default now()
);
--联合主键(ore id和条目id)
alter table recommendation_history add primary key (item_id, ore_id);