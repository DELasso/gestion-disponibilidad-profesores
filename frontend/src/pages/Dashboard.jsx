import { useEffect, useState } from 'react';
import { listarDisponibilidad, agendarAsesoria } from '../services/api';
import '../styles/Dashboard.css';

// Componente para mostrar la disponibilidad de profesores y agendar asesorías
export default function Dashboard() {
  // Estado para almacenar la lista de disponibilidades
  const [disponibilidad, setDisponibilidad] = useState([]);
  const usuario = JSON.parse(localStorage.getItem('usuario'));

  // Carga la disponibilidad al montar el componente
  useEffect(() => {
    const cargarDisponibilidad = async () => {
      try {
        const data = await listarDisponibilidad();
        setDisponibilidad(data);
      } catch (error) {
        alert('Error al cargar la disponibilidad.');
      }
    };
    cargarDisponibilidad();
  }, []);

  // Maneja el agendamiento de una asesoría
  const handleAgendar = async (disponibilidadItem) => {
    const asesoria = {
      estudiante: { id: usuario.id },
      disponibilidad: { id: disponibilidadItem.id },
      fecha: new Date().toISOString().split('T')[0],
      hora: disponibilidadItem.hora,
    };

    try {
      await agendarAsesoria(asesoria);
      alert('Asesoría agendada correctamente.');
      // Opcional: Recargar la disponibilidad para reflejar cambios
      const data = await listarDisponibilidad();
      setDisponibilidad(data);
    } catch (error) {
      alert('Error al agendar la asesoría.');
    }
  };

  return (
    <div className="dashboard-container">
      <h2>Disponibilidad de Profesores</h2>
      {disponibilidad.length === 0 ? (
        <p className="no-data">No hay disponibilidad registrada.</p>
      ) : (
        <ul className="disponibilidad-list">
          {disponibilidad.map((item) => (
            <li key={item.id} className="disponibilidad-item">
              <span>
                {item.profesor.nombre} - {item.dia} a las {item.hora} ({item.modalidad})
              </span>
              <button
                className="agendar-button"
                onClick={() => handleAgendar(item)}
                disabled={!usuario} // Deshabilita si no hay usuario
              >
                Agendar
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}