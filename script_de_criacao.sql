--script de criação do banco de dados tfd
CREATE TABLE motoristas(
id serial primary key not null,
nomemotorista character varying(100),
telefonemoto character(11),
ativo boolean);