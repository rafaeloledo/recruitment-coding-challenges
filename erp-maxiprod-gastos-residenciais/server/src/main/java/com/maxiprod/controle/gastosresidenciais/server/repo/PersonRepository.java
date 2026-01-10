package com.maxiprod.controle.gastosresidenciais.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.maxiprod.controle.gastosresidenciais.server.model.Person;

/**
 * @description
 *              Repositorio de metodos uteis mirando a Entidade Pessoa
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
