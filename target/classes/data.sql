-- Popula a tabela CATEGORIA
INSERT INTO CATEGORIA (nome, descricao) VALUES ('Informática', 'Livros de informática');
INSERT INTO CATEGORIA (nome, descricao) VALUES ('Ficção científica', 'Livros de ficção científica');
INSERT INTO CATEGORIA (nome, descricao) VALUES ('Terror', 'Livros de terror');

-- Popula a tabela LIVRO
INSERT INTO LIVRO (titulo, autor, texto, edicao, categoria_id) VALUES ('Clean code', 'Robertin Martin', 'Lorem ipsum', 'PRIMEIRA', 1);
INSERT INTO LIVRO (titulo, autor, texto, edicao, categoria_id) VALUES ('Engenharia do software', 'Louis V. Gerstner', 'Lorem ipsum', 'TERCEIRA', 1);
INSERT INTO LIVRO (titulo, autor, texto, edicao, categoria_id) VALUES ('The war of the worlds', 'H. G. Wells', 'Lorem ipsum', 'PRIMEIRA', 2);
INSERT INTO LIVRO (titulo, autor, texto, edicao, categoria_id) VALUES ('The time machine', 'H. G. Wells', 'Lorem ipsum', 'SEGUNDA', 2);
INSERT INTO LIVRO (titulo, autor, texto, edicao, categoria_id) VALUES ('I, robot', 'Isaac Asimov', 'Lorem ipsum', 'TERCEIRA', 2);

