const API_BASE = "http://localhost:8080/api";

// ------------------ AUTENTICACIÓN ------------------

export async function login(email, contraseña) {
  const res = await fetch(`${API_BASE}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ correo: email, contraseña }),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function register(user) {
  const res = await fetch(`${API_BASE}/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

// ------------------ DISPONIBILIDAD ------------------

export async function registrarDisponibilidad(data) {
  const res = await fetch(`${API_BASE}/disponibilidad`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function listarDisponibilidad() {
  const res = await fetch(`${API_BASE}/disponibilidad`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

// ------------------ ASESORÍAS ------------------

export async function agendarAsesoria(data) {
  const res = await fetch(`${API_BASE}/asesorias`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function listarAsesorias(estudianteId) {
  const res = await fetch(`${API_BASE}/asesorias/${estudianteId}`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function cancelarAsesoria(id) {
  const res = await fetch(`${API_BASE}/asesorias/${id}`, {
    method: "DELETE",
  });
  if (!res.ok) throw new Error(await res.text());
  return res.text();
}
