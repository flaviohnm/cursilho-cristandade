package com.cursilhos.cadastro.model;

import com.cursilhos.cadastro.enumeration.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate cursilhoStartDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate cursilhoEndDate;
    private String cursilhoLocal;
    @Builder.Default
    private boolean cursilhoAberto = true;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Cursilhista> cursilhistas;
    private int quantidadeParticipantes;
}
