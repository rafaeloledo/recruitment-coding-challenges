package com.maxiprod.controle.gastosresidenciais.server.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.maxiprod.controle.gastosresidenciais.server.repo.PersonRepository;
import com.maxiprod.controle.gastosresidenciais.server.repo.TransactionRepository;
import com.maxiprod.controle.gastosresidenciais.server.model.Person;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PersonController {
  @Autowired
  PersonRepository personRepository;
  @Autowired
  TransactionRepository transactionRepository;

  /**
   * @description
   *              Endpoint para obter a lista de pessoas cadastradas
   *
   * @param
   *
   * @return
   *         Lista de Person envolvido no Objeto ResponseEntity
   */
  @GetMapping("/persons")
  public ResponseEntity<List<Person>> getAllPersons() {
    try {
      List<Person> persons = new ArrayList<Person>();

      personRepository.findAll().forEach(persons::add);

      if (persons.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(persons, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * @description
   * Endpoint para cadastrar uma pessoa
   *
   * @param
   *
   * @return
   * Objeto Person da pessoa cadastrada envolvido no Objeto ResponseEntity
   */
  @PostMapping("/person")
  public ResponseEntity<Person> createPerson(@RequestBody Person person) {
    try {
      Person _person = personRepository.save(person);
      return new ResponseEntity<>(_person, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/person/{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
    try {
      if (!personRepository.existsById(id)) {
        System.out.println("Person don't exist");
        return ResponseEntity.notFound().build();
      }

      // Exclui todas as transações vinculadas à pessoa
      System.out.println(transactionRepository.deleteAllByPersonId(id));

      System.out.println("Teste");
      // Agora pode excluir a pessoa
      personRepository.deleteById(id);

      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
