package Auxiliares;

import Auxiliares.Dia;

public final class Dia {

	private static Dia instancia;
	
	private Dia() { }
	
	public static Dia getInstancia(){
		if(instancia == null)
			instancia = new Dia();
		return instancia;
	}
	
	public int cantidadDia(int mes, int anio){
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11)
			return 30;
		else
			if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
				return 31;
			else
				if(anio % 4 == 0 && anio % 100 != 0 || anio % 400 == 0)
					return 29;
				else
					return 28;						
	}
	
}
