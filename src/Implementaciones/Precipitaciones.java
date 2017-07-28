package Implementaciones;

import Auxiliares.Dia;
import tda.ABBMedicionesTDA;
import tda.ColaPrioridadInvertidaTDA;
import tda.ConjuntoStringTDA;
import tda.ConjuntoTDA;
import tda.DMMedicionesTDA;
import tda.DiccionarioSimpleTDA;
import tda.PrecipitacionesTDA;

public class Precipitaciones implements PrecipitacionesTDA{

	ABBMedicionesDinamico abbmd = new ABBMedicionesDinamico();	
	
	public void inicializar() {
		
		abbmd.inicializar();
		

	}

	public void agregar(String campo, int anio, int mes, int dia, float precipitacion) {
		
		abbmd.agregar(campo, anio, mes, dia, precipitacion);
	}

	public void eliminar(String campo) {
		abbmd.eliminar(campo);
		
	}

	public void eliminarMedicion(String campo, int anio, int mes, int dia) {
		abbmd.eliminarMedicionDia(campo, anio, mes, dia);
		
	}

	public int medicionDeUnDia(String campo, int anio, int mes, int dia) {
		DiccionarioSimpleTDA d = new DiccionarioSimple();
		float medicion=0;
		if (abbmd!=null){
			if(abbmd.campo().equalsIgnoreCase(campo)){
				if(abbmd.mediciones().anios().pertenece(anio)){
					if(abbmd.mediciones().meses(anio).pertenece(mes)){
						d=abbmd.mediciones().mediciones(anio, mes);
						medicion=d.recuperar(dia-1);
					}
				}
			}
		}
		return (int) medicion;
	}

	public ConjuntoStringTDA campos() {
		ConjuntoStringTDA cs = new ConjuntoString();
		cs.inicializar();
		recorroArbol(abbmd, cs);	
		return cs;
	}

	private void recorroArbol(ABBMedicionesTDA abbmd, ConjuntoStringTDA cs) {
		// TODO Auto-generated method stub
		if(!abbmd.arbolMedicionesVacio()){
			cs.agregar(abbmd.campo());
			recorroArbol(abbmd.hijoIzquierdo(),cs);
			recorroArbol(abbmd.hijoDerecho(), cs);
		}
		
	}
	
	

