CREATE TABLE public.users
(
  id           INTEGER                           NOT NULL DEFAULT nextval('users_id_seq' :: REGCLASS),
  name         TEXT COLLATE pg_catalog."default" NOT NULL,
  login        TEXT COLLATE pg_catalog."default" NOT NULL,
  email        TEXT COLLATE pg_catalog."default" NOT NULL,
  "createDate" BIGINT                            NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
  OWNER TO postgres;