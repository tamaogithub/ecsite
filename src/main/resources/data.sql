insert into issues (summary, description) values ('歯ブラシ', '歯を磨く道具です');
insert into issues (summary, description) values ('カッター', '工作用カッターです');
insert into issues (summary, description) values ('ペン', '油性ボールペンです');


insert into items (itemname) values ('ペン');
insert into items (itemname) values ('歯ブラシ');
insert into items (itemname) values ('カッター', '工作用カッターです');


INSERT INTO items (itemname, description, ) VALUES ("電池","単三電池です");
INSERT INTO items (user, description, ) VALUES ("電池","単三電池です");


//http://localhost:8080/items?limit=10&offset=0
UPDATE items SET payment="INVOICE" WHERE id=3;
UPDATE items SET description="油性ぺんです" WHERE id=1;
UPDATE items SET description="歯を磨く道具です" WHERE id=2;
UPDATE items SET description="工作用カッターです" WHERE id=3;

DELETE items
DELETE FROM items WHERE id = 5;

-- password1234
insert into users (username, password, authority) values
 ('tom2', '28ee1e646cbe4b4d19fce560683c31411b5073be0092b62975219025f273800b30fdab6520c06343', 'ADMIN');

-- password1234
insert into users (username, password, authority) values ('bob', '4c7b56cfed81c2b0d13ac766a79981adbf0fc1dd7caba700b3ed02a40312a1ed51bca03ccd6456ad', 'MAKER');
-- password1234
insert into users (username, password, authority) values ('dag', '70de9abf34908101fb061aaf0b3d3207bc9d1f084e525c81845ead98842b695684e384d953275520', 'SHOP');
