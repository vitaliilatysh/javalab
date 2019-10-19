drop trigger if exists row_update_trigger on students;

CREATE OR REPLACE FUNCTION set_updated_datetime()
  RETURNS trigger AS
$BODY$
BEGIN
   New.updated_datetime = now();
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER row_update_trigger
    BEFORE UPDATE
    ON students
	for each row
	when (OLD IS DISTINCT FROM NEW)
    EXECUTE PROCEDURE public.set_updated_datetime();