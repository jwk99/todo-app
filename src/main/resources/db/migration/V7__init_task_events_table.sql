DROP TABLE if exists tasks_events;
CREATE TABLE tasks_events(
    id int primary key auto_increment,
    task_id int,
    occurrence datetime,
    name varchar(30)
)