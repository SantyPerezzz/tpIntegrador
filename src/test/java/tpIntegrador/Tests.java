package tpIntegrador;

import fulbo.*;
import fulbo.Partido.Resultado;
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
		Participante pepe=new Participante("Pepe");
		
		Ronda r1=new Ronda("1");
		Ronda r2=new Ronda("2");
		Equipo boca=new Equipo("Boca","bokabokabokaaaa");
		Equipo river=new Equipo("River","gallinita");
		Partido p1= new Partido(boca,river,1,2);
		Partido p2= new Partido(boca,river,4,2);
		r1.agregarPartido(p1);
		r2.agregarPartido(p2);
		
		pepe.agregarPronostico(new Pronostico(p1,boca,Resultado.perdio));
		pepe.agregarPronostico(new Pronostico(p2,boca,Resultado.gano));
		
		assertEquals(2,pepe.puntosTotales());
	}

}
