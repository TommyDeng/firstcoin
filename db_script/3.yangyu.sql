--条目主表
drop table if exists yangyu_rewards;
create table yangyu_rewards(
	id serial primary key,
    order_code varchar, --
    customer_code varchar, --
    customer_name varchar, 
    customer_mobile varchar,
    prod_name varchar,
    prod_count numeric(10,2),
    prod_total_amt numeric(20,2),
    return_days int,
    first_return_date date default now(),
    create_time timestamp default now()
);

