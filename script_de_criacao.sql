--script de criação do banco de dados tfd
/*CREATE TABLE motoristas(
id serial primary key not null,
nomemotorista character varying(100),
telefonemoto character(11),
ativo boolean);

SELECT * FROM motoristas order by nomemotorista asc*/

CREATE TABLE cidades(
id serial primary key not null,
nomecidade character varying(100),
uf character(2));

