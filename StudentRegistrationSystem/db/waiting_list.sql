use projectdb;
create table waiting_list(
  wid int primary key auto_increment,
  cid int not null,
  roll int not null,
  joined_at timestamp default current_timestamp,
  unique (cid, roll),
  foreign key (cid) references course(cid) on delete cascade,
  foreign key (roll) references student(roll) on delete cascade
);