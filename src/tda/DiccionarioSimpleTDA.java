package tda;

public interface DiccionarioSimpleTDA {
		/** incializado*/
		public void agregar(int clave, float precipitacionPorDia);
		/** incializado*/
		public void eliminar(int clave);
		/** incializado y no vacio*/
		public float  recuperar(int clave);
		/** incializado y no vacio*/
		public ConjuntoTDA claves();
		/** no tiene*/
		public void inicializar();
		
}
