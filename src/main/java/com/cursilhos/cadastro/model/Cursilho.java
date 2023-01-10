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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cursilhoNumber;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Cursilhista> cursilhistas;
    @Enumerated
    private Type cursilhoType;
    private String cursilhoPeriod;
    private String cursilhoLocal;
    @Builder.Default
    private boolean cursilhoAberto = true;
}
