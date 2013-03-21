require "./calculadora"
require "test/unit"
 
class CalculadoraTest < Test::Unit::TestCase

  def test_soma_18_12_eh_30
    cal = Calculadora.new
    assert_equal(30, cal.soma(18,12))
  end
 
  def test_soma_20_e_0_eh_20
    cal = Calculadora.new
    assert_equal(20, cal.soma(0,20))
  end


  def test_multip_30_e_2_eh_60
    cal = Calculadora.new
    assert_equal(60, cal.multiplica(2,30))
  end

  def test_multip_0_e_0_eh_0
    cal = Calculadora.new
    assert_equal(0, cal.multiplica(0,0))
  end


  def test_multip_2_e_7_eh_14
    cal = Calculadora.new
    assert_equal(14, cal.multiplica(2,7))
  end


end
