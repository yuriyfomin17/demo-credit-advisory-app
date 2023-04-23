create table advisors
(
    id         bigserial,
    username   VARCHAR(255) not null,
    email      VARCHAR(255) not null,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    role       VARCHAR(255) not null CHECK (role IN ('ASSOCIATE', 'PARTNER', 'SENIOR')),
    constraint PK_advisor primary key (id),
    constraint UQ_advisor_username unique (username),
    constraint UQ_advisor_email unique (email)
);
