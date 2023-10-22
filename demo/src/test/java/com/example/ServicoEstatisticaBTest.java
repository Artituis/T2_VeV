package com.example;

import org.junit.Test;
import org.mockito.Mockito;

public class ServicoEstatisticaBTest {
    public class ServicoEstatisticaTest {

        @Test
        public void testCalculaEstatisticas() {
            IEventoRepository eventoRepository = Mockito.mock(IEventoRepository.class);

            ICalculoEstatistica calculoEstatistica = Mockito.mock(ICalculoEstatistica.class);

            ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, calculoEstatistica);

            EstatisticasDTO estatisticasDTO = new EstatisticasDTO(10.0, 5.0, 2.0);
            Mockito.when(calculoEstatistica.calculaEstatisticas(100)).thenReturn(estatisticasDTO);

            EstatisticasDTO resultado = servicoEstatistica.calculaEstatisticas(100);

            assertEquals(10.0, resultado.getMedia(), 0.001);
            assertEquals(5.0, resultado.getMediana(), 0.001);
            assertEquals(2.0, resultado.getDesvioPadrao(), 0.001);
            verify(calculoEstatistica, times(1)).calculaEstatisticas(100);
        }
    }
}
