require 'sinatra'
require './disciplina'

	configure do
      		enable :sessions
	end

	get '/novo-semestre' do
		session["disciplinas"] = {}
		"Novo semestre!!"
	end
	get '/nova-disciplina' do
		nome = params["nome"]

		pesos = {}
		params.each do |k,v|
			if(k != "nome")
				pesos[k] = v.to_i
			end
		end	


		disciplina = Disciplina.new(nome,pesos)
		disciplinas = session["disciplinas"]
		disciplinas[nome] = disciplina
		saida = "Voce esta cursando: <br/><ul>"
		disciplinas.each do |k,v|
			saida += "<li>#{k}</li>"
		end
		saida += "<ul>" 
	end

	get '/tirei' do
		nome = params["disciplina"]
		prova = params["prova"]
		nota = params["nota"].to_f

		disciplinas = session["disciplinas"]
		disciplinas[nome].tirei({prova => nota})

		"Anotei que voce tirou #{nota} na #{prova} de #{nome}!"
	end

	get '/preciso' do
		preciso = session["disciplinas"][params["disciplina"]].quanto_preciso?
		saida = "Para passar voce precisa tirar: <br/> <ul>"
		preciso.each do |k,v|
			saida+= "<li>#{k}: #{v}"
		end		
		saida
	end
