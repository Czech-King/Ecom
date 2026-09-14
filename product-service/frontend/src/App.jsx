import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/api/products")
      .then((response) => {
        if (!response.ok) {
          throw new Error(`API request failed: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setProducts(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Failed to load products:", err);
        setError("Unable to load products.");
        setLoading(false);
      });
  }, []);

  return (
    <div className="app">
      <header className="header">
        <div className="container">
          <h1>Ecom Store</h1>
        </div>
      </header>

      <section className="hero">
        <div className="container">
          <h2>Welcome to Ecom Store</h2>
          <p>Find the products you need at the best prices.</p>
        </div>
      </section>

      <main className="container products-section">
        <h2>Products</h2>

        {loading && <p>Loading products...</p>}

        {error && <p className="error">{error}</p>}

        {!loading && !error && products.length === 0 && (
          <p>No products available.</p>
        )}

        <div className="product-grid">
          {products.map((product) => (
            <div className="product-card" key={product.id}>
              <div className="product-info">
                <h3>{product.name}</h3>

                <p className="price">
                  ₹{Number(product.price).toLocaleString("en-IN")}
                </p>

                <p>Product ID: {product.id}</p>

                <p>Source: {product.source}</p>

                <button onClick={() => setSelectedProduct(product)}>
                  View Product
                </button>
              </div>
            </div>
          ))}
        </div>
      </main>

      {selectedProduct && (
        <div
          className="modal-overlay"
          onClick={() => setSelectedProduct(null)}
        >
          <div
            className="modal"
            onClick={(event) => event.stopPropagation()}
          >
            <button
              className="close-button"
              onClick={() => setSelectedProduct(null)}
            >
              ×
            </button>

            <h2>{selectedProduct.name}</h2>

            <p>
              <strong>Price:</strong> ₹
              {Number(selectedProduct.price).toLocaleString("en-IN")}
            </p>

            <p>
              <strong>Product ID:</strong> {selectedProduct.id}
            </p>

            <p>
              <strong>Source:</strong> {selectedProduct.source}
            </p>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
