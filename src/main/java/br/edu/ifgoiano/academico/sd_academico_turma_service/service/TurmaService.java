package br.edu.ifgoiano.academico.sd_academico_turma_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifgoiano.academico.sd_academico_turma_service.entity.Turma;
import br.edu.ifgoiano.academico.sd_academico_turma_service.repository.TurmaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public Turma salvar(Turma turma) {

        if (turma.getVagasOcupadas() == null) {
            turma.setVagasOcupadas(0);
        }

        if (turma.getStatus() == null) {
            turma.setStatus("ATIVA");
        }

        return repository.save(turma);
    }

    public List<Turma> listar() {
        return repository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean reservarVaga(Long turmaId) {

        Optional<Turma> optionalTurma = repository.findById(turmaId);

        if (optionalTurma.isEmpty()) {
            return false;
        }

        Turma turma = optionalTurma.get();

        if (turma.getVagasOcupadas() >= turma.getVagasTotal()) {
            return false;
        }

        turma.setVagasOcupadas(
                turma.getVagasOcupadas() + 1);

        repository.save(turma);

        return true;
    }

    @Transactional
    public boolean liberarVaga(Long turmaId) {

        Optional<Turma> optionalTurma = repository.findById(turmaId);

        if (optionalTurma.isEmpty()) {
            return false;
        }

        Turma turma = optionalTurma.get();

        if (turma.getVagasOcupadas() <= 0) {
            return false;
        }

        turma.setVagasOcupadas(
                turma.getVagasOcupadas() - 1);

        repository.save(turma);

        return true;
    }
}
