package com.maxiprod.controle.gastosresidenciais.server.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

/**
 * @description
 *              Classe para simular uma pessoa e a converter em objetos Java
 *              validos
 */
@Entity
public class Person implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long personId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int age;

  protected Person() {
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Long getId() {
    return this.personId;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "[name=" + name + " " + " age=" + age + "]";
  }
}
