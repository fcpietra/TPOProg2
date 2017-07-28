package Implementaciones;

import tda.ConjuntoStringTDA;

class Nodo{
	String valor;
	Nodo siguiente;
}

public class ConjuntoString implements ConjuntoStringTDA{

	Nodo primero;
	
	public void inicializar() {
		primero = null;
	}

	public boolean conjuntoVacio() {
		// TODO Auto-generated method stub
		return primero == null;
	}

	public void agregar(String valor) {
		if(!pertenece(valor)){
			Nodo nvo = new Nodo();
			nvo.valor = valor;
			nvo.siguiente =primero;
			primero = nvo;
		}
		
	}

	public String elegir() {
		// TODO Auto-generated method stub
		return primero.valor;
	}

	public void sacar(String valor) {
		if(primero != null){
			if(primero.valor == valor) //Elimino el primero nodo de la lista
				primero = primero.siguiente;
			
			else{
				
				Nodo aux=primero;
				
				while(aux.siguiente != null && !aux.siguiente.valor.equalsIgnoreCase(valor)) //no pregunto por el 1er nodo
					aux = aux.siguiente;		//debido a que ya hize el chequeo del primer valor con la clausula IF
					
				if(aux.siguiente != null)
					aux.siguiente = aux.siguiente.siguiente;
			
			}
		}
		
	}

	public boolean pertenece(String valor) {
		Nodo aux = primero;
		while(aux != null && !aux.valor.equalsIgnoreCase(valor)){
			aux = aux.siguiente;
		}
		
		if(aux == null)
			return false;
		else
			return true;
			
	}
	
}


