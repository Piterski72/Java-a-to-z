\c track;
INSERT INTO users (first_name, last_name, password) VALUES ('Ann', 'Ivanova', '123'), ('Pit', 'Ivanov', '111'), ('Mike', 'Petrov', 'aaa');
\copy users (first_name, last_name, password) FROM 'c:/sql/user_data.txt';

INSERT INTO tasks (task_desc, task_state, task_type, task_date, user_id) VALUES ('task_one', 'TRUE', 'in progress', '2017-02-20', 2), ('task_two', 'FALSE', 'fixed', '2017-04-20', 3);

INSERT INTO comments (comm_desc, comm_date, task_id) VALUES ('lalala', '2017-03-13', 1), ('testdesc', '2017-05-15', 2);

INSERT INTO roles (role_type) VALUES ('admin'), ('user'), ('backup_user'), ('guest');

INSERT INTO files_attached (file_path, task_id) VALUES ('c:/sql/', 1);

INSERT INTO user_info (phone_no, user_id, email) VALUES ('575435', 2, 'piterski@inbox.ru');

INSERT INTO user_role (user_id, role_id) VALUES (1, 2), (2, 1), (3, 2), (4, 3), (5, 4), (6, 2); 

