-- liquibase formatted sql

-- changeSet sgolubev:1
CREATE INDEX student_name_index ON student (name);

-- changeSet sgolubev:2
CREATE INDEX faculty_name_and_color_index ON faculty (name, color);