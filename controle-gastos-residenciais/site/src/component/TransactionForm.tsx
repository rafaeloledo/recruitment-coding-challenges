import React, { useState } from "react";

enum TransactionType {
  EXPENSE = "EXPENSE",
  OUTCOME = "OUTCOME",
}

interface Transaction {
  description: string;
  value: string;
  personId: number,
  type: TransactionType;
}

/**
 * @description
 * Componente de Formulario para cadastro de Transacoes
 * 
 * O cadastro de transacoes possui os campos de 
 *
 * Descricao (texto)
 * Valor da transacao (numero)
 * Identificador da Pessoa (exatamente igual ao id privado do banco de dados)
 * Tipo de transacao (enumeracao que aceita apenas dois valores e falha se n selecionar)
 */
const TransactionForm: React.FC = () => {
  const [transaction, setTransaction] = useState<Transaction>({
    description: "",
    value: "",
    personId: -1,
    type: "" as TransactionType,
  });

  const handleSubmit = (e: any) => {
    e.preventDefault();
    if (transaction.type === "" as TransactionType) {
      alert("Selecione um tipo de depesa");
      return;
    }
    fetch("http://localhost:8080/transaction", {
      method: "POST",
      body: JSON.stringify(transaction),
      headers: {
        "Content-Type": "application/json",
      },
    });
  };

  const handleInputChange = (e: any) => {
    const targetName = e.target.name
    const targetValue = e.target.value

    if (targetName === "type") {
      setTransaction((t) => {
        return {
          ...t,
          type: targetValue
        }
      })
    }

    setTransaction((t) => {
      return {
        ...t,
        [targetName]: targetValue
      };
    });
  };

  return (
    <div>
      Cadastro de Transações
      <form onSubmit={handleSubmit} method="POST">
        <input
          type="text"
          name="description"
          placeholder="Descricação"
          value={transaction.description}
          onChange={handleInputChange}
        />
        <br />
        <input
          type="text"
          name="value"
          placeholder="Valor da transação"
          value={transaction.value}
          onChange={handleInputChange}
        />
        <br />
        <label>Identificador da Pessoa (numero)</label>
        <br />
        <input
          type="text"
          name="personId"
          placeholder="Identificador"
          value={transaction.personId}
          onChange={handleInputChange}
        />
        <br />

        <label>Tipo da Transação</label>
        <br />
        <select name="type" onChange={handleInputChange}>
          <option value="">Selecione uma opcao...</option>
          <option value={TransactionType.EXPENSE}>Despesa</option>
          <option value={TransactionType.OUTCOME}>Receita</option>
        </select>
        <br />
        <button type="submit">OK</button>
      </form>
    </div >
  );
};

export default TransactionForm;
