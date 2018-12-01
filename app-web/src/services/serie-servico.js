import api from "./api";

const RESOURCE = "/series";

export async function salvar(serie) {
  return await api.post(RESOURCE, serie);
}

export async function buscarTodos() {
  return await api.get(RESOURCE);
}

export async function buscarPorId(id) {
  return await api.get(`${RESOURCE}/${id}`);
}

export async function atualizar(serie) {
  return await api.put(`${RESOURCE}/${id}`, serie);
}

export async function remover(id) {
  return await api.delete(`${RESOURCE}/${id}`);
}
