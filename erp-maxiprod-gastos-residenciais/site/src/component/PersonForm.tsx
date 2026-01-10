import React, { useState } from "react";

interface Person {
  name: string;
  age: string;
}

/**
 * @description
 * Componente de Formulario para cadastro de Pessoas
 * 
 * O cadastro de pessoas possui os campos de 
 *
 * Nome (texto)
 * Idade (texto)
 */
const PersonForm: React.FC = () => {
  const [person, setPerson] = useState<Person>({
    name: "",
    age: "",
  });

  const handleFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(person);
    fetch("http://localhost:8080/person", {
      method: "POST",
      body: JSON.stringify(person),
      headers: {
        "Content-Type": "application/json",
      },
    });
  };

  const handlePersonChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // console.log(e.target.name, e.target.value);
    setPerson({ ...person, [e.target.name]: e.target.value });
  };

  return (
    <div>
      Cadastro de Pessoa
      <form onSubmit={handleFormSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Nome"
          value={person.name}
          onChange={handlePersonChange}
        />
        <br />
        <input
          type="text"
          name="age"
          placeholder="Idade"
          value={person.age}
          onChange={handlePersonChange}
        />
        <br />
        <button type="submit">OK</button>
      </form>
      <br />
    </div>
  );
};

export default PersonForm;
