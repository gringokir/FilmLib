insert into users(id, active, password, username)
values (1, true, '', 'k'),
       (2, true, '', 'u');

insert into user_role(user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN'),
       (2, 'USER')
