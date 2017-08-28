--show all cars

--DROP VIEW allcars;

CREATE VIEW allcars AS

  SELECT
    c.brand,
    c.model,
    t.trans_type,
    e.engine_type,
    g.gear_type
  FROM car AS c
    INNER JOIN transmission AS t ON c.transm_id = t.trans_id
    INNER JOIN engine AS e ON c.engine_id = e.eng_id
    INNER JOIN gear AS g ON c.gear_id = g.gear_id
  ORDER BY brand;


SELECT *
FROM allcars;
