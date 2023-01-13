--liquidbase formatted sql
--changeset sanket:change enum in_use and is_active column to true and false meter and consumer table
ALTER TABLE meter CHANGE in_use
    in_use ENUM('true','false','yes','no') CHARACTER SET utf8
    COLLATE utf8_general_ci NOT NULL DEFAULT 'false';
ALTER TABLE consumer CHANGE is_active
    is_active ENUM('true','false','yes','no') CHARACTER SET utf8
    COLLATE utf8_general_ci NOT NULL DEFAULT 'false';
UPDATE meter set in_use = 'true' WHERE in_use = 'yes';
UPDATE meter set in_use = 'false' WHERE in_use = 'no';
UPDATE consumer set is_active = 'true' WHERE is_active = 'yes';
UPDATE consumer set is_active = 'false' WHERE is_active = 'no';
ALTER TABLE meter CHANGE in_use
    in_use ENUM('true','false') CHARACTER SET utf8
    COLLATE utf8_general_ci NOT NULL DEFAULT 'false';
ALTER TABLE consumer CHANGE is_active
    is_active ENUM('true','false') CHARACTER SET utf8
    COLLATE utf8_general_ci NOT NULL DEFAULT 'false';


