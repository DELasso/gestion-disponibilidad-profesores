import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../services/api';
import '../styles/Login.css';

// Componente para el formulario de inicio de sesión
export default function Login() {
  // Estados para los campos del formulario y mensajes de error
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  // Maneja cambios en los inputs del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Maneja el envío del formulario de inicio de sesión
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const usuario = await login(formData.email, formData.password);
      localStorage.setItem('usuario', JSON.stringify(usuario));
      navigate('/dashboard');
    } catch (err) {
      setError(err.message || 'Error al iniciar sesión');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Iniciar Sesión</h2>

        <div className="form-group">
          <input
            type="email"
            name="email"
            placeholder="Correo electrónico"
            value={formData.email}
            onChange={handleChange}
            required
            autoComplete="email"
          />
        </div>

        <div className="form-group">
          <input
            type="password"
            name="password"
            placeholder="Contraseña"
            value={formData.password}
            onChange={handleChange}
            required
            autoComplete="current-password"
          />
        </div>

        {error && <p className="error-message">{error}</p>}

        <button type="submit" className="submit-button">
          Entrar
        </button>
      </form>
    </div>
  );
}