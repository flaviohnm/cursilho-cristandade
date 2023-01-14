package com.cursilhos.cadastro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name="endereco")
@Getter
@Setter
public class Endereco {
    @Id
    private String id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    private String bairro;
    private String cidade;
    private String uf;
    @Pattern(regexp = "\\d{5}-\\d{3}", message="CEP não está no formato válido")
    private String cep;
}