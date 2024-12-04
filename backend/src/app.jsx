import { API_URL } from './utils/utils.config';

function App() {
  const fetchBooks = async () => {
    const response = await fetch(`${API_URL}/api/books`);
    const data = await response.json();
    console.log(data);
  };

  return (
    <div>
      <h1>Books</h1>
      <button onClick={fetchBooks}>Load Books</button>
    </div>
  );
}

export default App;
