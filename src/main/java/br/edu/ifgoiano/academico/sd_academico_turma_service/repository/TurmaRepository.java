package br.edu.ifgoiano.academico.sd_academico_turma_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.academico.sd_academico_turma_service.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
