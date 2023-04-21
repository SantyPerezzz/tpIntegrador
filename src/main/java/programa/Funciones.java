package programa;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

import fulbo.*;
import fulbo.Partido.Resultado;

public class Funciones {
	
	public static Partido buscarPartido(ArrayList<Partido> partidos,Equipo team1, Equipo team2) {
		Partido aux= new Partido();
		for(Partido part: partidos) {
			if((part.getEquipo1()==team1 || part.getEquipo2()==team1)&&(part.getEquipo1()==team2 || part.getEquipo2()==team2)) {
				aux=part;
			}
		}
		
		return aux;
	}
	
	public static int posEquipo(ArrayList<Equipo> equipos,String nombre) {
		int pos=-1;
		for(Equipo eq:equipos) {
			if(eq.getNombre().equals(nombre)) {
				pos=equipos.indexOf(eq);
			}
		}
		
		return pos;
	}
	
	public static int posParticipante(ArrayList<Participante> participantes,String nombre) {
		int pos=-1;
		for(Participante part:participantes) {
			if(part.getNombre().equals(nombre)) {
				pos=participantes.indexOf(part);
			}
		}
		
		return pos;
	}
	
	public static int posRonda(ArrayList<Ronda> rondas,String nro) {
		int pos=-1;
		for(Ronda r:rondas) {
			if(r.getNro().equals(nro)) {
				pos=rondas.indexOf(r);
			}
		}
		
		return pos;
	}
	
	public static Resultado resEquipo1(String linea[]) {
		Resultado res=Resultado.perdio;
		if(linea[2].equals("X")) {
			res= Resultado.gano;
		}else if(linea[3].equals("X")) {
			res= Resultado.empato;
		}
		
		return res;
	}
	
	public static String stringVs(ArrayList<Partido> partidos) {
		String partidosString="";
		for(Partido p:partidos) {
			partidosString+=p.getEquipo1().getNombre()+" vs "+p.getEquipo2().getNombre()+"\n";
		}
		return partidosString;
	}
	
	public static boolean verificarLineaResultado(String linea) {
		boolean b=false;
		String[] lineaSpliteada= linea.split(",");
		if(lineaSpliteada.length==5) {
			if(Integer.parseInt(lineaSpliteada[2])>=0) {
				if(Integer.parseInt(lineaSpliteada[3])>=0) {
					b=true;
				}
			}
		}
		
		return b;
	}
	
	public static void leerArchivoResultados(Path resultadosPath,ArrayList<Ronda> rondas,ArrayList<Partido> partidos,ArrayList<Equipo> equipos) {
		try {
			for (String linea : Files.readAllLines(resultadosPath)) {
				if(verificarLineaResultado(linea)) {
		        	String nroRonda= linea.split(",")[0];
		        	Ronda auxRonda= new Ronda(nroRonda);
		        	if(posRonda(rondas,nroRonda)!=-1) {
		        		auxRonda=rondas.get(posRonda(rondas,nroRonda));
		        	}else {
		        		rondas.add(auxRonda);
		        	}
		        	
		        	String nomEq1=linea.split(",")[1];
		        	String nomEq2=linea.split(",")[4];
		        	Equipo aux1 = new Equipo(nomEq1, "");
		            if(posEquipo(equipos, nomEq1)!=-1) {
		            	aux1 = equipos.get(posEquipo(equipos,nomEq1));	
		            }else {
		            	equipos.add(aux1);
		            }
		            Equipo aux2 = new Equipo(nomEq2, "");
		            if(posEquipo(equipos,nomEq2)!=-1) {
		            	aux2= equipos.get(posEquipo(equipos,nomEq2));
		            }else {
		            	equipos.add(aux2);
		            }
		            Partido auxPart= new Partido(aux1, aux2, Integer.parseInt(linea.split(",")[2]),Integer.parseInt(linea.split(",")[3]));
		            partidos.add(auxPart);
		            auxRonda.agregarPartido(auxPart);
		        }
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void leerArchivoPronosticos(Path pronosticoPath,ArrayList<Participante> participantes,ArrayList<Equipo> equipos,ArrayList<Partido> partidos) {
		try {
			for(String linea: Files.readAllLines(pronosticoPath)) {
            	String nomPart= linea.split(",")[0];
            	Participante auxPart= new Participante(nomPart);
            	if(posParticipante(participantes,nomPart)!=-1) {
            		auxPart=participantes.get(posParticipante(participantes, nomPart));
            	}else {
            		participantes.add(auxPart);
            	}
            	
            	String nomEq1= linea.split(",")[1];
            	String nomEq2= linea.split(",")[5];
            	Equipo eqAux1= equipos.get(posEquipo(equipos, nomEq1));
            	Equipo eqAux2= equipos.get(posEquipo(equipos,nomEq2));
            	
            	Pronostico aux = new Pronostico(buscarPartido(partidos,eqAux1,eqAux2),eqAux1,resEquipo1(linea.split(",")));
            	
            	auxPart.agregarPronostico(aux);
            }
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	public static boolean acertoPronosticosDeRonda(Participante part,Ronda ronda) {
		boolean r=true;
		ArrayList<Partido> partidosAcertados= new ArrayList<Partido>();
		for(Pronostico p:part.pronosticosAcertados()) {
			partidosAcertados.add(p.getPartido());
		}
		for(Partido p:ronda.getPartidos()) {
			if(!partidosAcertados.contains(p)) {
				r=false;
			}
		}
		
		return r;
	}
	
	public static void mostrarParticipante(Participante p) {
		System.out.println("Participante: "+p.getNombre()+"\t"+"Puntos: "+p.puntosTotales()+"\t"+"Pronosticos acertados: "+p.pronosticosAcertados().size());
	}

	/*public static boolean nombreADefinir(ArrayList<Ronda> rondas,Participante part, String nroRonda) {
		boolean r=true;
		Ronda auxRonda= new Ronda("0");
		for(Ronda ron:rondas) {
			if(ron.getNro().equals(nroRonda)) {
				auxRonda=ron;
			}
		}
		
		return r;
	}*/
}
