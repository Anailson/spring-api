package com.loiane.controller;

import com.loiane.model.Curso;
import com.loiane.repository.CursoRepository;
import com.loiane.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/cursos")
//@AllArgsConstructor
public class CursoController {

   // private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
       // this.cursoRepository = cursoRepository;
        this.cursoService = cursoService;
    }

    @GetMapping
    public @ResponseBody List <Curso> listaCursos(){

        return cursoService.listaCursos();
    }

    @GetMapping("/{id}")
    public Curso buscaPorId(@PathVariable @NotNull @Positive Long id){
        return cursoService.buscaPorId(id);
             //   .map(recordFound -> ResponseEntity.ok().body(recordFound))
                //caso tenha informação no BD pelo ID tera retorno
                //Caso NÃO tenha será notFound
             //   .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso criarCurso(@RequestBody @Valid Curso curso){
        //System.out.println(curso.getNome());
        return cursoService.criarCurso(curso);
         // return ResponseEntity.status(HttpStatus.CREATED)
        //   .body(cursoRepository.save(curso));
    }

    //atualizando registros
    @PutMapping("{id}")
    public Curso atualizar(@PathVariable @NotNull @Positive Long id,  @RequestBody Curso curso){
         return cursoService.atualizar(id, curso);
               //  .map(recordFound ->ResponseEntity.ok().body(recordFound))
                //     .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarRegistros(@PathVariable @NotNull @Positive Long id){

       /* if(cursoService.deletarRegistros(id)){
            return  ResponseEntity.noContent().<Void>build();
        }

        */
       // return ResponseEntity.notFound().build();
        cursoService.deletarRegistros(id);
    }


}
