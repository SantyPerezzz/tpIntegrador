package programa;

import fulbo.*;
import fulbo.Partido.Resultado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Path resultadosPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\resultados");
        Path pronosticoPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\pronostico.txt");

        ArrayList<Ronda> rondas= new ArrayList<Ronda>();
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos= new ArrayList<Equipo>();
        ArrayList<Participante> participantes= new ArrayList<Participante>();
        
        ArrayList<Pronostico> pronosticos= new ArrayList<Pronostico>();
        
        Funciones.leerArchivoResultados(resultadosPath,rondas,partidos,equipos);
        Funciones.leerArchivoPronosticos(pronosticoPath,participantes,equipos,partidos);
        
       
        for(Participante p: participantes) {
        	System.out.println(p.getNombre()+" tiene "+p.puntosTotales()+" puntos, y acerto "+p.pronosticosAcertados().size()+" pronosticos");
        }
        
       /* for(Partido p:partidos) {
        	System.out.println(p.getEquipo1().getNombre()+" vs "+p.getEquipo2().getNombre());
        }*/
        
        for(Ronda r: rondas) {
        	System.out.println(r.getNro()+" "+Funciones.stringVs(r.getPartidos()));
        }
    }
}