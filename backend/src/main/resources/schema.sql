DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS person_role;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS subject;
DROP TABLE IF EXISTS subscription;

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    label VARCHAR(10)
);

CREATE TABLE subject (
    id SERIAL PRIMARY KEY,
    label VARCHAR(255)
);

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30),
    password VARCHAR(255),
    speciality VARCHAR(255),
    avatar VARCHAR(255),
    role_id BIGINT REFERENCES role(id),
    subject_id BIGINT REFERENCES subject(id)
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(10485760),
    teacher_id BIGINT REFERENCES person(id),
    subject_id BIGINT REFERENCES subject(id)
);

CREATE TABLE subscription (
    id SERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES course(id),
    person_id BIGINT REFERENCES person(id)
)


