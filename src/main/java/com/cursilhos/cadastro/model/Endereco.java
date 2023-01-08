package com.cursilhos.cadastro.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String referencePoint;
    private String cidade;
    private String uf;
    private String complemento;
    private String cep;
    @OneToOne(cascade = CascadeType.ALL)
    private Cursilhista cursilhista;

}
