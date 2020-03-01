CREATE TABLE users
(
	-- 用户ID
	uid INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- 用户名
	uname VARCHAR(20) NOT NULL,
	-- 用户密码 MD5加密
	upass VARCHAR(32) NOT NULL,
	-- 用户邮箱
	umail VARCHAR(40) NOT NULL,
	-- 用户权限 0普通用户 1管理员
	uacce INT NOT NULL DEFAULT '0',
	-- 用户积分
	upoit INT NOT NULL DEFAULT '0',
	-- 用户状态 0正常 1封禁
	ustat INT NOT NULL DEFAULT '0'
)

/*
-- 123456 // admin123456
INSERT INTO users
VALUES ('', 'admin', 'a66abb5684c45962d887564f08346e8d', 'email@example.com', '1', '1000', '')

-- 123456 // demouser1123456
INSERT INTO users
VALUES ('', 'demouser1', '57b5d092ba67c05b0ae160d59877781d', 'email@example.com', '', '500', '')

-- 123456 // demouser2123456
INSERT INTO users
VALUES ('', 'demouser2', 'ba24fd07025b574df2c2e30ee8b66666', 'email@example.com', '', '100', '1')

-- 123456 // demouser3123456
INSERT INTO users
VALUES ('', 'demouser3', MD5('demouser3123456'), 'email@example.com', '', '', '1')
*/

CREATE TABLE nodes
(
	-- 节点ID
	nid INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- 节点名
	nname VARCHAR(20) NOT NULL,
	-- 节点简介
	ncont VARCHAR(128) NOT NULL
)

/*
INSERT INTO nodes
VALUES ('', '问与答', '一个更好的世界需要你持续地提出好问题。')

INSERT INTO nodes
VALUES ('', '创意探索', '创造未知，意在未来。')
*/

CREATE TABLE tags
(
	-- 分类ID
	gid INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- 分类名
	gname VARCHAR(20) NOT NULL,
	-- 所属节点ID
	nid INT UNSIGNED,
	FOREIGN KEY (nid) REFERENCES nodes(nid)
)

/*
INSERT INTO tags
VALUES ('', '分享创造', '2')

INSERT INTO tags
VALUES ('', '奇思妙想', '2')
*/

CREATE TABLE topics
(
	-- 话题ID
	tid INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- 话题名
	tname VARCHAR(50) NOT NULL,
	-- 话题内容
	tcont VARCHAR(10000) NOT NULL,
	-- 话题创建时间
	tdate VARCHAR(20) NOT NULL,
	-- 所属用户ID
	uid INT UNSIGNED,
	-- 所属节点ID
	nid INT UNSIGNED,
	-- 所属分类ID
	gid INT UNSIGNED,
	FOREIGN KEY (uid) REFERENCES users(uid),
	FOREIGN KEY (nid) REFERENCES nodes(nid),
	FOREIGN KEY (gid) REFERENCES tags(gid)
)

/*
INSERT INTO topics
VALUES (
'',
'SSL Checker README Document',
'<h1>SSLChecker</h1><p>A Node.js (with Shell Script) tool to check your site&#39;s SSL status. Used Shell Script is <a href="https://github.com/HAIZAKURA/CheckSSL">here</a>.</p><p><a href="https://nya.run"><img alt="Author" src="https://img.shields.io/badge/Author-HAIZAKURA-b68469?style=flat-square"/></a><a href="./LICENSE"><img alt="License" src="https://img.shields.io/github/license/HAIZAKURA/SSLChecker?style=flat-square"/></a></p><h2>Usage</h2><p>First clone this repo:</p><p><code>bash$ git clone https://github.com/HAIZAKURA/SSLChecker.git$ cd SSLChecker</code></p>',
'2020-02-12 16:06:30',
'2',
'2',
'1'
)
*/

CREATE TABLE replys
(
	-- 回复ID
	rid INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	-- 回复内容
	rcont VARCHAR(2000) NOT NULL,
	-- 回复时间
	rdate VARCHAR(20) NOT NULL,
	-- 所属用户ID
	uid INT UNSIGNED,
	-- 所属话题ID
	tid INT UNSIGNED,
	FOREIGN KEY (uid) REFERENCES users(uid),
	FOREIGN KEY (tid) REFERENCES topics(tid)
)

/*
INSERT INTO replys
VALUES (
'',
'<p><strong>Notice: This tool can not work on Windows !</strong></p><p><strong>Notice: The default Config is for Ubuntu 18.04. If you are using CentOS 7, please edit <code>runcheck.sh</code> .</strong></p><h2>Author</h2><p><strong>SSLChecker</strong> © <a href="https://nya.run">HAIZAKURA</a>, Released under the <a href="./LICENSE">MIT</a> License.</p><blockquote><p><a href="https://nya.run">Personal Website</a> · GitHub <a href="https://github.com/HAIZAKURA">@HAIZAKURA</a> · Twitter [@haizakura<em>0v0](https://twitter.com/haizakura</em>0v0) · Telegram <a href="https://t.me/haizakura">@haizakura</a></p></blockquote>',
'2020-02-16 16:06:30',
'1',
'1'
)
*/
