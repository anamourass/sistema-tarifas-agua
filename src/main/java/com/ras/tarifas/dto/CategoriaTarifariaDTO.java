package com.ras.tarifas.dto;

public class CategoriaTarifariaDTO {

        private String nome;

        public CategoriaTarifariaDTO() {
        }

        public CategoriaTarifariaDTO(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
