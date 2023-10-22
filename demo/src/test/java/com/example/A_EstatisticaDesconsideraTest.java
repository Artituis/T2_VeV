package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class A_EstatisticaDesconsideraTest {

    private static IEventoRepository rep;
    
    @BeforeAll
    public static void inicializa(){
        rep = mock(IEventoRepository.class);
        when(rep.todos()).thenReturn(Arrays.asList(
            new Evento(10,"POA Day RUN", 10, 3, 2021, 5000, 0, 43, 0),
            new Evento(12,"POA Night RUN", 15, 5, 2021, 5000, 0, 42,0),
            new Evento(11,"NIKE RUN", 17, 6, 2021, 21000, 1, 22,0),
            new Evento(14,"SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),      
            new Evento(16,"SPRING RUN", 22, 8, 2021, 5000, 0, 41, 30),      
            new Evento(18,"WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30)));      
    }
    
    @Test
    public void testEstatisticaDesconsidera(){
        EstatisticaDesconsidera est = new EstatisticaDesconsidera(rep);
        // Falta fazer os calculos pra fazer o assertEquals
        EstatisticasDTO estatisticas = est.calculaEstatisticas(5000);
        assertEquals(2520.0, estatisticas.getMedia());
        assertEquals(2520.0, estatisticas.getMediana());
        assertEquals(2250, estatisticas.getDesvioPadrao());
    }
}
