chcp 1251
set PGPASSWORD=azsxdc
psql -U pit -d postgres -f createbase.sql
psql -U pit -d postgres -f createtables.sql
psql -U pit -d postgres -f filltables.sql

