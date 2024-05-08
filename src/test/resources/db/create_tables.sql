drop database if exists medical_center_test;
create database medical_center_test;
use medical_center_test;

create table doctors
(
    doctor_id      bigint auto_increment
        primary key,
    name           varchar(255) null,
    specialization varchar(255) null,
    username       varchar(255) unique not null
);

create table reports
(
    report_id   bigint auto_increment
        primary key,
    description varchar(255) null
);

create table appointmets
(
    appointment_id bigint auto_increment
        primary key,
    end_time       datetime(6)                               null,
    note           varchar(255)                              null,
    patient_name   varchar(255)                              null,
    start_time     datetime(6)                               null,
    status         enum ('COMPLETED', 'CANCELED', 'CREATED') null,
    doctor         bigint                                    null,
    report         bigint   unique                           null,
    constraint doctor_appointment
        foreign key (doctor) references doctors (doctor_id),
    constraint report_appointment
        foreign key (report) references reports (report_id)
);

create table roles
(
    role varchar(255) not null
        primary key
);

create table users
(
    user_id  bigint auto_increment
        primary key,
    active   bit          null,
    password varchar(255) null,
    username varchar(255) unique null,
    role     varchar(255) null,

    constraint user_role
        foreign key (role) references roles (role)
);

