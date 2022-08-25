INSERT INTO users(username, password, enabled)
values
       ('admin1', 'pass', true);

INSERT INTO authorities(username, authority)
values ('user', 'ROLE_USER'),
 ('admin', 'ROLE_ADMIN');