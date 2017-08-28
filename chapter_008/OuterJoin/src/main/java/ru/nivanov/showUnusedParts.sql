SELECT
  e.engine_type AS type,
  e.eng_id      AS id,
  'engine'      AS category

FROM engine AS e
  LEFT OUTER JOIN car AS c ON c.engine_id = e.eng_id
WHERE c.car_id IS NULL

UNION

SELECT
  g.gear_type AS type,
  g.gear_id   AS id,
  'gear'      AS category

FROM gear AS g
  LEFT OUTER JOIN car AS c ON c.gear_id = g.gear_id
WHERE c.car_id IS NULL

UNION

SELECT
  t.trans_type   AS type,
  t.trans_id     AS id,
  'transmission' AS category

FROM transmission AS t
  LEFT OUTER JOIN car AS c ON c.transm_id = t.trans_id
WHERE c.car_id IS NULL;


