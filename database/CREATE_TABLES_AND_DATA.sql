CREATE TABLE VNPT_USER
(
	username varchar(200) PRIMARY KEY NOT NULL
	,password varchar(250) NOT NULL
	,email varchar(250) 
	,user_role_id INT REFERENCES USER_ROLE (user_role_id)
);

CREATE TABLE USER_ROLE
(
	user_role_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY
	,user_role_name varchar(80) NOT NULL
	,description text
);

CREATE TABLE USER_FUNCTION
(
	user_function_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY
	,user_function_name varchar not null
	,description text
);

CREATE TABLE FUNCTION_ROLE
(
	user_role_id INT
	,user_function_id INT
);
ALTER TABLE function_role ADD PRIMARY KEY (user_role_id, user_function_id);

CREATE TABLE category
(
	category_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY
	,category_name varchar(80) NOT NULL
	,description text
	,url varchar(250)
	,parent_id INT
);

INSERT INTO USER_ROLE (user_role_name,description) VALUES('admin','admin role');
INSERT INTO USER_ROLE (user_role_name,description) VALUES('user','user role');

INSERT INTO USER_FUNCTION (user_function_name, description) VALUES('_view', 'NA');
INSERT INTO USER_FUNCTION (user_function_name, description) VALUES('_list', 'NA');
INSERT INTO USER_FUNCTION (user_function_name, description) VALUES('_create', 'NA');
INSERT INTO USER_FUNCTION (user_function_name, description) VALUES('_update', 'NA');
INSERT INTO USER_FUNCTION (user_function_name, description) VALUES('_delete', 'NA');

INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(1, 1);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(1, 2);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(1, 3);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(1, 4);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(1, 5);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(2, 1);
INSERT INTO FUNCTION_ROLE (user_role_id, user_function_id) VALUES(2, 2);

INSERT INTO VNPT_USER(username, password, email, user_role_id) VALUES('hoptq', '123', 'hoptq@vnpt.vn', 1);
INSERT INTO VNPT_USER(username, password, email, user_role_id) VALUES('user', '123', 'hoptq@vnpt.vn', 2);