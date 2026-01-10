package com.maxiprod.controle.gastosresidenciais.server.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.maxiprod.controle.gastosresidenciais.server.repo.TransactionRepository;
import com.maxiprod.controle.gastosresidenciais.server.repo.PersonRepository;
import com.maxiprod.controle.gastosresidenciais.server.model.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  PersonRepository PersonRepository;

  /**
   * @title Obter todas as transacoes
   *
   * @description
   *              Retorna todas as transacoes
   *
   * @param
   * 
   * @return resposta
   */
  @GetMapping("/transactions")
  public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam(required = false) String name) {
    try {
      List<Transaction> transactions = new ArrayList<Transaction>();

      transactionRepository.findAll().forEach(transactions::add);

      if (transactions.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(transactions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @title Cadastro de transacoes
   *
   * @description
   *              Cadastra uma transacao checando a existencia da pessoa.
   *              Caso a pessoa existe, o metodo retorna HttpStatus.CREATED
   *              Caso a pessoa n exista (id nao cadastrado), o meotodo retorna
   *              HttpStatus.METHOD_NOT_ALLOWED
   *
   * @param transaction
   * 
   * @return resposta
   */
  @PostMapping("/transaction")
  public ResponseEntity<Transaction> createtransaction(@RequestBody Transaction transaction) {
    try {
      boolean personExists = PersonRepository.existsById(transaction.getPersonId());
      if (personExists) {
        Transaction _transaction = transactionRepository.saveAndFlush(transaction);
        return new ResponseEntity<>(_transaction, HttpStatus.CREATED);
      }
      return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
