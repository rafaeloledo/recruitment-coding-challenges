import "./App.css";
import PersonForm from "./component/PersonForm";
import QueryAll from "./component/QueryAll";
import TransactionForm from "./component/TransactionForm";

function App() {
  return (
    <>
      <PersonForm></PersonForm>
      <TransactionForm></TransactionForm>
      <QueryAll></QueryAll>
    </>
  );
}

export default App;
