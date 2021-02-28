create table user_table (
	user_id serial not null primary key,
	user_name varchar(15) not null,
	user_password varchar(15) not null
);

alter table user_table alter column user_password type varchar(100);

create table user_role (
	role_id serial not null primary key,
	role_name varchar(50) not null
);

create table user_to_role (
	user_id integer not null,
	role_id integer not null
);

alter table user_to_role add constraint fk_user_id
foreign key(user_id) references user_table(user_id)
on delete cascade;

alter table user_to_role add constraint fk_role_id
foreign key(role_id) references user_role(role_id)
on delete cascade;

create table addressee(
addressee_id serial not null primary key,
addressee_email varchar(50),
addressee_name varchar(50)
);

create table user_to_addressee (
	user_id integer not null,
	addressee_id integer not null,
	foreign key(user_id) references user_table(user_id)
	on delete cascade,
	foreign key(addressee_id) references addressee(addressee_id)
	on delete cascade
);

create table email_scheduler(
	scheduler_id serial not null primary key,
	scheduler_name varchar(50) not null,
	mail varchar(50) not null,
	scheduler_password varchar(50) not null
);

