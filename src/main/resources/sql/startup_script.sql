CREATE DATABASE IF NOT EXISTS tgstation;

USE tgstation;

create table IF NOT EXISTS crewmember
(
  name varchar(255) not null
    primary key
) CHARSET = UTF8;

create table IF NOT EXISTS crewmember_roles
(
  CrewMember_name varchar(255) not null,
  roles varchar(255) null,
  foreign key (CrewMember_name) references crewmember (name)
) CHARSET = UTF8;

create table IF NOT EXISTS hibernate_sequence
(
  next_val bigint null
);

INSERT INTO hibernate_sequence VALUES (0);

create table IF NOT EXISTS player
(
  ckey varchar(255) not null
    primary key,
  byond varchar(255) not null
) CHARSET = UTF8;

create table IF NOT EXISTS post
(
  id bigint not null
    primary key,
  author text null,
  content text null,
  publishedDate timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
) CHARSET = UTF8;

create table IF NOT EXISTS rule
(
  name varchar(255) not null
    primary key,
  description text null
) CHARSET = UTF8;

create table IF NOT EXISTS website_admins
(
  username varchar(255) not null
    primary key,
  enabled bit not null,
  password varchar(60) not null
) CHARSET = UTF8;

create table IF NOT EXISTS website_admins_authorities
(
  id bigint not null
    primary key auto_increment,
  authority varchar(255) not null,
  username varchar(255) not null,
  foreign key (username) references website_admins (username)
) CHARSET = UTF8;

create table IF NOT EXISTS website_rules
(
  NAME varchar(32) not null
    primary key,
  DESCRIPTION text not null
) engine=MyISAM CHARSET = UTF8;

create table IF NOT EXISTS website_visitors
(
  IP_ADDRESS varchar(18) not null
    primary key,
  LAST_HOST varchar(24) not null,
  LAST_VISIT timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  VISITS int(12) not null
) engine=MyISAM CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS website_visitors (
  IP_ADDRESS VARCHAR(36) NOT NULL,
  LAST_HOST VARCHAR(255) NOT NULL,
  LAST_VISIT TIMESTAMP NOT NULL,
  VISITS BIGINT(16) NOT NULL
) CHARSET = UTF8;