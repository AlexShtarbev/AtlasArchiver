SET sql_safe_updates = false;

ALTER TABLE items DROP COLUMN image;

ALTER TABLE items ADD COLUMN image_url STRING;