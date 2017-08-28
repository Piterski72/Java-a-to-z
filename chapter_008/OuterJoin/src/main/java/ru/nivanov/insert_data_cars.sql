--connect to cars database

\c cars;

--inser data into tables

INSERT INTO transmission (trans_id, trans_type)
VALUES (1, 'automatic'), (2, 'variator'), (3, 'hand'), (4, 'robot'), (5, 'voice');

INSERT INTO engine (eng_id, engine_type)
VALUES (1, 'gasoline'), (2, 'diesel'), (3, 'electro'), (4, 'hybrid'), (5, 'steam'), (6, 'muscule');

INSERT INTO gear (gear_id, gear_type)
VALUES (1, '4wd'), (2, 'rear drive'), (3, 'front-wheel drive'), (4, 'one wheel drive');

INSERT INTO car (brand, model, transm_id, engine_id, gear_id)
VALUES ('audi', 'a4', 2, 1, 3), ('bmw', '320d', 1, 2, 2), ('vaz', '2104', 3, 1, 2), ('opel', 'astra', 4, 1, 3),
  ('tesla', 'modelX', 2, 3, 1), ('lexus', 'rx450h', 2, 4, 1);




