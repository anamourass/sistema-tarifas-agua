package com.ras.tarifas.service;

import com.ras.tarifas.model.TabelaTarifaria;
import com.ras.tarifas.repository.TabelaTarifariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaTarifariaService {

    private final TabelaTarifariaRepository tabelaTarifariaRepository;

    public TabelaTarifariaService(TabelaTarifariaRepository tabelaTarifariaRepository) {
        this.tabelaTarifariaRepository = tabelaTarifariaRepository;
    }

    public TabelaTarifaria salvar(TabelaTarifaria tabelaTarifaria) {
        return tabelaTarifariaRepository.save(tabelaTarifaria);
    }

    public List<TabelaTarifaria> listarTodas() {
        return tabelaTarifariaRepository.findAll();
    }

    public void deletar(Long id) {
        tabelaTarifariaRepository.deleteById(id);
    }
}

/**
 * Serviço responsável pelo gerenciamento das tabelas tarifárias.
 * Implementa as operações de CRUD para tabelas tarifárias, incluindo
 * validações de regras de negócio e mapeamento entre entidades e DTOs.
 */
/*
     * Cria uma nova tabela tarifária completa com todas as categorias e faixas.
     *
     * @param request DTO com os dados da tabela a ser criada
     * @return DTO com a tabela criada, incluindo IDs gerados
     * @throws RegraDeNegocioException se alguma regra de negócio for violada
     */
//     @Transactional
//     public TabelaTarifariaResponseDTO criar(TabelaTarifariaRequestDTO request) {
//         // log.info("Criando nova tabela tarifária: {}", request.getNome());

//         // Valida categorias duplicadas no request
//         long categoriasDistintas = request.getCategoria().stream()
//                 .map(c -> c.getCategoria())
//                 .distinct()
//                 .count();

//         // if (categoriasDistintas != request.getCategorias().size()) {
//         //     throw new RegraDeNegocioException(
//         //             "Não é permitido ter categorias duplicadas na mesma tabela tarifária."
//         //     );
//         // }

//         // Valida as faixas de cada categoria
//         for (CategoriaTarifariaDTO categoriaDTO : request.getCategoria()) {
//             log.debug("Validando faixas da categoria: {}", categoriaDTO.getCategoria());
//             validacaoFaixasService.validar(categoriaDTO.getFaixas());
//         }

//         // Constrói a entidade TabelaTarifaria
//         TabelaTarifaria tabela = TabelaTarifaria.builder()
//                 .nome(request.getNome())
//                 .descricao(request.getDescricao())
//                 .dataVigenciaInicio(request.getDataVigenciaInicio())
//                 .dataVigenciaFim(request.getDataVigenciaFim())
//                 .build();

//         // Constrói e vincula as categorias
//         List<CategoriaTarifaria> categorias = request.getCategorias().stream()
//                 .map(categoriaDTO -> {
//                     CategoriaTarifaria categoria = CategoriaTarifaria.builder()
//                             .tabelaTarifaria(tabela)
//                             .categoria(categoriaDTO.getCategoria())
//                             .build();

//                     // Constrói e vincula as faixas
//                     List<FaixaConsumo> faixas = categoriaDTO.getFaixas().stream()
//                             .map(faixaDTO -> FaixaConsumo.builder()
//                                     .categoriaTarifaria(categoria)
//                                     .inicio(faixaDTO.getInicio())
//                                     .fim(faixaDTO.getFim())
//                                     .valorUnitario(faixaDTO.getValorUnitario())
//                                     .build())
//                             .collect(Collectors.toList());

//                     categoria.setFaixas(faixas);
//                     return categoria;
//                 })
//                 .collect(Collectors.toList());

//         tabela.setCategorias(categorias);

//         TabelaTarifaria salva = repository.save(tabela);
//         log.info("Tabela tarifária criada com ID: {}", salva.getId());

//         return toResponseDTO(salva);
//    }


