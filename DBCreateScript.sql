drop table if exists goods;
drop table if exists types;
drop table if exists stores;
drop table if exists store_lot;
create table if not exists types (
    type_id serial unique not null primary key,
    type_name character(25) unique not null
);
create table if not exists goods (
    goods_id serial4 unique not null primary key,
    type_id int,
    goods_name character(40) unique not null
);
create table if not exists stores (
    store_id serial not null primary key,
    store_name character(25) unique not null ,
    store_address character(40) not null
);
create table if not exists store_lot (
    lot_id serial4 unique not null primary key,
    goods_id int4 not null ,
    store_id int not null ,
    qty float4
);