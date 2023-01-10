package com.cursilhos.cadastro.model;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.enumeration.Igreja;
import com.cursilhos.cadastro.enumeration.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name="cursilhista")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cursilhista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CPF(message="CPF informado não é valido")
    @NotNull(message="CPF não pode ser nulo")
    private String cpf;
    @NotNull(message="fullName não pode ser nulo")
    @NotBlank(message="fullName não pode ser vazio")
    private String fullName;
    @NotNull(message="displayName não pode ser nulo")
    @NotBlank(message="displayName não pode ser vazio")
    private String displayName;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime insertDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime confirmationDate;
    private String conjugeName;
    private String conjugePhoneNumber;
    @NotNull
    private String emergencyName;
    @NotNull
    private String emergencyPhoneNumber;
    @Builder.Default
    private boolean foodRestriction = false;
    private String foodRestrictionDescription;
    @Enumerated
    private FormaPagamento formaPagamento;
    @Enumerated
    private Transport transport;
    @Enumerated
    private Igreja igreja;
    @Builder.Default
    private boolean confirmed = false;

      @Override
    public String toString(){
        return "{id="+id+", cpf="+cpf+", fullName=" +fullName+", displayName=" +displayName+", phoneNumber="+phoneNumber+", mobileNumber="+mobileNumber+", emailAddress="+emailAddress+", birthDate="+birthDate+", insertDate="+insertDate+", confirmationDate="+confirmationDate+", conjugeName="+conjugeName+", conjugePhoneNumber="+conjugePhoneNumber+", emergencyName="+emergencyName+", emergencyPhoneNumber="+emergencyPhoneNumber+", foodRestriction="+foodRestriction+", foodRestrictionDescription="+foodRestrictionDescription+", formaPagamento="+formaPagamento+", transport="+transport+", igreja=" +igreja+", confirmed="+confirmed+"}";
    }
}
