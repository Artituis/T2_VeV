package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EstatisticaIntegrationTest {

    private static IEventoRepository rep;

    @BeforeAll
    public static void inicializa() {
        rep = mock(IEventoRepository.class);
        when(rep.todos()).thenReturn(Arrays.asList(
                new Evento(10, "POA Day RUN", 10, 3, 2021, 5000, 0, 43, 0),
                new Evento(12, "POA Night RUN", 15, 5, 2021, 5000, 0, 42, 0),
                new Evento(11, "NIKE RUN", 17, 6, 2021, 21000, 1, 22, 0),
                new Evento(14, "SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),
                new Evento(16, "SPRING RUN", 22, 8, 2021, 5000, 0, 41, 30),
                new Evento(18, "WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30)));
    }

    @Test
    void testIntegracaoEstatistica() {
        ICalculoEstatistica calculoEstatistica = new EstatisticaNormal(rep);
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(rep, calculoEstatistica);

        EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(5000);

        assertEquals(2520, estatisticas.getMedia());
        assertEquals(2520, estatisticas.getMediana());
        assertEquals(2250, estatisticas.getDesvioPadrao());
        verify(rep, times(1)).todos();
    }
}
