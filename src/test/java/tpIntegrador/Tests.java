package tpIntegrador;

import fulbo.*;
import programa.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void puntajeEnDosRondasConsecutivasTestDesdeArchivos() {
		Path resultadosPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\resultados");
        Path pronosticoPath = Path.of("D:\\ProgramFilesx86\\workspace\\tpIntegrador\\src\\main\\java\\programa\\pronostico.txt");
        
        ArrayList<Ronda> rondas= new ArrayList<Ronda>();
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Equipo> equipos= new ArrayList<Equipo>();
        ArrayList<Participante> participantes= new ArrayList<Participante>();
        
       
        Funciones.leerArchivoResultados(resultadosPath,rondas,partidos,equipos);
        Funciones.leerArchivoPronosticos(pronosticoPath,participantes,equipos,partidos);

        Participante a= participantes.get(0);
        
        assertEquals(9,a.puntosTotales());
	}
	
	@Test
	void puntajeEnDosRondasConsecutivas() {
		
		
		
	}

}
