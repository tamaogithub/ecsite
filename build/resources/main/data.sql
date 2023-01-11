insert into issues (summary, description) values ('歯ブラシ', '歯を磨く道具です');
insert into issues (summary, description) values ('カッター', '工作用カッターです');
insert into issues (summary, description) values ('ペン', '油性ボールペンです');

insert into items (name, detail) values ('ペン', '油性ボールペンです');

create table items (
item_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
item_name VARCHAR(256) NOT NULl,
item_description VARCHAR(256) NOT NULl
);

-- password1234
insert into users (username, password, authority) values ('tom', '28ee1e646cbe4b4d19fce560683c31411b5073be0092b62975219025f273800b30fdab6520c06343', 'ADMIN');
-- password1234
insert into users (username, password, authority) values ('bob', '4c7b56cfed81c2b0d13ac766a79981adbf0fc1dd7caba700b3ed02a40312a1ed51bca03ccd6456ad', 'MAKER');
-- password1234
insert into users (username, password, authority) values ('dag', '70de9abf34908101fb061aaf0b3d3207bc9d1f084e525c81845ead98842b695684e384d953275520', 'SHOP');
