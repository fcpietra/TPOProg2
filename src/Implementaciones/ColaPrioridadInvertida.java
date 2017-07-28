package Implementaciones;

import tda.ColaPrioridadInvertidaTDA;

public class ColaPrioridadInvertida implements ColaPrioridadInvertidaTDA {

	class Nodo{
		int valor;
		int prioridad;
		Nodo siguiente;
	}
	
	Nodo primero;
	
	public void inicializar() {
		primero = null;
	}

	public void acolar(int valor, int prioridad) {
		Nodo nvo = new Nodo();
		nvo.valor = valor;
		nvo.prioridad = prioridad;
		if (primero == null || prioridad < primero.prioridad){
			nvo.siguiente = primero;
			primero = nvo;
		}else{
			Nodo aux = primero;
			while (aux.siguiente != null && aux.siguiente.prioridad <= prioridad){
				aux = aux.siguiente;
			}
			nvo.siguiente = aux.siguiente;
			aux.siguiente = nvo;
		}
		
	}

	public void desacolar() {
		primero = primero.siguiente;
	}

	public int primero() {
		return primero.valor;
	}

	public int prioridad() {
		return primero.prioridad;
	}

	public boolean colaVacia() {
		return primero == null;
	}

}
