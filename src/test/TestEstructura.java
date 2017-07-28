package test;

import Implementaciones.Precipitaciones;
import tda.ColaPrioridadInvertidaTDA;
import tda.ConjuntoStringTDA;
import tda.PrecipitacionesTDA;

public class TestEstructura {

	/**
	 * Este main es para mediciones en float
	 * 
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PrecipitacionesTDA mediciones = new Precipitaciones();
		mediciones.inicializar();
		
		mediciones.agregar("La Florinda", 2016, 04, 3, 15);
		mediciones.agregar("La Florinda", 2016, 04, 5, 10);
		mediciones.agregar("La Florinda", 2016, 04, 9, 25);
		mediciones.agregar("La Florinda", 2016, 04, 12, 25);
		mediciones.agregar("La Florinda", 2016, 04, 13, 35);
		mediciones.agregar("La Florinda", 2016, 04, 22, 25);
		mediciones.agregar("La Florinda", 2016, 04, 23, 25);
		mediciones.agregar("La Florinda", 2016, 04, 25, 15);
		mediciones.agregar("La Florinda", 2017, 04, 4, 15);
		mediciones.agregar("La Florinda", 2017, 04, 6, 30);
		mediciones.agregar("La Florinda", 2017, 04, 10, 15);
		mediciones.agregar("La Florinda", 2017, 04, 11, 35);
		mediciones.agregar("La Florinda", 2017, 04, 12, 35);
		mediciones.agregar("La Florinda", 2017, 04, 21, 20);
		mediciones.agregar("La Florinda", 2017, 04, 23, 30);
		mediciones.agregar("La Florinda", 2017, 04, 27, 35);
		
		mediciones.eliminarMedicion("La Florinda", 2017, 04, 10);
		
		ColaPrioridadInvertidaTDA auxCPI = mediciones.comparativaMensual("la florinda", 04);
		while(!auxCPI.colaVacia()){
			System.out.println("Dia: " + auxCPI.prioridad() + " Precipitacion " + auxCPI.primero());
			auxCPI.desacolar();
		}
		
		mediciones.agregar("La Florera", 2016, 05, 3, 15);
		mediciones.agregar("La Jarronera", 2016, 06, 5, 10);
		mediciones.agregar("La jarronera", 2016, 06, 9, 25);
		mediciones.agregar("La Florera", 2016, 05, 12, 25);
		mediciones.agregar("La Florinda", 2016, 07, 13, 35);
		mediciones.agregar("la Jarronera", 2016, 03, 22, 25);
		mediciones.agregar("La Florinda", 2016, 06, 23, 25);
		mediciones.agregar("La Florera", 2016, 02, 25, 15);
		mediciones.agregar("La Florinda", 2017, 01, 4, 15);
		mediciones.agregar("la jarronera", 2017, 01, 6, 30);
		mediciones.agregar("La Florinda", 2017, 02, 10, 15);
		mediciones.agregar("La Florera", 2017, 04, 11, 35);
		mediciones.agregar("La Florinda", 2017, 03, 12, 35);
		mediciones.agregar("La Florera", 2017, 04, 21, 20);
		mediciones.agregar("La Florinda", 2017, 03, 23, 30);
		mediciones.agregar("La Jarronera", 2017, 03, 27, 35);
		
		ConjuntoStringTDA auxCS = mediciones.campos();
		String campo = null;
		while(!auxCS.conjuntoVacio()){
			campo = auxCS.elegir();
			System.out.println(campo);
			auxCS.sacar(campo);
		}
			
		mediciones.eliminar("la Jarronera");

		while(!auxCS.conjuntoVacio()){
			campo = auxCS.elegir();
			System.out.println(campo);
			auxCS.sacar(campo);
		}
		
		float promedioAnual = mediciones.promedioAnual("La Florinda", 2016);
		System.out.println(promedioAnual);
		float promedioMensual = mediciones.promedioMensual("La jarronera", 2016, 1);
		System.out.println(promedioMensual);
		
		mediciones.agregar("La Jarronera", 2016, 01, 10, 30);
		mediciones.agregar("La Jarronera", 2016, 01, 12, 20);
		mediciones.agregar("La Jarronera", 2016, 01, 15, 30);
		mediciones.agregar("La Jarronera", 2016, 01, 17, 20);
		
		promedioMensual = mediciones.promedioMensual("La jarronera", 2016, 1);
		System.out.println(promedioMensual);
		
		mediciones.eliminarMedicion("La Florinda", 2017, 01, 4);
		auxCPI = mediciones.comparativaMensual("la florinda", 04);
		while(!auxCPI.colaVacia()){
			System.out.println("Año: " + auxCPI.prioridad() + " Precipitacion " + auxCPI.primero());
			auxCPI.desacolar();
		}
		
		mediciones.agregar("El Jagüel", 2016, 03, 22, 25);
		mediciones.agregar("El Jagüel", 2016, 06, 23, 25);
		mediciones.agregar("el Jagüel", 2016, 02, 25, 15);
		mediciones.agregar("El Jagüel", 2017, 01, 4, 15);
		mediciones.agregar("El jagüel", 2017, 01, 6, 30);
		mediciones.agregar("El Jagüel", 2017, 02, 10, 15);
		
		ConjuntoStringTDA auxCS2 = mediciones.campos();
		String campo2 = null;
		while(!auxCS2.conjuntoVacio()){
			campo2 = auxCS2.elegir();
			System.out.println(campo2);
			auxCS2.sacar(campo2);
		}
		
		promedioMensual = mediciones.promedioMensual("La jarronera", 2016, 1);
		System.out.println(promedioMensual);
		
		mediciones.eliminarMedicion("La Florinda", 2017, 01, 4);
		auxCPI = mediciones.comparativaMensual("El Jagüel", 02);
		while(!auxCPI.colaVacia()){
			System.out.println("Año: " + auxCPI.prioridad() + " Precipitacion " + auxCPI.primero());
			auxCPI.desacolar();
		}
		String[] campos = mediciones.camposString();
		for(int i =0; i<campos.length;i++){
			System.out.println(campos[i]);
		}
	}

}
