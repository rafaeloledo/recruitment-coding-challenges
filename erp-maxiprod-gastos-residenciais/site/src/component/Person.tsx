import { useState, useEffect } from "react";

/**
 * @description
 * Componente de Pessoa
 * 
 * Retorna os dados da pessoa cadastrada no banco e dados
 */
const Person = () => {
  const [data, setData] = useState("");

  useEffect(() => {
    const apiUrl = "http://localhost:8080/person";

    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.text();
      })
      .then((response) => {
        setData(response);
      });
  }, []);

  return <div>{data}</div>;
};

export default Person;
