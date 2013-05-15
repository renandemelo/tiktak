require "./disciplina"
require "test/unit"

class Teste < Test::Unit::TestCase
	def teste_cria_disciplina
		d = Disciplina.new("Java",:P1 => 2, :P2 => 3,:P3 => 3)
		assert_equal("Java",d.nome)
		assert_equal(2,d.pesos[:P1])
		assert_equal(3,d.pesos[:P2])
		assert_equal(3,d.pesos[:P3])
	end

	def teste_adiciona_caso_1
		d = Disciplina.new("Automatos",:P1 => 6, :P2 => 4)
		d.tirei(:P1 => 8.0)
		assert_equal({:P2 => 0.5},d.quanto_preciso?)
	end

	def teste_adiciona_caso_2
		d = Disciplina.new("Calculo-1",:P1 => 1, :P2 => 2)
		d.tirei(:P1 => 3.0)
		assert_equal({:P2 =>  6.0},d.quanto_preciso?)
	end

	def teste_adiciona_caso_3
		d = Disciplina.new("Portugues",:P1 => 1, :P2 => 1, :P3 => 1)
		d.tirei(:P1 => 6.0)
		assert_equal({:P2 =>  4.5,:P3 => 4.5},d.quanto_preciso?)
	end

	def teste_adiciona_caso_3
		d = Disciplina.new("Portugues",:P1 => 1, :P2 => 1, :P3 => 2)
		d.tirei(:P1 => 6.0)
		d.tirei(:P2 => 2.0)
		assert_equal({:P3 => 6.0},d.quanto_preciso?)
	end
end



