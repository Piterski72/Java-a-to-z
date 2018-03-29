-- Table: public.roles

-- DROP TABLE public.roles;

CREATE TABLE public.roles
(
  roleid   INTEGER                           NOT NULL DEFAULT nextval('roles_roleid_seq' :: REGCLASS),
  rolename TEXT COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (roleid),
  CONSTRAINT uniq_name UNIQUE (rolename)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.roles
  OWNER TO postgres;