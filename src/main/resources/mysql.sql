create database myblog;
use myblog;

create table kind(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key(id)
);

create table article(
	id varchar(36) not null,
	title varchar(50) not null,
    content text null,
	create_time datetime,
	primary key(id)
);

create table tag(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key(id)
);


create table article_with_kind(
	id varchar(36) not null,
	article_id  varchar(36) not null,
	kind_id  varchar(36) not null,
	primary key(id)
);

create table article_with_tag(
	id varchar(36) not null,
	article_id  varchar(36) not null,
	tag_id  varchar(36) not null,
	primary key(id)
);