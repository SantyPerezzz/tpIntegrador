package fulbo;

import java.util.ArrayList;

public class Participante {
	private String nombre;
	private ArrayList<Pronostico> pronosticos;
	
	public Participante(String nombre) {
		this.nombre=nombre;
		this.pronosticos=new ArrayList<Pronostico>();
	}
	
	public Participante(String nombre, ArrayList<Pronostico> pronosticos) {
		this.nombre = nombre;
		this.pronosticos = pronosticos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Pronostico> getPronosticos() {
		return pronosticos;
	}
	public void setPronosticos(ArrayList<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}
	
	public void agregarPronostico(Pronostico pron) {
		pronosticos.add(pron);
	}
	
	public int puntosTotales() {
		int puntos=0;
		for(Pronostico p:pronosticos) {
			puntos+=p.puntos();
		}
		
		return puntos;
	}
	
	public ArrayList<Pronostico> pronosticosAcertados(){
		ArrayList<Pronostico> aciertos= new ArrayList<>();
		for(Pronostico p:pronosticos) {
			if(p.puntos()>0) {
				aciertos.add(p);
			}
		}
		
		return aciertos;
	}
}
