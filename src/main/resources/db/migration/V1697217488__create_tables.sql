create table if not exists students
(
    id             bigserial primary key,
    first_name     varchar(82) not null,
    last_name      varchar(82) not null,
    national_code  varchar(10) not null unique,
    student_number varchar(12) unique,
    address        text
);