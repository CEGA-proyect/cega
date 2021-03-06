CREATE TABLE public.usuario(
	documento varchar(50) primary key, 
	correo varchar(50) UNIQUE, 
	nombre varchar(100) NOT NULL, 
	usern varchar(50) NOT NULL ,
	estado varchar(50) NOT NULL, 
	tipoIdentificacion varchar(3) NOT NULL,
	fechaRegistro date NOT NULL,
	contrasena varchar(50) NOT NULL
);

create sequence categoria_id_seq;
CREATE TABLE public.categoria (
	id int4 NOT NULL DEFAULT nextval('categoria_id_seq'),
	nombre varchar(60) NOT NULL,
	descripcion varchar(200) NOT NULL,
	fechadecreacion date NOT NULL,
	estado varchar(10) NOT NULL,
	valida varchar(20) not null,
	fechademodificacion date NULL,
	CONSTRAINT categoria_nombre_key UNIQUE (nombre),
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);
alter sequence categoria_id_seq owned by categoria.id;

create sequence necesidad_id_seq;
CREATE TABLE public.necesidad (
	id int4 NOT NULL DEFAULT nextval('necesidad_id_seq'),
	nombre varchar(60) NOT NULL,
	descripcion varchar(200) NOT NULL,
	fechadecreacion date NOT NULL,
	estado varchar(10) NOT NULL,
	fechademodificacion date NULL,
	urgencia varchar(7) NOT NULL,
	categoria_id int4 NOT NULL,
	usuario_id varchar(50) not null,
	CONSTRAINT necesidad_pk PRIMARY KEY (id),
	CONSTRAINT necesidad_categoria_id_fkey FOREIGN KEY (categoria_id) REFERENCES categoria(id),
	CONSTRAINT necesidad_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES usuario(documento)
);
alter sequence necesidad_id_seq owned by necesidad.id;


create sequence oferta_id_seq ;
CREATE TABLE public.oferta(
	id int4 NOT NULL DEFAULT nextval('necesidad_id_seq'),
	nombre varchar(60) NOT NULL,
	descripcion varchar(200) NOT NULL,
	fechadecreacion date NOT NULL,
	estado varchar(10) NOT NULL,
	fechademodificacion date NULL,
	categoria_id int4 NOT NULL,
	usuario_id varchar(50) NOT NULL,
	CONSTRAINT oferta_pk PRIMARY key (id),
	CONSTRAINT oferta_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES usuario(documento),
	constraint oferta_categoria_id_fkey FOREIGN KEY (categoria_id) references categoria(id)
); 
alter sequence oferta_id_seq owned by oferta.id; 


create sequence respuesta_id_seq ;
CREATE TABLE public.respuesta (
	id integer NOT NULL DEFAULT nextval('respuesta_id_seq'),
	nombre varchar(60) NOT NULL,
	comentario varchar(200) NOT NULL,
	fechadecreacion date NOT NULL,
	necesidad_id int4 ,
	oferta_id int4,
	usuario_id varchar(50) not null,
	CONSTRAINT respuesta_pk PRIMARY key (id),
	CONSTRAINT respuesta_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES usuario(documento),
	CONSTRAINT respuesta_necesidad_id_fkey FOREIGN KEY (necesidad_id) REFERENCES necesidad(id),
	CONSTRAINT respuesta_oferta_id_fkey FOREIGN KEY (oferta_id) REFERENCES oferta(id)
);
alter sequence respuesta_id_seq owned by respuesta.id;



drop table public.respuesta cascade; 
drop table public.oferta cascade ;
drop table public.necesidad cascade ; 
drop table public.categoria cascade; 
drop sequence categoria_id_seq;
drop sequence necesidad_id_seq;
drop sequence oferta_id_seq;
drop sequence respuesta_id_seq;
