CREATE OR REPLACE FUNCTION log_last_update()
  RETURNS trigger AS
$BODY$
BEGIN
   New.updated_datetime = now();
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

drop trigger if exists last_row_update on students;

CREATE TRIGGER last_row_update
    BEFORE UPDATE
    ON students
	for each row
	when (OLD IS DISTINCT FROM NEW)
    EXECUTE PROCEDURE public.log_last_update();