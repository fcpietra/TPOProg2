package Implementaciones;

import Auxiliares.Dia;
import tda.ConjuntoTDA;
import tda.DMMedicionesTDA;
import tda.DiccionarioSimpleTDA;

public class DMMedicionesDinamico implements DMMedicionesTDA {
	NodoMedicionesAnio primeroAnio;
	NodoMedicionesMes primeroMes;
	
	public void inicializar() {
		primeroAnio = null;
		primeroMes = null;
		
	}

	public void agregar(int anio, int mes, int dia, float medicion) {
		int i;
		if (primeroAnio == null){
			primeroAnio = new NodoMedicionesAnio();
			primeroAnio.anio = anio;
			primeroAnio.medicionMes = new NodoMedicionesMes();
			primeroAnio.medicionMes.mes = mes;
			primeroAnio.medicionMes.cantidadDiasMes = Dia.getInstancia().cantidadDia(mes, anio);
			primeroAnio.medicionMes.precipitacionPorDia = new float [primeroAnio.medicionMes.cantidadDiasMes];
			for (i = 0 ; i < primeroAnio.medicionMes.cantidadDiasMes; i++){
				primeroAnio.medicionMes.precipitacionPorDia [i] = 0;
			}
			primeroAnio.medicionMes.precipitacionPorDia[dia-1] = medicion;
		}else{
			NodoMedicionesAnio auxiliarAnio =  new NodoMedicionesAnio();
			auxiliarAnio.medicionMes = new NodoMedicionesMes();
			auxiliarAnio.medicionMes.precipitacionPorDia = new float [auxiliarAnio.medicionMes.cantidadDiasMes];
			auxiliarAnio = primeroAnio;
			
			while (auxiliarAnio.siguienteAnio != null && auxiliarAnio.anio != anio){
				auxiliarAnio = auxiliarAnio.siguienteAnio;
			}
			if (auxiliarAnio.anio == anio){
				NodoMedicionesMes auxiliarMes = auxiliarAnio.medicionMes;
				while(auxiliarMes.siguienteMes != null && auxiliarMes.mes != mes){
					auxiliarMes = auxiliarMes.siguienteMes;
				}
				if (auxiliarMes.mes == mes){
					auxiliarMes.precipitacionPorDia [dia-1] = medicion;
				}else{//SI EXISTE EL A�O PERO NO EL MES
					NodoMedicionesMes nvo = new NodoMedicionesMes();
					nvo.mes = mes;
					nvo.cantidadDiasMes = Dia.getInstancia().cantidadDia(mes, anio);
					nvo.precipitacionPorDia = new float [nvo.cantidadDiasMes];
					for (i = 0; i < nvo.cantidadDiasMes; i++){
						nvo.precipitacionPorDia [i] = 0;
					}
					nvo.precipitacionPorDia[dia-1]=medicion;
					nvo.siguienteMes = auxiliarAnio.medicionMes.siguienteMes;
					auxiliarAnio.medicionMes.siguienteMes = nvo; 
				}
			}else{//SI NO EXISTE EL A�O
				NodoMedicionesAnio nvo = new NodoMedicionesAnio();
				nvo.anio = anio;
				nvo.medicionMes = new NodoMedicionesMes();
				nvo.medicionMes.mes = mes;
				nvo.medicionMes.cantidadDiasMes = Dia.getInstancia().cantidadDia(mes, anio);
				nvo.medicionMes.precipitacionPorDia = new float [nvo.medicionMes.cantidadDiasMes];
				for (i = 0; i < nvo.medicionMes.cantidadDiasMes; i++){
					nvo.medicionMes.precipitacionPorDia [i] = 0;
				}
				nvo.medicionMes.precipitacionPorDia [dia-1] = medicion;
				nvo.siguienteAnio = auxiliarAnio.siguienteAnio;
				auxiliarAnio.siguienteAnio = nvo;
			}
		}
		
	}
	

	public void eliminarAnio(int anio) {
		NodoMedicionesAnio auxiliarAnio = new NodoMedicionesAnio();
		NodoMedicionesAnio anioAnterior = new NodoMedicionesAnio();
		auxiliarAnio = primeroAnio;
		anioAnterior = null;
		if (auxiliarAnio != null){
			while (auxiliarAnio.siguienteAnio != null && auxiliarAnio.anio != anio){
				anioAnterior = auxiliarAnio;
				auxiliarAnio = auxiliarAnio.siguienteAnio;
			}
			if (primeroAnio.anio == anio)
				primeroAnio = primeroAnio.siguienteAnio;
			if (auxiliarAnio.anio == anio){
				anioAnterior.siguienteAnio = auxiliarAnio.siguienteAnio;
			}else{
				System.out.println("No se encontro el anio");
			}
		}
		
	}

	public void eliminarMes(int anio, int mes) {
		NodoMedicionesAnio auxiliarAnio = new NodoMedicionesAnio();
		NodoMedicionesMes auxiliarMes = new NodoMedicionesMes();
		NodoMedicionesMes anteriorMes = new NodoMedicionesMes();
		auxiliarAnio.medicionMes = new NodoMedicionesMes();
		anteriorMes = null;
		auxiliarAnio = primeroAnio;
		if (auxiliarAnio != null){
			while (auxiliarAnio.siguienteAnio != null && auxiliarAnio.anio != anio){
				auxiliarAnio = auxiliarAnio.siguienteAnio;
			}
			if (auxiliarAnio.anio == anio){
				while(auxiliarAnio.medicionMes.siguienteMes != null && auxiliarAnio.medicionMes.mes != mes){
					auxiliarAnio.medicionMes = auxiliarAnio.medicionMes.siguienteMes;
				}
				if (auxiliarAnio.medicionMes.mes == mes){
					auxiliarMes = auxiliarAnio.medicionMes;
					while (auxiliarMes.siguienteMes != null && auxiliarMes.mes != mes){
						anteriorMes = auxiliarMes;
						auxiliarMes = auxiliarMes.siguienteMes;
					}
					if (auxiliarMes.mes == mes){
						if (anteriorMes == null){
							auxiliarAnio.medicionMes = auxiliarAnio.medicionMes.siguienteMes;
						}else{
							anteriorMes.siguienteMes = auxiliarMes.siguienteMes;
						}
					}
				}
			}
		}
		
	}

