public class CalculadoraTest{

	public static void main(String[] args){
		Calculadora cal = new Calculadora();
		if(cal.soma(4,5) != 9)
			throw new RuntimeException("Erro na soma!!");
	}
}
