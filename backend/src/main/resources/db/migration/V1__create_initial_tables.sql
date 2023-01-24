create table users (
    id int generated always as identity,
    user_credential_id int,
    primary key(id)
);

create table user_credential (
    id int generated always as identity,
    user_id int not null,
    username varchar(50) not null,
    password varchar(255) not null,
    email varchar(100) not null,
    primary key(id),
    constraint fk_uc_user foreign key(user_id) references users(id)
);

alter table users
    add constraint fk_u_user_credential
        foreign key(user_credential_id) references user_credential(id);

create table quiz (
    id int generated always as identity,
    user_id int not null,
    title varchar(255),
    description text,
    is_public boolean,
    primary key(id),
    constraint fk_q_user foreign key(user_id) references users(id)
);

create table quiz_entry (
    id int generated always as identity,
    quiz_id int not null,
    quiz_entry_type varchar(50),
    question text,
    options jsonb,
    answer text,
    primary key(id),
    constraint fk_qe_q foreign key(quiz_id) references quiz(id)
);