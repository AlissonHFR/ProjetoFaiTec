drop database reggistre;

create database reggistre with encoding 'utf8';

\c reggistre;

create table pessoa_fisica (
  id serial primary key,
  nome_usuario character varying(150) UNIQUE not null,
  nome_completo character varying(150) not null,
  email character varying(30) not null,
  email_alternativo character varying(30) not null,
  senha character varying(40) not null
  	
);

create table categoria (
  id serial primary key,
  nome character varying(50) UNIQUE not null,
  tipo character varying(20) not null,
  icone integer not null,
  pessoa_fisica_id integer not null
  references pessoa_fisica(id) on update cascade
  	
);

create table movimentacao (
  id serial primary key,
  nome character varying(50) not null,
  descricao character varying(150) not null,
  data date not null,
  tipo_movimentacao character varying(20) not null,
  valor float not null,
  pessoa_fisica_id integer not null
  references pessoa_fisica(id) on update cascade,
  categoria_id integer not null references categoria(id) on update cascade  
);

alter sequence categoria_id_seq restart with 10;
alter sequence pessoa_fisica_id_seq restart with 10;
alter sequence movimentacao_id_seq restart with 15;