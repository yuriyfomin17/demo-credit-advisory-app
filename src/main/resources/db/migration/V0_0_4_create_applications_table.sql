create table applications
(
    id                  bigserial,
    amount_of_money_usd decimal(6, 2) not null,
    status              VARCHAR(255)  not null CHECK (status IN ('NEW', 'ASSIGNED', 'ON_HOLD', 'APPROVED', 'CANCELED',
                                                                 'DECLINED')),
    advisor_id          bigint        not null,
    applicant_id        bigint        not null,
    resolved_at         timestamp,
    assigned_at         timestamp,
    created_at          timestamp default now(),
    constraint PK_application_id primary key (id),

    constraint FK_advisor foreign key (advisor_id) references advisors (id),
    constraint FK_applicant foreign key (applicant_id) references applicants (id)
);