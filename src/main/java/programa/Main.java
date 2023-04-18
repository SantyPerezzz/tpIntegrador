package programa;

import fulbo.*;
import fulbo.Partido.Resultado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    
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
	
	public static Resultado resEquipo1(String linea[]) {
		Resultado res=Resultado.perdio;
		if(linea[2].equals("X")) {
			res= Resultado.gano;
		}else if(linea[3].equals("X")) {
			res= Resultado.empato;
		}
		
		return res;
	}

    public static void main(String[] args) {
        Path resultadosPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\resultados");
        Path pronosticoPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\pronostico.txt");

        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos= new ArrayList<Equipo>();
        ArrayList<Participante> participantes= new ArrayList<Participante>();
        
        ArrayList<Pronostico> pronosticos= new ArrayList<Pronostico>();
        
        try {
            for (String linea : Files.readAllLines(resultadosPath)) {
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
                
                partidos.add(new Partido(aux1, aux2, Integer.parseInt(linea.split(",")[2]),Integer.parseInt(linea.split(",")[3])));
            }
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
            	
            	pronosticos.add(aux);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
       
        for(Participante p: participantes) {
        	System.out.println(p.getNombre()+" tiene "+p.puntosTotales()+" puntos");
        }
        
    }
}