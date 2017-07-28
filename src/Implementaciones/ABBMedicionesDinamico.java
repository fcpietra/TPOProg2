package Implementaciones;

import tda.ABBMedicionesTDA;
import tda.DMMedicionesTDA;
import tda.DiccionarioSimpleTDA;

public class ABBMedicionesDinamico implements ABBMedicionesTDA {
	NodoCampo primero;

	public void inicializar() {
		primero = null;
		
	}

	public void agregar(String campo, int anio, int mes, int dia, float medicion) {
		DMMedicionesTDA DMMLluvias = new DMMedicionesDinamico();
		boolean flag = false;
		if (primero == null){
			NodoCampo nvo = new NodoCampo();
			nvo.ciudad = campo;
			DMMLluvias.inicializar();
			
			DMMLluvias.agregar(anio, mes, dia, medicion);
			nvo.mediciones = DMMLluvias;
			
			nvo.hijoI = new ABBMedicionesDinamico();
			nvo.hijoI.inicializar();
			nvo.hijoD = new ABBMedicionesDinamico();
			nvo.hijoD.inicializar();
			if (flag == false){
				primero = nvo;
				flag = true;
			}
			
			
		}
		else if (primero.ciudad.compareToIgnoreCase(campo) < 0){
				primero.hijoD.agregar(campo, anio, mes, dia, medicion);
			}
		else if (primero.ciudad.compareToIgnoreCase(campo) > 0){
				primero.hijoI.agregar(campo, anio, mes, dia, medicion);
			}
		else if (primero.ciudad.compareToIgnoreCase(campo) == 0){
			DMMLluvias = primero.mediciones;
			DMMLluvias.agregar(anio, mes, dia, medicion);
			primero.mediciones = DMMLluvias;
		}
		}
		

	public void eliminar(String campo) {
		if (primero != null){
			if (primero.ciudad.equalsIgnoreCase(campo) && primero.hijoI.arbolMedicionesVacio() && primero.hijoD.arbolMedicionesVacio()){
				primero = null;
		}else{
				if (primero.ciudad.equalsIgnoreCase(campo) && !primero.hijoI.arbolMedicionesVacio()){
					primero.ciudad = mayor(primero.hijoI);//modificar metodo para que devuelva string y dmm
					primero.hijoI.eliminar(primero.ciudad);//crear NodoAux con string y dmmm y referenciar
				}else{
					if (primero.ciudad.equalsIgnoreCase(campo) && primero.hijoI.arbolMedicionesVacio()){
						primero.ciudad = menor(primero.hijoD);
						primero.hijoD.eliminar(primero.ciudad);
					}
					else{
						if (primero.ciudad.compareToIgnoreCase(campo) > 0){
								primero.hijoI.eliminar(campo);
						}else{
							primero.hijoD.eliminar(campo);
						}
					}	
				}
			}
		}
	}

	public void eliminarMedicionDia(String campo, int anio, int mes, int dia) {
		NodoCiudades ciudades = new NodoCiudades();
		ciudades.ciudad = primero.ciudad;
		ciudades.mediciones = primero.mediciones;
		if(primero != null){
			if(primero.ciudad.equalsIgnoreCase(campo)){
				if (ciudades.mediciones.anios().pertenece(anio)){
					if (ciudades.mediciones.meses(anio).pertenece(mes)){
						ciudades.mediciones.eliminarDia(anio, mes, dia);
					}
				}
			}else if (primero.ciudad.compareToIgnoreCase(campo)>0){
				primero.hijoI.eliminarMedicionDia(campo, anio, mes, dia);
			}
			else if (primero.ciudad.compareToIgnoreCase(campo)<0){
				primero.hijoD.eliminarMedicionDia(campo, anio, mes, dia);
			}
		
		}
		
	}

	public String campo() {
		
		return primero.ciudad;
	}

	public DMMedicionesTDA mediciones() {
		return primero.mediciones;
	}

	public ABBMedicionesTDA hijoIzquierdo() {
		return primero.hijoI;
	}
	
	

	public ABBMedicionesTDA hijoDerecho() {
		return primero.hijoD;
	}

	public boolean arbolMedicionesVacio() {
		return primero == null;
	}
	
	private String menor(ABBMedicionesTDA ABB){
		if (ABB.hijoIzquierdo().arbolMedicionesVacio()){
			return ABB.campo();
		}else{
			return menor(ABB.hijoIzquierdo());
		}
	}
	
	private String mayor (ABBMedicionesTDA ABB){
		if (ABB.hijoDerecho().arbolMedicionesVacio()){
			return ABB.campo();
		}else{
			return mayor(ABB.hijoDerecho());
		}
	}


}
