import { useEffect, useState } from 'react';
import { getPersonas, realizarTransaccion, buscarPersona } from './apiService';

function Hola() {
  const [personas, setPersonas] = useState([]);
  const [cedulaEmisor, setCedulaEmisor] = useState('');
  const [cedulaReceptor, setCedulaReceptor] = useState('');
  const [cedula, setCedula] = useState('');
const [personaEncontrada, setPersonaEncontrada] = useState(null);

  const [monto, setMonto] = useState('');

  const [mensaje, setMensaje] = useState('');

  useEffect(() => {
    cargarPersonas();
  }, []);

  const cargarPersonas = async () => {
    try {
      const data = await getPersonas();
      setPersonas(data);
    } catch (error) {
      console.error("Error al cargar personas:", error);
    }
  };

  const handleTransaccion = async () => {
    try {
      if (!cedulaEmisor || !cedulaReceptor || !monto) {
        alert("Completa todos los campos");
        return;
      }

      if (cedulaEmisor === cedulaReceptor) {
        alert("Emisor y receptor no pueden ser la misma persona");
        return;
      }

      const response = await realizarTransaccion(cedulaEmisor, cedulaReceptor, parseFloat(monto));
      setMensaje(response.mensaje || "Transacción realizada");
      setMonto('');
      cargarPersonas();
    } catch (error) {
      setMensaje("Saldo insuficiente");
    }
  };

  const handleBuscarPersona = async () => {
  try {
    const resultado = await buscarPersona(cedula);
    setPersonaEncontrada(resultado);
  } catch (error) {
    setPersonaEncontrada(null);
    alert("Persona no encontrada");
  }
};


  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>

      <h2>Personas</h2>
      <ul>
        {personas.map((persona, index) => (
            <li key={persona.cedula || index}>
            {persona.nombre} {persona.apellido} - Cédula: {persona.cedula} - Saldo: ${persona.saldo}
            </li>
        ))}
        </ul>

      <h2>Realizar Transacción</h2>

      <div style={{ marginBottom: "10px" }}>
        <label>Emisor:</label>
        <select value={cedulaEmisor} onChange={e => setCedulaEmisor(e.target.value)}>
          <option value="">Seleccione emisor</option>
          {personas.map(p => (
            <option key={p.cedula} value={p.cedula}>
              {p.nombre} {p.apellido} ({p.cedula})
            </option>
          ))}
        </select>
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Receptor:</label>
        <select value={cedulaReceptor} onChange={e => setCedulaReceptor(e.target.value)}>
          <option value="">Seleccione receptor</option>
          {personas.map(p => (
            <option key={p.cedula} value={p.cedula}>
              {p.nombre} {p.apellido} ({p.cedula})
            </option>
          ))}
        </select>
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Monto:</label>
        <input
          type="number"
          value={monto}
          onChange={e => setMonto(e.target.value)}
          placeholder="Ej. 50.00"
        />
      </div>

      <button onClick={handleTransaccion}>Enviar</button>

      {mensaje && <p style={{ marginTop: "10px", color: "green" }}>{mensaje}</p>}


      <h3>Filtrado</h3>

        <label htmlFor="textCedula">Buscar por cédula</label>
        <input
        id="textCedula"
        type="text"
        value={cedula}
        onChange={(e) => setCedula(e.target.value)}
        />

        <button onClick={handleBuscarPersona}>Buscar</button>

        {personaEncontrada && (
        <div style={{ marginTop: "10px", border: "1px solid gray", padding: "10px" }}>
            <p><strong>Nombre:</strong> {personaEncontrada.nombre}</p>
            <p><strong>Apellido:</strong> {personaEncontrada.apellido}</p>
            <p><strong>Cédula:</strong> {personaEncontrada.cedula}</p>
            <p><strong>Saldo:</strong> ${personaEncontrada.saldo}</p>
        </div>
        )}
    </div>
  );
}

export default Hola;
