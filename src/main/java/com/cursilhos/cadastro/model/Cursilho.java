package com.cursilhos.cadastro.model;

import com.cursilhos.cadastro.enumeration.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name="cursilho")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cursilho {
    @Id
    private String id;
    private String cursilhoNumber;
    @Enumerated
    private Type cursilhoType;
    private String cursilhoPeriod;
    private String cursilhoLocal;
    @Builder.Default
    private boolean cursilhoAberto = true;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Cursilhista> cursilhistas;
    private int quantidadeParticipantes;
}
