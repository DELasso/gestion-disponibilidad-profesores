import { Link, useNavigate } from 'react-router-dom';
import '../styles/Navbar.css';

// Componente para la barra de navegación
export default function Navbar() {
  const navigate = useNavigate();
  const usuario = JSON.parse(localStorage.getItem('usuario'));

  // Maneja el cierre de sesión
  const handleLogout = () => {
    localStorage.removeItem('usuario');
    navigate('/');
  };

  return (
    <nav className="navbar">
      {usuario ? (
        <div className="navbar-content">
          <span className="navbar-greeting">Hola, {usuario.nombre}</span>
          <Link to="/dashboard" className="navbar-link">
            Disponibilidad
          </Link>
          <Link to="/appointments" className="navbar-link">
            Mis Asesorías
          </Link>
          <button className="logout-button" onClick={handleLogout}>
            Cerrar sesión
          </button>
        </div>
      ) : (
        <div className="navbar-content">
          <Link to="/" className="navbar-link">
            Inicio
          </Link>
          <Link to="/register" className="navbar-link">
            Registrarse
          </Link>
        </div>
      )}
    </nav>
  );
}