	public ColaPrioridadInvertidaTDA mediciones(String campo, int anio, int mes) {
		ColaPrioridadInvertidaTDA c = new ColaPrioridadInvertida();
		c.inicializar();
		ABBMedicionesTDA arbolAux = abbmd;
		while(!arbolAux.campo().equalsIgnoreCase(campo)){
			if(arbolAux.campo().compareToIgnoreCase(campo)>0)
			arbolAux = arbolAux.hijoDerecho();
			if(arbolAux.campo().compareToIgnoreCase(campo)<0)
				arbolAux = arbolAux.hijoIzquierdo();
		}
		int cant = Dia.getInstancia().cantidadDia(mes, anio);
		for(int i = 0; i<cant; i++){
			int precipitacion = (int) arbolAux.mediciones().mediciones(anio, mes).recuperar(i);
			c.acolar(precipitacion, i);
		}
		return c;
	}
	

	
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) {
		ColaPrioridadInvertidaTDA cola= new ColaPrioridadInvertida();
		cola.inicializar();
		ABBMedicionesTDA aux=buscarCampo(abbmd, campos);
		if(aux!=null){
			ConjuntoTDA anios=aux.mediciones().anios();
			if(!anios.conjuntoVacio()){
				int a;
				int suma;
				while(!anios.conjuntoVacio()){
					a=anios.elegir();
					suma=(int) sumaPreciMensual(mes, a, campos);
					cola.acolar(suma, a);
					anios.sacar(a);
				}
			}
		}
		return cola;
	}
	
	/*
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) {
		ColaPrioridadInvertidaTDA cpi = new ColaPrioridadInvertidaEstatica();
		cpi.inicializar();
		int e;
		if(!abbmd.arbolMedicionesVacio())
		{
			if(abbmd.campo().equalsIgnoreCase(campos))
			{
				DMMedicionesTDA dicm = abbmd.mediciones();
				ConjuntoTDA c = dicm.anios(); 
				while(!c.conjuntoVacio())
				{
					e = c.elegir();
					c.sacar(e);
					cpi.acolar(sumaMes(dicm.mediciones(e, mes)), e);
				}
				return cpi;
			}
			else
			{
				if(!abbmd.hijoIzquierdo().arbolMedicionesVacio() && abbmd.campo().compareToIgnoreCase(campos)>0)
				{
					cpi = comparativaMensualAux(abbmd.hijoIzquierdo(),campos,mes);
				}
				else if(!abbmd.hijoDerecho().arbolMedicionesVacio() && abbmd.campo().compareToIgnoreCase(campos)<0)
				{
					cpi = comparativaMensualAux(abbmd.hijoDerecho(),campos,mes);
				}
			}
		}
	return cpi;
	}

	private ColaPrioridadInvertidaTDA comparativaMensualAux(ABBMedicionesTDA a, String campos, int mes) {//a como siempre
		ColaPrioridadInvertidaTDA cpi = new ColaPrioridadInvertidaEstatica();
		cpi.inicializar();
		int e;
		if(!a.arbolMedicionesVacio())
		{
			if(a.campo().equalsIgnoreCase(campos))
			{
				DMMedicionesTDA dicm = a.mediciones();
				ConjuntoTDA c = dicm.anios(); 
				while(!c.conjuntoVacio())
				{
					e = c.elegir();
					c.sacar(e);
					cpi.acolar(sumaMes(dicm.mediciones(e, mes)), e);
				}
				return cpi;
			}
			else
			{
				if(!a.hijoIzquierdo().arbolMedicionesVacio() && a.campo().compareToIgnoreCase(campos)>0)
				{
					cpi = comparativaMensualAux(a.hijoIzquierdo(),campos,mes);
				}
				else if(!a.hijoDerecho().arbolMedicionesVacio() && a.campo().compareToIgnoreCase(campos)<0)
				{
					cpi = comparativaMensualAux(a.hijoDerecho(),campos,mes);
				}
			}
		}
	return cpi;
	}
	*/
	private float sumaPreciMensual (int mes,int anio, String campo){
		float suma = 0;
		ABBMedicionesTDA aux = buscarCampo(abbmd, campo);
		int precipitacion;
		if(aux != null){
			ConjuntoTDA c = aux.mediciones().mediciones(anio, mes).claves();
			while(!c.conjuntoVacio()){
				precipitacion = c.elegir();
				suma = suma + aux.mediciones().mediciones(anio, mes).recuperar(precipitacion);
				c.sacar(precipitacion);
				
			}
		}
		return suma;
	}
	/*
	private int sumaMes(DiccionarioSimpleTDA m)//Este diccionario contiene los datos de un mes, siendo las claves los dias y los valores las precipitaciones de cada dia
	{
		int r=0, e;
		ConjuntoTDA c = m.claves();
		while(!c.conjuntoVacio())
		{
			e = c.elegir();
			c.sacar(e);
			r = (int) (r + m.recuperar(e));
		}
		return r;
	}
	*/
	
	public float promedioAnual(String campo, int anio) {
		float promedio = 0;
		ConjuntoTDA conjMes=new ConjuntoEstatico();
		ABBMedicionesTDA aux = buscarCampo(abbmd, campo);
		int mes=0;
		conjMes=aux.mediciones().meses(anio);
		if(aux != null){
			if(aux.mediciones().anios().pertenece(anio)){
				while(!conjMes.conjuntoVacio()){
					mes=conjMes.elegir();
					conjMes.sacar(mes);
						promedio=promedioMensual(campo,anio,mes);
					}
			}
		}
		
		if ((anio%4==0&&anio%100!=0)||anio%400==0){
			return promedio/366;
		}else 
			return promedio/365;
	}

	public float promedioMensual(String campo, int anio, int mes) {
		float promedio = 0;
		ABBMedicionesTDA aux = buscarCampo(abbmd, campo);
		int precipitacion;
		int cantidad = Dia.getInstancia().cantidadDia(mes, anio);
		if(aux != null){
			ConjuntoTDA c = aux.mediciones().mediciones(anio, mes).claves();
			while(!c.conjuntoVacio()){
				precipitacion = c.elegir();
				promedio = promedio + aux.mediciones().mediciones(anio, mes).recuperar(precipitacion);
				c.sacar(precipitacion);
		
			}
		}
		if(cantidad != 0)
			return promedio/cantidad;
		else
			return promedio;
	}
	
	private ABBMedicionesTDA buscarCampo(ABBMedicionesTDA arbol, String campo){
		if (!arbol.arbolMedicionesVacio()){
			if(arbol.campo().equalsIgnoreCase(campo))
				return arbol;
			else if(arbol.campo().compareToIgnoreCase(campo)>0){
				return buscarCampo(arbol.hijoIzquierdo(), campo);
			}
			else{
				return buscarCampo(arbol.hijoDerecho(), campo);
			}
		}
		else
			return null;
	}

	public String[] camposString() {
		String[] vecString = new String[10];
		 Integer i=0;
		recorroArbolAlf(abbmd,vecString,i);
				
			// TODO Auto-generated method stub
		return vecString;
	}
	
	public void recorroArbolAlf(ABBMedicionesTDA abbmd, String[] vecString, Integer i){
		if(!abbmd.arbolMedicionesVacio()){
			recorroArbolAlf(abbmd.hijoIzquierdo(), vecString, i);
				vecString[i]=abbmd.campo();
				i++;
			recorroArbolAlf(abbmd.hijoDerecho(), vecString, i);
			
		}
	}

	
	
	

}
