  package com.example;

public class ServicoEstatisticaTest {
    
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

    @Before
    public void setUp() {
        ICalculoEstatistica calculoEstatistica = mock(ICalculoEstatistica.class);
        servicoEstatistica = new ServicoEstatistica(rep, calculoEstatistica);
    }

    @Test
    public void testCalculaEstatisticas() {
        int distancia = 5000;

        EstatisticasDTO estatisticasEsperadas = new EstatisticasDTO();
        estatisticasEsperadas.setMedia(2550);
        estatisticasEsperadas.setMediana(2550);
        estatisticasEsperadas.setDesvioPadrao(2520);
        when(calculoEstatistica.calculaEstatisticas(distancia)).thenReturn(estatisticasEsperadas);

        EstatisticasDTO result = servicoEstatistica.calculaEstatisticas(distancia);

        assertEquals(estatisticasEsperadas, result);
    }

    @Test
    public void testCalculaAumentoPerformance() {
        int distancia = 5000;
        int ano = 2021;

        PerformanceDTO result = servicoEstatistica.calculaAumentoPerformance(distancia, ano);

        String nomePrimeiroEventoEsperado = "POA Night RUN";
        String nomeSegundoEventoEsperado = "SUMMER RUN";

        assertEquals(nomePrimeiroEventoEsperado, result.getNomeEvento1());
        assertEquals(nomeSegundoEventoEsperado, result.getNomeEvento2());
        assertEquals(aumentoDesempenhoEsperado, result.getAumentoDesempenho(), 0.01); 
    }
    
}