\c reggistre;


INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('1','Gabrieli','Fernanda Gabrieli','gabrielicolisi@hotmail.com','gabriellycolly@hotmail.com','1234');

INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('2','Vinicius','Vinicius Castro','vinicius@hotmail.com','viniciuscardoso@hotmail.com','2222');

INSERT INTO public.pessoa_fisica(
	id, nome_usuario, nome_completo, email, email_alternativo, senha)
	VALUES ('3','Alisson','Alisson Henrique','alisson@hotmail.com','alissonhenrique@hotmail.com','3333');

INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('1','alimentacao','despesa','1','3');
	
INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('2','aluguel','despesa','1','1');
	
INSERT INTO public.categoria(
	id, nome, tipo, icone, pessoa_fisica_id)
	VALUES ('3','aplicacao financeira','receita','2','2');	
	
INSERT INTO public.movimentacao(
	id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id)
	VALUES ('1','pagar aluguel','aluguel da casa de Santa Rita','2020/06/19', 'fixa','800','1', '2');

INSERT INTO public.movimentacao(
	id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id)
	VALUES ('2','pagar restaurante','Marmita mensal do restaurante Meu Cantinho','2020/06/19', 'fixa','300','3', '1');

INSERT INTO public.movimentacao(
	id, nome, descricao, data, tipo_movimentacao, valor, pessoa_fisica_id, categoria_id)
	VALUES ('3','aplicacao financeira','rendimento da aplicacao no mes de junho','2020/06/19', 'variavel','10','2', '3');