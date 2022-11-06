create table if not exists task (
    id              int                   not null primary key auto_increment,
    name            varchar(30)           not null,
    expiration_date date                  not null,
    created_at    datetime              not null,
    updated_at     datetime              not null
);