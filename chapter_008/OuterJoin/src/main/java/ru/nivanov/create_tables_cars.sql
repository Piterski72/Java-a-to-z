--connect to cars database

\c cars;

--DROP TABLE car;
--DROP TABLE transmission;
--DROP TABLE engine;
--DROP TABLE gear;

--create table transmission

CREATE TABLE transmission (
  trans_id   SERIAL NOT NULL PRIMARY KEY,
  trans_type TEXT   NOT NULL
);

--create table engine

CREATE TABLE engine (
  eng_id      SERIAL NOT NULL PRIMARY KEY,
  engine_type TEXT   NOT NULL
);

--create table gear

CREATE TABLE gear (
  gear_id   SERIAL NOT NULL PRIMARY KEY,
  gear_type TEXT   NOT NULL
);

--create table car

CREATE TABLE car (
  car_id    SERIAL  NOT NULL PRIMARY KEY,
  brand     TEXT    NOT NULL,
  model     TEXT    NOT NULL,
  transm_id INTEGER NOT NULL REFERENCES transmission (trans_id),
  engine_id INTEGER NOT NULL REFERENCES engine (eng_id),
  gear_id   INTEGER NOT NULL REFERENCES gear (gear_id)

);



