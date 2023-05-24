CREATE DATABASE ecsite_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE users(
     id INT(10) NOT NULL  PRIMARY KEY AUTO_INCREMENT,
     username VARCHAR(100) NOT NULL,
     password VARCHAR(100) NOT NULL,
     authority enum('ADMIN','MAKER','SHOP') NOT NULL,
     campany VARCHAR(100) NOT NULL,
     address VARCHAR(100) NOT NULL,
     phone VARCHAR(255) NOT NULL
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     created_user VARCHAR(100) NOT NULL,
--     updated_user VARCHAR(100)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into users (username, password, authority, campany, address, phone) values
("bob","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob1","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag1","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy1","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson1","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin1","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim1","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom1","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan1","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob2","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag2","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy2","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson2","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin2","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim2","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom2","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan2","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob3","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag3","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy3","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson3","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin3","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim3","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom3","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan3","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob4","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag4","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy4","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson4","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin4","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim4","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom4","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan4","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob5","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag5","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy5","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson5","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin5","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim5","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom5","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan5","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob6","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag6","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy6","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson6","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin6","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim6","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom6","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan6","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),

("bob7","9e7d2dd05832011de87fd4b5ecf0e32dc6759a3b12be01d9b49c91ff2c8038b8e27d6f0ece11c86f","MAKER","ecシステム","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("dag7","0adf569b4a6f43cde80a2c301171d0da1d63f7f47b1654169ca05824ba6e2256b72c5824cbf77985","SHOP","LION","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jimmy7","e925a6b23a110a5d81bc7daca7c0f02475d3f16efd1e4c83de305b3680efabffbf7ab4eb62c3b8de","MAKER","ダイソー","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("jonson7","45c02cb55e70d3eb0d7948a2d6258336f898b7276ffe0b4e8989440b5b78b8bc4fee5f2bbb35cd40","MAKER","丸々工業","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("kebin7","d1bba4d4a4f868494ece912fb4858eab29242d0db6e455701a69ed0ec527038ca33aebbeedcaf02d","SHOP","ヤマダ電機","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tim7","c87b4bcb9c2c1cc75dbf200934406cd619d8d909c9cb4f98dd693905261ebcb93d2536af4070bada","MAKER","ELECOM","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("tom7","36367251bb4fc35d3c72e22bec7ba80d1802251706f7d5c1c7856a3ec7bd33af35c6a5327ff2e3e0","ADMIN","無印良品","東京都港区1--1-1 丸々ビル1F","080-9999-9999"),
("yan7","58f4f3e88c488c08a836882ff4ac813ee5016f4a460d6850a8899a6182f66971374107caeee1ca26","SHOP","丸々硝子","東京都港区1--1-1 丸々ビル1F","080-9999-9999");


CREATE TABLE items(
        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
        itemname VARCHAR(20)  NOT NULL,
        description VARCHAR(100)  NOT NULL,
        company VARCHAR(100)  NOT NULL,
        price INT(10) NOT NULL DEFAULT 0,
        stock INT(10) NOT NULL DEFAULT 1,
--        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--        created_user VARCHAR(100) DEFAULT NULL,
--        updated_user VARCHAR(100) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO items (itemname, description, company, price, stock ) VALUES
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5"),
("歯ブラシ","歯ブラシ（極細）","LION","198","3"),
("歯磨き粉","フッ素配合歯磨き粉","サンスター","198","3"),
("櫛","プラスチック加工の櫛","丸々工業","198","40"),
("マウス","レーザーマウス","ELECOM","2200","5"),
("キーボード","スピーカー内臓キーボード","ELECOM","1300","10"),
("USBケーブル","2メートル用","ELECOM","760","12"),
("コップ","ガラス製コップ","丸々硝子","980","6"),
("イヤホン","ノイズキャンセリング対応","SONY","1800","7"),
("財布","黒皮の財布","丸々レザー","9800","1"),
("化粧水","保湿力高い","NIVEA","980","5");

--CREATE TABLE items(
--        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
--        itemname VARCHAR(20)  NOT NULL COMMENT '商品名',
--        description VARCHAR(100)  NOT NULL COMMENT '商品詳細',
--        itemimage MEDIUMBLOB  DEFAULT NULL COMMENT '商品画像',
--        company VARCHAR(100)  NOT NULL,
--        price INT(10) NOT NULL DEFAULT 0,
--        stock INT(10) NOT NULL DEFAULT 1
----        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
----        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
----        created_user VARCHAR(100) DEFAULT NULL,
----        updated_user VARCHAR(100) DEFAULT NULL
--) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE items(
        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
        itemname VARCHAR(20)  NOT NULL COMMENT '商品名',
        description VARCHAR(100)  NOT NULL COMMENT '商品詳細',
        filename VARCHAR(255)   DEFAULT NULL COMMENT 'ファイル名',
        itemimage MEDIUMBLOB  DEFAULT NULL COMMENT '商品画像',
        company VARCHAR(20)  NOT NULL COMMENT '販売会社',
        price VARCHAR(20) NOT NULL DEFAULT 0 COMMENT '販売価格（円）',
        stock INT(10) NOT NULL DEFAULT 1 COMMENT '在庫数（個）',
        payment enum('INVOICE','BANK','CREDIT') DEFAULT NULL COMMENT '支払い方法'
--        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--        created_user VARCHAR(100) DEFAULT NULL,
--        updated_user VARCHAR(100) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

insert into users (username, password, authority, campany, address, phone) values

INSERT INTO items (itemname, description, filename, itemimage, company, price, stock, payment) VALUES
("歯ブラシ","歯ブラシ（極細）",NULL,NULL ,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL);

("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL),
("歯ブラシ","歯ブラシ（極細）",NULL,NULL,"LION","198","3",NULL),
("歯磨き粉","フッ素配合歯磨き粉",NULL,NULL,"サンスター","198","3",NULL),
("櫛","プラスチック加工の櫛",NULL,NULL,"丸々工業","198","40",NULL),
("マウス","レーザーマウス",NULL,NULL,"ELECOM","2200","5",NULL),
("キーボード","スピーカー内臓キーボード",NULL,NULL,"ELECOM","1300","10",NULL),
("USBケーブル","2メートル用",NULL,NULL,"ELECOM","760","12",NULL),
("コップ","ガラス製コップ",NULL,NULL,"丸々硝子","980","6",NULL),
("イヤホン","ノイズキャンセリング対応",NULL,NULL,"SONY","1800","7",NULL),
("財布","黒皮の財布",NULL,NULL,"丸々レザー","9800","1",NULL),
("化粧水","保湿力高い",NULL,NULL,"NIVEA","980","5",NULL);



CREATE TABLE orders(
        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '注文ID',
        user_id INT(10) FOREIGN KEY NOT NULL COMMENT '会員ID',
        item_id INT(10)  FOREIGN KEY NOT NULL COMMENT '商品ID',
        company VARCHAR(100)  NOT NULL,
        stock INT(10) NOT NULL DEFAULT 1,
--        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--        created_user VARCHAR(100) DEFAULT NULL,
--        updated_user VARCHAR(100) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE orders(
        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
        user_id INT(10) NOT NULL,
        item_id INT(10) NOT NULL,
        quantity INT(10) NOT NULL DEFAULT 1
--        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--        created_user VARCHAR(100) DEFAULT NULL,
--        updated_user VARCHAR(100) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO oders (user_id, item_id, quantity) VALUES
(1,1,1);