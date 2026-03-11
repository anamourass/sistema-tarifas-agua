package com.ras.tarifas.service;

import com.ras.tarifas.dto.CalculoRequestDTO;
import com.ras.tarifas.dto.CalculoResponseDTO;
import com.ras.tarifas.dto.DetalhamentoDTO;
import com.ras.tarifas.dto.FaixaConsumoDTO;
import com.ras.tarifas.model.FaixaConsumo;
import com.ras.tarifas.repository.FaixaConsumoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculoService {

    private final FaixaConsumoRepository faixaRepository;

    public CalculoService(FaixaConsumoRepository faixaRepository) {
        this.faixaRepository = faixaRepository;
    }

    public CalculoResponseDTO calcular(CalculoRequestDTO request){

        List<FaixaConsumo> faixas =
                faixaRepository.buscarPorCategoria(request.getCategoria());

        int consumo = request.getConsumo();

        BigDecimal total = BigDecimal.ZERO;

        List<DetalhamentoDTO> detalhes = new ArrayList<>();

        for(FaixaConsumo faixa : faixas){

            if(consumo <= 0){
                break;
            }

            int inicio = faixa.getConsumoMinimo().intValue();
            int fim = faixa.getConsumoMaximo().intValue();

            int limite = fim - inicio + 1;

            int consumoFaixa = Math.min(consumo, limite);

            BigDecimal subtotal =
                    //faixa.getValorTarifa()
                    BigDecimal.valueOf(faixa.getValorTarifa())
                            .multiply(BigDecimal.valueOf(consumoFaixa));

            total = total.add(subtotal);

            consumo -= consumoFaixa;

            detalhes.add(new DetalhamentoDTO(
                    inicio,
                    fim,
                    consumoFaixa,
                    BigDecimal.valueOf(faixa.getValorTarifa()),
                    subtotal
            ));
        }

        return new CalculoResponseDTO(
                request.getCategoria().name(),
                request.getConsumo(),
                total,
                detalhes
        );
    }
}