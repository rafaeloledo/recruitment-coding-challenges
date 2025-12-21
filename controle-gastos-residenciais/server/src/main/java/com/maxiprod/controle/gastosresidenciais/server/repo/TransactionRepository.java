package com.maxiprod.controle.gastosresidenciais.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.maxiprod.controle.gastosresidenciais.server.model.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  /**
   * @title Deleta todas as linhas a partir de um identificador
   *
   * @description
   * Ao requsitar a delecao de uma pessoa, pode-se chamar este metodo auxiliar
   * para deletar, tambem, todas as linhas onde aquela pessoa aparece, ou seja,
   * todas as transacoes feitos por aquele pessoa.
   *
   * @param id da pessoa (precisa ser um id presente na tabela pessoa)
   *
   * @return quantas linhas foram deletadas
   */
  @Modifying
  @Transactional
  @Query("DELETE FROM Transaction t WHERE t.personId = :personId")
  int deleteAllByPersonId(@Param("personId") Long personId);
}
