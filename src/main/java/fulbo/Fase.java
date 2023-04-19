package fulbo;

import java.util.ArrayList;

public class Fase {
	private String nombre;
	private ArrayList<Ronda> rondas;
	
	public Fase(String nombre) {
		this.nombre=nombre;
		this.rondas= new ArrayList<Ronda>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public ArrayList<Ronda> getRondas(){
		return rondas;
	}
	
	public void setRondas(ArrayList<Ronda> rondas) {
		this.rondas=rondas;
	}
	
	
}
