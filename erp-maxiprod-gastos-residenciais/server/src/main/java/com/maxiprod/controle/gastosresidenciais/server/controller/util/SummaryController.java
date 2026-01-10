package com.maxiprod.controle.gastosresidenciais.server.controller.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxiprod.controle.gastosresidenciais.server.dto.SummaryDTO;
import com.maxiprod.controle.gastosresidenciais.server.repo.SummaryRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SummaryController {
  private final SummaryRepository summaryRepository;

  public SummaryController(SummaryRepository summaryRepository) {
    this.summaryRepository = summaryRepository;
  }

  /**
   * @description
   *              Endpoint para obter a Consulta de Totais
   *
   * @param
   *
   * @return Lista de DTO envolvido em um Objeto ResponseEntity
   */
  @GetMapping("/summary")
  public ResponseEntity<List<SummaryDTO>> getSummary() {
    return new ResponseEntity<>(summaryRepository.getSummary(), HttpStatus.OK);
  }
}
