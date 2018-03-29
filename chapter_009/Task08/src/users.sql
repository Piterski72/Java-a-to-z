-- Table: public.usersfinal

-- DROP TABLE public.usersfinal;

CREATE TABLE public.usersfinal
(
  id         INTEGER                           NOT NULL DEFAULT nextval('usersfinal_id_seq' :: REGCLASS),
  username   TEXT COLLATE pg_catalog."default" NOT NULL,
  userlogin  TEXT COLLATE pg_catalog."default" NOT NULL,
  useremail  TEXT COLLATE pg_catalog."default" NOT NULL,
  userpass   TEXT COLLATE pg_catalog."default" NOT NULL,
  roleid     INTEGER                           NOT NULL DEFAULT 2,
  createdate BIGINT                            NOT NULL,
  city       TEXT COLLATE pg_catalog."default",
  country    TEXT COLLATE pg_catalog."default",
  CONSTRAINT usersfinal_pkey PRIMARY KEY (id),
  CONSTRAINT roleid FOREIGN KEY (roleid)
  REFERENCES public.roles (roleid) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usersfinal
  OWNER TO postgres;