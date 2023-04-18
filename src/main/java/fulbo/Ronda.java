package fulbo;

import java.util.ArrayList;

public class Ronda {
	private String nro;
	private ArrayList<Partido> partidos;

	public Ronda(String nro) {
		this.nro = nro;
		this.partidos = new ArrayList<>();
	}

	public Ronda(String nro, ArrayList<Partido> partidos) {
		super();
		this.nro = nro;
		this.partidos = partidos;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	public void agregarPartido(Partido part) {
		partidos.add(part);
	}
}
