package com.maxiprod.controle.gastosresidenciais.server.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

/**
 * @description
 *              Classe para simular uma transacao e a converter em objetos Java
 *              validos
 */
@Entity
public class Transaction implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private double value;

  @Column(nullable = false)
  private Long personId;

  @Column(nullable = false)
  private String type;

  protected Transaction() {
  }

  public Transaction(
      String description,
      double value,
      Long personAge,
      String type) {
    this.description = description;
    this.value = value;
    this.type = type;
  }

  public Long getTransactionId() {
    return this.transactionId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    System.out.println("Chamei o setDescription " + description);
    this.description = description;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    System.out.println("Chamei o setValue " + value);
    this.value = value;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    System.out.println("Chamei o setType " + type);
    this.type = type;
  }

  @Override
  public String toString() {
    return "[transactionId=" + transactionId + " description=" + description +
        " value=" + value +
        " personId=" + personId +
        " type=" + type;
  }
}
