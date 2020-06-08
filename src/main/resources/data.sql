INSERT INTO my_users (username, password, enabled) values ('JohnUser', 'pass', true );
INSERT INTO my_users (username, password, enabled) values ('JoeAdmin', 'pass', true );
INSERT INTO my_users (username, password, enabled) values ('FredAdmin', 'pass', true );

INSERT INTO my_authorities (username, authority) values ('JohnUser', 'ROLE_USER');
INSERT INTO my_authorities (username, authority) values ('JoeAdmin', 'ROLE_ADMIN');
INSERT INTO my_authorities (username, authority) values ('FredAdmin', 'ROLE_ADMIN')