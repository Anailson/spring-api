package com.loiane.service;

import com.loiane.exception.RecordNotFoundException;
import com.loiane.model.Curso;
import com.loiane.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * A Service criar toda a logica na aplicação. E assim fazendo a chamda na controller
 */
@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public @ResponseBody List<Curso> listaCursos(){

        return cursoRepository.findAll();
    }

    public Curso buscaPorId(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Curso criarCurso(@Valid Curso curso){
            return cursoRepository.save(curso);
    }

    public Curso atualizar( @NotNull @Positive Long id,Curso curso){
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(curso.getNome());
                    recordFound.setCategoria(curso.getCategoria());
                    return  cursoRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void deletarRegistros(@NotNull @Positive Long id){

        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

        /*Refatorando acima. Ambas maneiras de excluir estão corretas
         cursoRepository.findById(id)//caso tenha informação registro
                .map(recordFound -> {
                    cursoRepository.deleteById(id);
                    return  true;
                })
                .orElseThrow(() -> new RecordNotFoundException(id));

         */
    }

}
