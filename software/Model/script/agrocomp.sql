CREATE TABLE usuario(
	id BIGSERIAL NOT NULL UNIQUE,
	nome VARCHAR(30) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(500) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE administrador(
	usuario_fk BIGINT NOT NULL,
	PRIMARY KEY(usuario_fk)
);
CREATE TABLE cliente(
	usuario_fk BIGINT NOT NULL,
	cidade VARCHAR(100),
	estado VARCHAR(25),
	telefone VARCHAR(20),
	PRIMARY KEY(usuario_fk)
);
CREATE TABLE noticia(
	administrador_fk BIGINT NOT NULL,
	id BIGSERIAL NOT NULL UNIQUE,
	data_hora TIMESTAMP NOT NULL,
	descricao VARCHAR(500) NOT NULL,
	PRIMARY KEY(id)
);

----------------------------------------------------------------------------------------------------------

CREATE TABLE anuncio(
	cliente_fk BIGINT NOT NULL,
	categoria_fk BIGINT NOT NULL,
	id BIGSERIAL UNIQUE NOT NULL,
    nome varchar(100) NOT NULL,
	foto bytea,
	qtde INT NOT NULL,
	unidade_medida varchar(50),
	preco decimal NOT NULL,
	descricao VARCHAR(500),
	PRIMARY KEY(id)
	
);
----------------------------------------------------------------------------------------------------------
CREATE TABLE categoria(
	id BIGSERIAL UNIQUE NOT NULL,
	nome VARCHAR(30) NOT NULL UNIQUE,
	PRIMARY KEY(id)
);
----------------------------------------------------------------------------------------------------------

CREATE TABLE reporte(
	anuncio_fk BIGINT NOT NULL,
	id BIGSERIAL UNIQUE NOT NULL,
	descricao VARCHAR(500),
	data_hora TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
);



CREATE TABLE discussao
(
  id bigserial NOT NULL,
  titulo varchar(200) NOT NULL,
  pergunta varchar(500) NOT NULL,
  datahora timestamp  NOT NULL,
  cliente_fk bigint NOT NULL,
    PRIMARY KEY (id)
 
);

CREATE TABLE mensagem
(
  id bigserial not null,
  resposta text NOT NULL,
  datahora timestamp  NOT NULL,
  cliente_fk bigint NOT NULL,
  discussao_fk bigint not null,
  PRIMARY KEY (id)
  
);
CREATE TABLE mensagem_anuncio
(
  id bigserial not null,
  usuario_fk bigint NOT NULL,
  anuncio_fk bigint NOT NULL,
  mensagem text NOT NULL,
  data_hora timestamp  NOT NULL,
  PRIMARY KEY (id)
);


-----------------------------------------Adicionando Restrições------------------------------------
ALTER TABLE administrador ADD CONSTRAINT administrador_cliente_fk FOREIGN KEY(usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE cliente ADD CONSTRAINT cliente_usuario_fk FOREIGN KEY(usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE anuncio ADD CONSTRAINT anuncio_usuario_fk FOREIGN KEY(cliente_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE anuncio ADD CONSTRAINT anuncio_categoria_fk FOREIGN KEY(categoria_fk) REFERENCES categoria(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE reporte ADD CONSTRAINT report_anuncio_fk FOREIGN KEY(anuncio_fk) REFERENCES anuncio(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE discussao ADD CONSTRAINT discussao_cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(usuario_fk) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE mensagem ADD CONSTRAINT mensagem_cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(usuario_fk) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE mensagem ADD CONSTRAINT mensagem_discussao_fk FOREIGN KEY(discussao_fk) REFERENCES discussao(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE mensagem_anuncio ADD CONSTRAINT mensagem_anuncio_cliente_fk FOREIGN KEY(usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE mensagem_anuncio ADD CONSTRAINT mensagem_anuncio_anuncio_fk FOREIGN KEY(anuncio_fk) REFERENCES anuncio(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE noticia ADD CONSTRAINT noticia_administrador_fk FOREIGN KEY(administrador_fk) REFERENCES administrador(usuario_fk) ON UPDATE CASCADE ON DELETE CASCADE;



