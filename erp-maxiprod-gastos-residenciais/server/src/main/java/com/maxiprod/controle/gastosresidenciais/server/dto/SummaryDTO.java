package com.maxiprod.controle.gastosresidenciais.server.dto;

/**
 * @description
 *              Classe para auxiliar no parsing de informacoes do banco de dados
 *              para
 *              um objeto Java valido
 *
 */
public class SummaryDTO {
  private Long personId;
  private Double totalExpense;
  private Double totalIncome;
  private Double modulatedBalance;

  public SummaryDTO(Long personId, Double totalExpense, Double totalIncome, Double modulatedBalance) {
    this.personId = personId;
    this.totalExpense = totalExpense;
    this.totalIncome = totalIncome;
    this.modulatedBalance = modulatedBalance;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public Double getTotalExpense() {
    return totalExpense;
  }

  public void setTotalExpense(Double totalExpense) {
    this.totalExpense = totalExpense;
  }

  public Double getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(Double totalIncome) {
    this.totalIncome = totalIncome;
  }

  public Double getModulatedBalance() {
    return modulatedBalance;
  }

  public void setModulatedBalance(Double modulatedBalance) {
    this.modulatedBalance = modulatedBalance;
  }
}
