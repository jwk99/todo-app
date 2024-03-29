CREATE TABLE projects(
                            id int primary key auto_increment,
                            description varchar(100) not null
);
alter table task_groups add column project_id int null;
alter table task_groups add foreign key (project_id) references projects (id);
CREATE TABLE project_steps(
                            id int primary key auto_increment,
                            description varchar(100) not null,
                            days_to_deadline int not null,
                            project_id int not null,
                            foreign key (project_id) references projects (id)
);