package Implementaciones;

import tda.ConjuntoTDA;

public class ConjuntoEstatico implements ConjuntoTDA{

	int v[];
	int cantidad;
	
	public void inicializar() {
		 v= new int[100];
		cantidad = 0;
	}

	public boolean conjuntoVacio() {
		// TODO Auto-generated method stub
		return (cantidad == 0);
	}

	public void agregar(int valor) {
		if(!pertenece(valor)){
			v[cantidad]=valor;
			cantidad++;
		}
		
	}
	
	public int elegir() {
		
		return v[cantidad-1];
	}

	public void sacar(int valor) {
		int i;
		for(i=0; i<cantidad;i++){
			if(v[i] == valor){
				v[i] = v[cantidad-1];  //copio en la pocision i el valor de la posc. cant -1
				cantidad--;
			}
		}
	}

	public boolean pertenece(int valor) {
		int i;
		for(i=0; i<cantidad; i++){
			if(v[i] == valor)
				return true;
		}
		return false;
	}

}
