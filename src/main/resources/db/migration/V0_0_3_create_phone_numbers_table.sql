create table phone_numbers
(
    id           bigserial,
    phone_number VARCHAR(255),
    type         VARCHAR(255) check ( type in ('HOME', 'WORK', 'MOBILE') ),
    applicant_id bigint,
    constraint PK_phone_number primary key (id),
    constraint UQ_phone_number unique (phone_number),
    constraint FK_applicant foreign key (applicant_id) references applicants (id)
);