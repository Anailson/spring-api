package com.loiane.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data  //equivalente ao  @Getter  - Loobok e todos os recursos
@Entity
//@Table(name = "cursos")  - caso o sistema fosse legado. E assim deixaria o nome que consta no banco
@SQLDelete(sql  ="UPDATE Curso SET status = 'Inativo' WHERE id = ?") //usando pra exclusão lógica
@Where(clause = "status = 'Ativo'")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull//não deixa nulo nem vazio
    @Length(min = 5 , max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull//não deixa nulo nem vazio
    @Length( max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 10, nullable = false)
    private String categoria;

    //controlando o status do registro. Serve como auditoria caso tenha remoção de registros
    @NotNull//não deixa nulo nem vazio
    @Length( max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}
