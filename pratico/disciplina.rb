class Disciplina
	attr_reader :nome,:pesos

	def initialize(nome,pesos)
		@nome = nome
		@pesos = pesos
		@notas = {}

	end

	def tirei(nota)
		@notas = @notas.merge(nota)
	end

	def quanto_preciso?
		minimo = total_pesos() * 5
		total_ate_agora = 0
		pesos_ate_agora = 0
		@notas.each do |prova,nota|
			total_ate_agora += (nota * pesos[prova])
			pesos_ate_agora += pesos[prova]
		end
		total_preciso = minimo - total_ate_agora
		peso_restante = total_pesos() - pesos_ate_agora
		media = total_preciso/peso_restante
		
		provas = {}
		provas_restantes().each do |k,v|
			provas[k] = media
		end
		provas

	end

	def provas_restantes
		restantes = []		
		@pesos.each do |k,v|
			if(@notas[k] == nil)
				restantes << k
			end
		end
		restantes
	end

	def total_pesos
		total = 0
		pesos.each do |k,v| 
			total = total + v
		end
		total
	end


end
