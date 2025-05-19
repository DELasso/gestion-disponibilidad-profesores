import { useEffect, useState } from 'react';
import { listarAsesorias, cancelarAsesoria } from '../services/api';
import '../styles/Appointments.css';

// Componente para mostrar y gestionar las asesorías del usuario
export default function Appointments() {
  // Estado para almacenar la lista de asesorías
  const [asesorias, setAsesorias] = useState([]);
  const usuario = JSON.parse(localStorage.getItem('usuario'));

  // Carga las asesorías al montar el componente
  const cargarAsesorias = async () => {
    try {
      if (!usuario?.id) {
        throw new Error('Usuario no autenticado');
      }
      const data = await listarAsesorias(usuario.id);
      setAsesorias(data);
    } catch (error) {
      alert('Error al cargar las asesorías.');
    }
  };

  // Maneja la cancelación de una asesoría
  const handleCancelar = async (id) => {
    try {
      await cancelarAsesoria(id);
      alert('Asesoría cancelada correctamente.');
      await cargarAsesorias(); // Recarga la lista tras cancelar
    } catch (error) {
      alert('Error al cancelar la asesoría.');
    }
  };

  // Carga inicial de asesorías
  useEffect(() => {
    cargarAsesorias();
  }, []);

  return (
    <div className="appointments-container">
      <h2>Mis Asesorías</h2>
      {asesorias.length === 0 ? (
        <p className="no-data">No tienes asesorías programadas.</p>
      ) : (
        <ul className="asesorias-list">
          {asesorias.map((asesoria) => (
            <li key={asesoria.id} className="asesoria-item">
              <span>
                {asesoria.disponibilidad.profesor.nombre} - {asesoria.fecha} a las {asesoria.hora}
              </span>
              <button
                className="cancelar-button"
                onClick={() => handleCancelar(asesoria.id)}
                disabled={!usuario} // Deshabilita si no hay usuario
              >
                Cancelar
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}