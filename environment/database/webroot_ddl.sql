
# This is a placeholder table to validate after deployment

drop table webroot.people;
CREATE TABLE webroot.PEOPLE
(
   id character varying(10) COLLATE pg_catalog."default",
   name character varying(30) COLLATE pg_catalog."default"
)
WITH (
   OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE webroot.PEOPLE OWNER to postgres;
insert into webroot.PEOPLE values ('E2493R', 'ELDHO THOMAS');