	public void eliminarDia(int anio, int mes, int dia) {
		NodoMedicionesAnio primero = primeroAnio;
		NodoMedicionesAnio anterior = null;
		while(primero!=null && primero.anio!=anio)
		{
			anterior = primero;
			primero = primero.siguienteAnio;
		}
		if(primero!=null)
		{
			NodoMedicionesMes primeroMes = primero.medicionMes;
			NodoMedicionesMes anteriorMes = null;
			while(primeroMes!=null && primeroMes.mes!=mes)
			{
				anteriorMes = primeroMes;
				primeroMes = primeroMes.siguienteMes;
			}
			if(primeroMes!=null)
			{
				primeroMes.precipitacionPorDia[dia-1] = 0;
				boolean c = false;
				for(int i = 0 ; i < primeroMes.cantidadDiasMes ; i++)
				{
					if(primeroMes.precipitacionPorDia[i]!=0)
					{
						c = true;
						break;
					}
				}
				if(c==false)
				{
					if(anteriorMes==null)
					{primero.medicionMes=null;}
					else
					{
						anteriorMes.siguienteMes=primeroMes.siguienteMes;
					}
				}
				if(primero.medicionMes==null)
				{
					anterior.siguienteAnio=primero.siguienteAnio;
				}
			}
		}
	}
	
	/*public void eliminarDia(int anio, int mes, int dia) {
		NodoMedicionesAnio auxiliarAnio = new NodoMedicionesAnio();
		auxiliarAnio.medicionMes = new NodoMedicionesMes();
		auxiliarAnio = primeroAnio;
		boolean bandera = true;
		
		if (auxiliarAnio != null){
			while (auxiliarAnio.siguienteAnio != null && auxiliarAnio.anio != anio)
				auxiliarAnio = auxiliarAnio.siguienteAnio;
			if (auxiliarAnio.anio == anio){
				while (auxiliarAnio.medicionMes != null && auxiliarAnio.medicionMes.mes != mes){
					auxiliarAnio.medicionMes = auxiliarAnio.medicionMes.siguienteMes;
				}
				if (auxiliarAnio.medicionMes.mes == mes){
					auxiliarAnio.medicionMes.precipitacionPorDia[dia]=0;
					for (int i = 0; i < auxiliarAnio.medicionMes.cantidadDiasMes; i++){
						if (auxiliarAnio.medicionMes.precipitacionPorDia[i] != 0){
							bandera = false;
							break;
						}
					}
					if (bandera == false)
						eliminarMes(anio, mes);
				}
			}
		}
	}*/
	
	
	
	public ConjuntoTDA anios() {
		ConjuntoTDA conjuntoAnio = new ConjuntoEstatico();
		NodoMedicionesAnio auxiliarAnio = new NodoMedicionesAnio();
		auxiliarAnio = primeroAnio;
		conjuntoAnio.inicializar();
		while (auxiliarAnio != null){
			conjuntoAnio.agregar(auxiliarAnio.anio);
			auxiliarAnio = auxiliarAnio.siguienteAnio;
		}
		return conjuntoAnio;
	}

	public ConjuntoTDA meses(int anio) {
		ConjuntoTDA conjuntoMes = new ConjuntoEstatico();
		NodoMedicionesAnio auxiliarAnio = new NodoMedicionesAnio();
		auxiliarAnio.medicionMes = new NodoMedicionesMes();
		auxiliarAnio = primeroAnio;
		conjuntoMes.inicializar();
		
		if (auxiliarAnio != null){
			while (auxiliarAnio != null && auxiliarAnio.anio != anio){
				auxiliarAnio = auxiliarAnio.siguienteAnio;
			}
			if (auxiliarAnio.anio == anio){
				NodoMedicionesMes auxiliarMes = new NodoMedicionesMes();
				auxiliarMes = auxiliarAnio.medicionMes;
				while (auxiliarMes != null){
					conjuntoMes.agregar(auxiliarMes.mes);
					auxiliarMes = auxiliarMes.siguienteMes;
				}
			}else{
				System.out.println ("El conjunto esta vacio");
			}
		}
		return conjuntoMes;
	}
	
	public DiccionarioSimpleTDA mediciones(int anio, int mes) {
		DiccionarioSimpleTDA d = new DiccionarioSimple();
		d.inicializar();
		NodoMedicionesAnio primero = primeroAnio;
		while(primero!=null && primero.anio!=anio)
		{
			primero = primero.siguienteAnio;
		}
		if(primero!=null)
		{
			NodoMedicionesMes primeroMes = primero.medicionMes;
			while(primeroMes!=null && primeroMes.mes!=mes)
			{
				primeroMes = primeroMes.siguienteMes;
			}
			if(primeroMes!=null)
			{
				for(int i = 0 ; i < primeroMes.cantidadDiasMes ; i++){
					d.agregar(i, primeroMes.precipitacionPorDia[i]);
				}
			}
		}
		return d;
	}
	

}
