require "./calculadora"
require "test/unit"
 
class CalculadoraTest < Test::Unit::TestCase
 
  def test_soma
    cal = Calculadora.new
    assert_equal(30, cal.soma(18,12))
  end
 
end
