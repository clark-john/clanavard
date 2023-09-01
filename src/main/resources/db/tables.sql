create table if not exists birthdays (
	userId text primary key unique not null,
	birthday datetime
);

create table if not exists config (
	guildId text primary key unique not null,
	birthdayChannel text
)
