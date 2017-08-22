--show all users with role containing 'user'

CREATE VIEW userRolesLikeUser AS

  SELECT
    u.first_name,
    u.last_name,
    ui.email,
    r.role_type
  FROM user_role AS ur
    INNER JOIN users AS u ON u.user_id = ur.user_id
    INNER JOIN roles AS r ON r.role_id = ur.role_id
    INNER JOIN user_info AS ui ON ui.user_id = ur.user_id
  WHERE r.role_type LIKE '%user';

SELECT *
FROM userRolesLikeUser;

--show all users with id in list (1, 3, 8)

CREATE VIEW userRoles1_3_8 AS

  SELECT
    u.first_name,
    u.last_name,
    ui.email,
    r.role_type
  FROM user_role AS ur
    INNER JOIN users AS u ON u.user_id = ur.user_id
    INNER JOIN roles AS r ON r.role_id = ur.role_id
    INNER JOIN user_info AS ui ON ui.user_id = ur.user_id
  WHERE u.user_id IN (1, 3, 8);

SELECT *
FROM userRoles1_3_8;