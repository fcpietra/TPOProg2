package Implementaciones;

import tda.ConjuntoTDA;
import tda.DiccionarioSimpleTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA{

	class Nodo{
		int clave;
		float valor;
		Nodo siguiente;
	}
	
	Nodo primero;
	
	/*public void agregar(int clave, int valor) {
		Nodo aux;
		Nodo nvo = new Nodo();
		if(primero == null){
			nvo.clave = clave;
			nvo.valor = valor;
			nvo.siguiente = primero;
			primero = nvo;
		}else {
			aux = primero;
			while( aux != null && aux.clave != clave){
				aux=aux.siguiente;				
			}
			if (aux == null){
				nvo.clave = clave;
				nvo.valor = valor;
				nvo.siguiente = aux;
				aux.siguiente = nvo;
			}else{
			
				aux.valor = valor;
			}
		}
		
		
	}*/

	public void agregar(int clave, float valor) {
		Nodo nvo = encontrarNodo(clave);
		if (nvo == null){
			nvo = new Nodo();
			nvo.clave = clave;
			nvo.siguiente = primero;
			primero = nvo;
		}
		nvo.valor = valor;
	}
	
	private Nodo encontrarNodo (int clave){
		Nodo aux = primero;
		
		while (aux != null && aux.clave != clave){
			aux = aux.siguiente;
		}
		return aux;
	}
	
	public void eliminar(int clave) {
		if (primero != null){
			if (primero.clave == clave){
				primero = primero.siguiente;
			} else {
				Nodo aux = primero;
				while (aux.siguiente != null && aux.siguiente.clave != clave){
					aux = aux.siguiente;
				}
				if (aux.siguiente != null){
					aux.siguiente = aux.siguiente.siguiente;
				}
			}
		}
		
	}

	public float recuperar(int clave) {
		if (primero != null){
			if (primero.clave == clave)
				return primero.valor;
			else{
				Nodo aux = primero;
				while (aux.siguiente != null && aux.siguiente.clave != clave)
					aux=aux.siguiente;
				if(aux.siguiente.clave==clave){
					return aux.siguiente.valor;
				}
			}
		}
		return -1;
	}

	public ConjuntoTDA claves() {
		ConjuntoTDA c = new ConjuntoEstatico();
		c.inicializar();
		
		Nodo aux = primero;
		while (aux != null){
			c.agregar(aux.clave);
			aux = aux.siguiente;
		}

		return c;
	}

	public void inicializar() {
		primero = null;
		
	}

}
