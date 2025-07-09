import axios from 'axios';

const URL_BASE = "http://localhost:8080/prueba2/nuevo/";

export const getPersonas = async () => {
  try {
    const response = await axios.get(`${URL_BASE}persona`);
    return response.data;
  } catch (error) {
    console.error("Error al obtener personas:", error);
    throw error;
  }
};

export const addPersona = async (nombre, apellido, cedula, monto) => {
  const persona = { nombre, cedula, apellido, monto};
  try {
    const response = await axios.post(`${URL_BASE}persona`, persona);
    return response.data;
  } catch (error) {
    console.error("Error al agregar persona:", error);
    throw error;
  }
};

export const realizarTransaccion =  async (cedulaEmisor, cedulaReceptor, monto) => {
    const transaccion = {cedulaEmisor, cedulaReceptor, monto};
    try {
    const response = await axios.post(`${URL_BASE}transaccion`, transaccion);
    return response.data;
  } catch (error) {
    console.error("Saldo  insuficiente:", error);
    throw error;
  }
}

export const buscarPersona = async (cedula) => {
  try {
    const response = await axios.get(`${URL_BASE}persona/${cedula}`);
    return response.data;
  } catch (error) {
    console.error("Error al buscar persona:", error);
    throw error;
  }
};