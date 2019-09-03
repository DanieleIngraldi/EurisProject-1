CREATE TABLE IF NOT EXISTS module
(
    id integer AUTO_INCREMENT PRIMARY KEY,
    name nvarchar2(100) not null,
    surname nvarchar2(100) null,
    birthDate date not null,
    creationTimestamp timestamp not null,
    age INTEGER AS DATEDIFF(YEAR, birthDate, CURRENT_DATE()),
    type char
);