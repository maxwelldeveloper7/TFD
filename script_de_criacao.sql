--script de criação do banco de dados tfd
/*CREATE TABLE motoristas(
id serial primary key not null,
nomemotorista character varying(100),
telefonemoto character(11),
ativo boolean);

SELECT * FROM motoristas order by nomemotorista asc

CREATE TABLE cidades(
id serial primary key not null,
nomecidade character varying(100),
uf character(2));

CREATE TABLE especialidades(
id serial primary key not null,
nomeespe character varying);

CREATE TABLE procedimentos(
id serial primary key not null,
nomeproc character varying not null,
cdespeci integer not null,
CONSTRAINT fk_cdespeci FOREIGN KEY (cdespeci)
REFERENCES especialidades (id)
ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE VIEW vpproc as (

SELECT p.id, p.nomeproc, p.cdespeci, e.nomeespe

FROM procedimentos p INNER JOIN especialidades e on p.cdespeci = e.id);


select * from vpproc where id = 1 order by nomeproc asc*/

CREATE TABLE estabelecimentos (
id serial primary key not null,
nomeestabe character varying not null);

select * from estabelecimentos