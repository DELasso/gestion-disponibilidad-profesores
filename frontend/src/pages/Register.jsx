// src/components/Register.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register } from '../services/api';
import '../styles/Register.css';

// Componente para el formulario de registro de usuarios
export default function Register() {
  // Estado inicial del formulario
  const [formData, setFormData] = useState({
    nombre: '',
    correo: '',
    contraseña: '',
    rol: 'estudiante',
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  // Maneja cambios en los inputs del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Maneja el envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await register(formData);
      navigate('/');
    } catch (err) {
      setError(err.message || 'Error al registrarse');
    }
  };

  return (
    <div className="register-container">
      <form onSubmit={handleSubmit} className="register-form">
        <h2>Crear Cuenta</h2>
        
        <div className="form-group">
          <input
            type="text"
            name="nombre"
            placeholder="Nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
            autoComplete="name"
          />
        </div>

        <div className="form-group">
          <input
            type="email"
            name="correo"
            placeholder="Correo electrónico"
            value={formData.correo}
            onChange={handleChange}
            required
            autoComplete="email"
          />
        </div>

        <div className="form-group">
          <input
            type="password"
            name="contraseña"
            placeholder="Contraseña"
            value={formData.contraseña}
            onChange={handleChange}
            required
            autoComplete="new-password"
          />
        </div>

        <div className="form-group">
          <select
            name="rol"
            value={formData.rol}
            onChange={handleChange}
            required
          >
            <option value="estudiante">Estudiante</option>
            <option value="profesor">Profesor</option>
          </select>
        </div>

        {error && <p className="error-message">{error}</p>}

        <button type="submit" className="submit-button">
          Registrarse
        </button>
      </form>
    </div>
  );
}