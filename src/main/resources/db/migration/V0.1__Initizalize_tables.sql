
CREATE TABLE quiz_entries (
    id serial primary key,
    quiz_id integer not null,
    name varchar(255),
    quiz_entry_type varchar(255) not null,
    question text not null,
    options varchar(255)[] not null default '{}',
    answer varchar(255) not null
);

CREATE TABLE quiz (
    id serial primary key,
    user_id integer not null,
    title varchar (255) not null,
    description text not null,
    is_public boolean default false,
    quiz_entry_ids integer[] not null default '{}'
);

CREATE TABLE user_credentials (
   id serial primary key,
   user_id integer not null,
   username varchar(25) unique not null,
   password varchar(50) not null,
   email varchar(255) unique not null
);

CREATE TABLE users (
    id serial primary key,
    user_credential_id integer references user_credentials (id),
    owned_quiz_ids integer[] not null default '{}',
    favourite_quiz_ids integer[] not null default '{}'
);

alter table user_credentials
    add constraint fk_user foreign key (user_id) references users (id);

alter table quiz
    add constraint fk_user foreign key (user_id) references users (id);

alter table quiz_entries
    add constraint fk_quiz FOREIGN KEY (quiz_id) REFERENCES quiz (id);