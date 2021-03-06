
-- user and role and user_to_role
insert into user_table (user_name, user_password) values
('User0', 'Password0'),
('User1', 'Password1'),
('User2', 'Password2');

INSERT INTO user_role(role_name)VALUES
('ROLE_OTHER'),
('ROLE_USER'),
('ROLE_ADMIN');

insert into user_to_role (user_id, role_id) values
('1', '2'),
('2', '2'),
('3', '1');

-- addressee
insert into addressee (addressee_email, addressee_name) values
('mail1', 'name1'),
('mail2', 'name2'),
('mail3', 'name3');

insert into user_to_addressee (user_id, addressee_id) values
(1, 1),
(1, 2),
(2, 3);

--email scheduler
insert into email_scheduler(scheduler_name, mail, scheduler_password) values
('scheduledName', 'scheduledMail', 'scheduledPassword');
