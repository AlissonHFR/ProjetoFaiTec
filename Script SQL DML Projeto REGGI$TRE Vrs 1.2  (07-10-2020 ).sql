\c reggistre;

--BEGIN: Populando Pessoa_fisica--
INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('1','Gabrieli','Fernanda Gabrieli','gabrielicolisi@hotmail.com','gabriellycolly@hotmail.com',md5('admin'));

INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('2','Vinicius','Vinicius Castro','vinicius@hotmail.com','viniciuscardoso@hotmail.com',md5('admin'));

INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('3','Alisson','Alisson Henrique','alisson@hotmail.com','alissonhenrique@hotmail.com',md5('admin'));
--END: Populando Pessoa_fisica--


--BEGIN: Populando Categoria--
INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('1','alimentacao','despesa','1','3');
	
INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('2','aluguel','despesa','1','1');
	
INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('3','aplicacao financeira','receita','2','2');	
--END: Populando categoria--



--BEGIN: Populando movimentacao--

--Despesas--
INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('1','pagar aluguel','aluguel da casa de Santa Rita','2020/06/19', 'fixa','800','1', '1');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('2','pagar aluguel','aluguel da casa de Santa Rita','2020/06/19', 'fixa','100','1', '1');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('3','pagar aluguel','aluguel da casa de Santa Rita','2020/06/19', 'fixa','800','1', '1');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES
('4','pagar restaurante','Marmita mensal do restaurante Meu Cantinho','2020/06/19', 'fixa','300','3', '1');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('5','pagar aluguel','aluguel da casa de Santa Rita','2020/06/19', 'fixa','800','1', '2');
--Despesas--

--Receitas--

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('6','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','1000','1', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('7','aplicacao financeira','rendimento da aplicacao  no mes de junho','2020/06/19', 'variavel','700','1', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('8','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','1200','1', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('9','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','100','1', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('10','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('11','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('12','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('13','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');

INSERT INTO public.movimentacao(id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id) VALUES 
('14','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');	
--Receitas--

--END: Populando movimento--


