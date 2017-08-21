\c track;

CREATE TABLE users( 
    user_id SERIAL NOT NULL PRIMARY KEY,
    first_name text NOT NULL,
    last_name text NOT NULL,
    password text NOT NULL
);

CREATE TABLE tasks(
    task_id SERIAL NOT NULL PRIMARY KEY,
    task_desc text NOT NULL,
    task_state boolean NOT NULL,
    task_type text NOT NULL,
    task_date timestamp with time zone NOT NULL,
    user_id integer NOT NULL,
	FOREIGN KEY (user_id)
	REFERENCES users (user_id)
);

CREATE TABLE comments(
    comm_id SERIAL NOT NULL PRIMARY KEY,
    comm_desc text NOT NULL,
    comm_date timestamp with time zone NOT NULL,
    task_id integer NOT NULL,
	FOREIGN KEY (task_id)
	REFERENCES tasks (task_id)
);

CREATE TABLE roles(
    role_id SERIAL NOT NULL PRIMARY KEY,
    role_type text NOT NULL
);

CREATE TABLE files_attached(
    file_id SERIAL NOT NULL PRIMARY KEY,
    file_path text NOT NULL,
    task_id integer NOT NULL,
	FOREIGN KEY (task_id)
	REFERENCES tasks (task_id)
);

CREATE TABLE user_info(
    id SERIAL NOT NULL PRIMARY KEY,
    phone_no text NOT NULL,
    user_id integer NOT NULL,
    email text NOT NULL,
	FOREIGN KEY (user_id)
	REFERENCES users (user_id)
);

CREATE TABLE public.user_role(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
	FOREIGN KEY (user_id)
	REFERENCES users (user_id),
	FOREIGN KEY (role_id)
	REFERENCES roles (role_id)
);
