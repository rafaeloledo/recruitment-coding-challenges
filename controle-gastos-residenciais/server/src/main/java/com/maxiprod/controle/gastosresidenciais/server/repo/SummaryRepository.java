package com.maxiprod.controle.gastosresidenciais.server.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maxiprod.controle.gastosresidenciais.server.dto.SummaryDTO;
import com.maxiprod.controle.gastosresidenciais.server.model.Transaction;

@Repository
public interface SummaryRepository extends JpaRepository<Transaction, Long> {

  /**
   * @title Soma de totais
   *
   * @description
   *              Retorna um sumario de pessoas e transacoes com o valor total de
   *              cada pessoal
   *              e a Soma de todas as pessoas ao final.
   *
   *              Primeiro, junta-se os registros pessoas que possuem transacoes
   *              com (nem todas
   *              as pessoas tem transacoes) `INNER JOIN person p ON t.person_id =
   *              p.person_id`.
   * 
   *              Tendo os dados em maos, calculamos o total de depesas, receitas
   *              e o saldo de
   *              cada pessoa.Calcula-se, tambem, o total fazendo uma soma de cada
   *              pessoa e
   *              colocando na linha final.Por fim, juntamos o resultado com
   *              `UNION ALL` em
   *              apenas um output
   *
   * @param
   *
   * @return Lista de DTO que sao retornadas como um array de objetos (JSON)
   */
  @Query(value = """
      SELECT t.person_id,
        SUM(CASE WHEN t.type = 'EXPENSE' THEN t.value ELSE 0 END) AS total_expense,
        SUM(CASE WHEN t.type = 'OUTCOME' THEN t.value ELSE 0 END) AS total_income,
        ABS(SUM(CASE WHEN t.type = 'OUTCOME' THEN t.value ELSE 0 END) -
            SUM(CASE WHEN t.type = 'EXPENSE' THEN t.value ELSE 0 END)) AS modulated_balance
      FROM transaction t INNER JOIN person p ON t.person_id = p.person_id
      GROUP BY t.person_id

      UNION ALL

      SELECT NULL AS person_id,
        SUM(CASE WHEN t.type = 'EXPENSE' THEN t.value ELSE 0 END) AS total_expense,
        SUM(CASE WHEN t.type = 'OUTCOME' THEN t.value ELSE 0 END) AS total_income,
        ABS(SUM(CASE WHEN t.type = 'OUTCOME' THEN t.value ELSE 0 END) -
            SUM(CASE WHEN t.type = 'EXPENSE' THEN t.value ELSE 0 END)) AS modulated_balance
      FROM transaction t INNER JOIN person p ON t.person_id = p.person_id;
            """, nativeQuery = true)
  List<SummaryDTO> getSummary();
}
