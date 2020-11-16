package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.fai.reggistre.client.service.CategoryService;
import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Categoria;
import br.fai.reggistre.model.entities.Movimentacao;
import br.fai.reggistre.model.entities.PessoaFisica;

@Controller
public class HomeController
{
	@Autowired
	private MovementService movementService;
	@Autowired
	private CategoryService categoryService;
	int erroLogin;

	@GetMapping("/")
	public String getHomePage()
	{

		return "home";
	}

	@GetMapping("/dashboard")
	public ModelAndView getDashboardPage(HttpSession session) throws Exception
	{
		ModelAndView mv = null;
		PessoaFisica pf = new PessoaFisica();
		double valorTotalDespesas = 0;
		double valorTotalReceitas = 0;
		double valorTotalDasMesmasReceitas = 0;
		double valorTotalDasMesmasDespesas = 0;
		List<Movimentacao> despesasList = new ArrayList<>();
		List<Movimentacao> receitasList = new ArrayList<>();
		List<Double> valorTotalDasMesmasReceitasList = new ArrayList<>();
		List<Double> valorTotalDasMesmasDespesasList = new ArrayList<>();
		List<String> CoresVerdeGrafico = new ArrayList<>(); // rgb(100, 235, 52)
		List<String> CoresCinzaGrafico = new ArrayList<>(); // grey
		List<String> categoriasDespesaNomeList = new ArrayList<>();
		List<String> categoriasReceitaNomeList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		Map<Long, String> mapaDespesasCada = new HashMap<Long, String>();
		Map<Long, String> mapaReceitasCada = new HashMap<Long, String>();

		List<String> dataDeCadaMovimentacaoDespesa = new ArrayList<>();
		List<Double> valoresDeCadaMovimentacaoDespesa = new ArrayList<>();

		List<String> dataDeCadaMovimentacaoReceita = new ArrayList<>();
		List<Double> valoresDeCadaMovimentacaoReceita = new ArrayList<>();
		
		
		
		if (session.getAttribute("usuarioLogado") == null)
		{

			mv = new ModelAndView("redirect:/user/login");
			erroLogin = 1;
			return mv;
		} else
		{
			erroLogin = 0;
			mv = new ModelAndView("/dashboard");
			pf = (PessoaFisica) session.getAttribute("usuarioLogado");

			System.out.println(pf.getNomeCompleto());
			List<Movimentacao> movimentacaoList = movementService.readAll(); // Lé do banco todas as movimentações.

			for (Movimentacao movimentacao : movimentacaoList) // Pegar a lista com todas as movimentações
			{
				if (movimentacao.getPessoaFisicaId() == pf.getId()) // Se o PessoaFisica ID for igual ao ID da pessoa
																	// logada
				{
					Categoria categoria = new Categoria();
					categoria = categoryService.readById(movimentacao.getCategoriaId()); // Lé a categoria da
																							// movimentação atual

					if (categoria.getTipo().equals("receita")) // Se essa categoria lida, for igual a receita
					{
						receitasList.add(movimentacao); // Adiciona na lista de receitas essa movimentação
						mapaReceitasCada.put(categoria.getId(), movimentacao.getNome()); // Adiciona no mapa o "id"
																							// desta categoria e o
																							// "nome" dela
						if (!categoriasReceitaNomeList.contains(categoria.getNome()))// Testa se na lista de categorias
																						// do tipo receita já existe a
																						// categoria atual
						{
							categoriasReceitaNomeList.add(categoria.getNome()); // Caso não exista, adiciona ela na
																				// lista de categorias do tipo receita
						}
					} else if (categoria.getTipo().equals("despesa"))// Se essa categoria lida, for igual a receita
					{
						despesasList.add(movimentacao);// Adiciona na lista de despesas essa movimentação
						mapaDespesasCada.put(categoria.getId(), movimentacao.getNome());// Adiciona no mapa o "id" desta
																						// categoria e o "nome" dela
						if (!categoriasDespesaNomeList.contains(categoria.getNome()))// Testa se na lista de categorias
																						// do tipo despesas já existe a
																						// categoria atual
						{
							categoriasDespesaNomeList.add(categoria.getNome());// Caso não exista, adiciona ela na lista
																				// de categorias do tipo despesa
						}
					}
				}
			} // Fim-FOR
			
			
			/*Pegar o mês de hoje*/
			Date dataHoje = new Date(); 
			GregorianCalendar dataCalHoje = new GregorianCalendar();
			dataCalHoje.setTime(dataHoje);
			int mesHoje = dataCalHoje.get(Calendar.MONTH);
			mesHoje += 1;
			System.out.println("Mes de hoje: " + mesHoje);
			/*Pegar o mês de hoje*/
			String mesExtenso = "";
			
			for (Long key : mapaReceitasCada.keySet())
			{
				System.out.println(String.format("key Receita: %s", key));
				for (Movimentacao movimentacao : movimentacaoList)
				{
					if (movimentacao.getCategoriaId() == key && movimentacao.getPessoaFisicaId() == pf.getId())
					{
						System.out.println(movimentacao.getValor());

						
						
						Date data = new Date();
						GregorianCalendar dataCal = new GregorianCalendar();
						dataCal.setTime((movimentacao.getData()));
						int mes = dataCal.get((Calendar.MONTH));
						mes += 1;
						
						if(mes == mesHoje)
						{
							switch (mes)
							{
							case 1:
								mesExtenso = "Janeiro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 2:
								mesExtenso = "Fevereiro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 3:
								mesExtenso = "Março";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 4:
								mesExtenso = "Abril";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 5:
								mesExtenso = "Maio";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 6:
								mesExtenso = "Junho";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 7:
								mesExtenso = "Julho";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 8:
								mesExtenso = "Agosto";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 9:
								mesExtenso = "Setembro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 10:
								mesExtenso = "Outubro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 11:
								mesExtenso = "Novembro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;
							case 12:
								mesExtenso = "Dezembro";
								valoresDeCadaMovimentacaoReceita.add(movimentacao.getValor());
								dataDeCadaMovimentacaoReceita.add(movimentacao.getNome());
								break;

							default:
								mesExtenso = "";
								break;
							}				
						}
						

						
						
						System.out.println(movimentacao.getData());
						valorTotalDasMesmasReceitas += movimentacao.getValor();
					}
				}

				valorTotalDasMesmasReceitasList.add(valorTotalDasMesmasReceitas);
				valorTotalDasMesmasReceitas = 0;
			}

			for (Long key : mapaDespesasCada.keySet())
			{

				System.out.println(String.format("key Despesa: %s", key));
				for (Movimentacao movimentacao : movimentacaoList)
				{
					if (movimentacao.getCategoriaId() == key && movimentacao.getPessoaFisicaId() == pf.getId())
					{
						System.out.println(movimentacao.getValor());
											
						Date data = new Date();
						GregorianCalendar dataCal = new GregorianCalendar();
						dataCal.setTime((movimentacao.getData()));
						int mes = dataCal.get((Calendar.MONTH));
						mes += 1;
						
						if(mes == mesHoje)
						{
							switch (mes)
							{
							case 1:
								mesExtenso = "Janeiro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 2:
								mesExtenso = "Fevereiro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 3:
								mesExtenso = "Março";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 4:
								mesExtenso = "Abril";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 5:
								mesExtenso = "Maio";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 6:
								mesExtenso = "Junho";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 7:
								mesExtenso = "Julho";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 8:
								mesExtenso = "Agosto";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 9:
								mesExtenso = "Setembro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 10:
								mesExtenso = "Outubro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 11:
								mesExtenso = "Novembro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;
							case 12:
								mesExtenso = "Dezembro";
								valoresDeCadaMovimentacaoDespesa.add(movimentacao.getValor());
								dataDeCadaMovimentacaoDespesa.add(movimentacao.getNome());
								break;

							default:
								mesExtenso = "";
								break;
							}				
						}
								
						
						
						System.out.println(movimentacao.getData());
						
						valorTotalDasMesmasDespesas += movimentacao.getValor();

					}
				}
				valorTotalDasMesmasDespesasList.add(valorTotalDasMesmasDespesas);
				valorTotalDasMesmasDespesas = 0;
			}

			for (Movimentacao movimentacaoReceitas : receitasList)
			{
				valorTotalReceitas += movimentacaoReceitas.getValor();

			}

			for (Movimentacao movimentacaoDespesas : despesasList)
			{
				valorTotalDespesas += movimentacaoDespesas.getValor();
			}
		}

		for (int cinza = 1; cinza <= valorTotalDasMesmasDespesasList.size(); cinza++)
		{
			CoresCinzaGrafico.add("grey");
		}

		for (int verde = 1; verde <= valorTotalDasMesmasReceitasList.size(); verde++)
		{
			CoresVerdeGrafico.add("rgb(100, 235, 52)");
		}

		
		
	
		
		System.out.println("Valor de cada movimentacao Receita" + valoresDeCadaMovimentacaoReceita);
		System.out.println("Data de cada movimentacao Receita" + dataDeCadaMovimentacaoReceita);
	
		System.out.println("Valor de cada movimentacao Despesa" + valoresDeCadaMovimentacaoDespesa);
		System.out.println("Data de cada movimentacao Despesa" + dataDeCadaMovimentacaoDespesa);

		
		mv.addObject("valoresDeCadaMovimentacaoReceitaList", valoresDeCadaMovimentacaoReceita);
		mv.addObject("dataDeCadaMovimentacaoReceitaList", dataDeCadaMovimentacaoReceita);
		mv.addObject("valoresDeCadaMovimentacaoDespesaList", valoresDeCadaMovimentacaoDespesa);
		mv.addObject("dataDeCadaMovimentacaoDespesaList", dataDeCadaMovimentacaoDespesa);
		
		System.out.println("Lista das cores cinzas" + CoresCinzaGrafico);
		System.out.println("Lista das cores verdes" + CoresVerdeGrafico);
		System.out.println("Lista do Total das mesmas Despesas " + valorTotalDasMesmasDespesasList);
		System.out.println("Lista do total das mesmas Receitas " + valorTotalDasMesmasReceitasList);
		System.out.println("Total das mesmas Despesas " + valorTotalDasMesmasDespesas);
		System.out.println("Total das mesmas Receitas " + valorTotalDasMesmasReceitas);
		System.out.println("Total de Despesas " + valorTotalDespesas);
		System.out.println("Total de Receitas " + valorTotalReceitas);
		
		
		//Email -- Toda vez que passa aqui, manda o email.
		String textoEmail = "Seu saldo está negativo! Saldo atual: R$" + (valorTotalReceitas - valorTotalDespesas); 
		movementService.notificarViaEmail("gabrielicolisi@hotmail.com", textoEmail);
		//Email -- Toda vez que passa aqui, manda o email.
		
		
		System.out.println("Nomes das Despesas " + categoriasDespesaNomeList);
		System.out.println("Nomes das Receitas " + categoriasReceitaNomeList);
		System.out.println("Mapa Receitas ->" + mapaReceitasCada);
		System.out.println("Mapa Despesas ->" + mapaDespesasCada);

		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		mv.addObject("CoresCinzaGraficoList", CoresCinzaGrafico);
		mv.addObject("CoresVerdeGraficoList", CoresVerdeGrafico);

		mv.addObject("valorTotalDasMesmasDespesasList", valorTotalDasMesmasDespesasList);
		mv.addObject("valorTotalDasMesmasReceitasList", valorTotalDasMesmasReceitasList);

		mv.addObject("despesasTotalPessoa", valorTotalDespesas);
		mv.addObject("receitasTotalPessoa", valorTotalReceitas);

		mv.addObject("nomeDespesasList", categoriasDespesaNomeList);
		mv.addObject("nomeReceitasList", categoriasReceitaNomeList);
		return mv;
	}

	@GetMapping("/not-found")
	public String getNotFoundPage()
	{
		return "general/not-found";
	}

	@GetMapping("/notifications")
	public ModelAndView getNotificationsPage(HttpSession session)
	{
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		mv = new ModelAndView("notifications");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		return mv;
	}

}
