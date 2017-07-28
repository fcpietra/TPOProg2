package Implementaciones;

import tda.ColaTDA;

public class ColaDinamica implements ColaTDA{
	
	NodoCola primero;
		
	public void inicilizarCola() {
		primero =null;
		
	}

	public void acolar(int valor) {
		NodoCola ant, aux;
		NodoCola nvo = new NodoCola();
		nvo.valor = valor;
		aux = primero;
		ant = null;
		while(aux != null){
			ant = aux;
			aux = aux.siguiente;
		}
		if(primero == null){
			nvo.siguiente = primero;
			primero = nvo;
		}
		else{
			ant.siguiente = nvo;
			nvo.siguiente = aux;
		}
	}

	public void desacolar() {
		primero = primero.siguiente;
		
	}

	public int primero() {
		// TODO Auto-generated method stub
		return primero.valor;
	}

	public boolean colaVacia() {
		// TODO Auto-generated method stub
		return (primero == null);
	}

}
