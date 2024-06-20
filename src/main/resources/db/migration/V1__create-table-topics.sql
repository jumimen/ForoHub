create table topics(
id bigint not null auto_increment,
title varchar(80)not null,
message varchar(180)not null,
date_creation datetime not null,
status tinytext not null,
author tinytext not null,
course tinytext not null ,
primary key(id)
);