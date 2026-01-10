/**
 * @description
 * Componente de Consultas React
 * 
 * Suporta consultas de Pessoas, Transacoes e Totais
 */
const QueryAll: React.FC = () => {
  const handleQuery = async (e: any) => {
    const queryType = e.target.name
    const res = await fetch(`http://localhost:8080/${queryType}`);
    const text = await res.text();

    alert("Resultado em JSON: \n\n" + text);
  }

  return (
    <>
      <br />
      <button name="persons" onClick={handleQuery}>Consultar Pessoas</button>
      <br />
      <br />
      <button name="transactions" onClick={handleQuery}>Consultar Transações</button>
      <br />
      <br />
      <button name="summary" onClick={handleQuery}>Consulta de Totais</button>
    </>
  )
}

export default QueryAll
