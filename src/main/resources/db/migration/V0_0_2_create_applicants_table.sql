create table applicants
(
    id         bigserial,
    username   VARCHAR(255) not null,
    email      VARCHAR(255) not null,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    ssn        VARCHAR(255) not null,
    city       VARCHAR(255) not null,
    street     VARCHAR(255) not null,
    number     VARCHAR(255) not null,
    zip        VARCHAR(255) not null,
    apt        VARCHAR(255) not null,
    constraint PK_applicant primary key (id),
    constraint UQ_applicant_username unique (username),
    constraint UQ_applicant_email unique (email),
    constraint UQ_ssn unique (ssn)
);