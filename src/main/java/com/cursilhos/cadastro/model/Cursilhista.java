package com.cursilhos.cadastro.model;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.enumeration.Igreja;
import com.cursilhos.cadastro.enumeration.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private String id;
    @CPF(message="CPF informado não é valido")
    @NotNull(message="CPF não pode ser nulo")
    private String cpf;
    @NotNull(message="fullName não pode ser nulo")
    @NotBlank(message="fullName não pode ser vazio")
    private String fullName;
    @NotNull(message="displayName não pode ser nulo")
    @NotBlank(message="displayName não pode ser vazio")
    private String displayName;
//    @Pattern(regexp = "(\\d{2})\\d{4}-\\d{4}", message = "phoneNumber não está no formato válido")
    private String phoneNumber;
//    @Pattern(regexp = "(\\d{2})\\d{5}-\\d{4}", message = "mobileNumber não está no formato válido")
    private String mobileNumber;
    @Email(message="Email com formato inválido")
    @NotNull(message="emailAddress não pode ser nulo")
    @NotBlank(message="emailAddress não pode ser vazio")
    private String emailAddress;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime insertDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime confirmationDate;
    private String conjugeName;
//    @Pattern(regexp = "(\\d{2})\\d{5}-\\d{4}", message = "conjugePhoneNumber não está no formato válido")
    private String conjugePhoneNumber;
    @NotNull
    private String emergencyName;
    @NotNull
//    @Pattern(regexp = "(\\d{2})\\d{5}-\\d{4}", message = "emergencyPhoneNumber não está no formato válido")
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
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}