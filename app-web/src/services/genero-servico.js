import api from "./api";

const RESOURCE = "/generos";

export async function salvar(genero) {
  return await api.post(RESOURCE, genero);
}

export async function buscarTodos() {
  return await api.get(RESOURCE);
}

export async function buscarPorId(id) {
  return await api.get(`${RESOURCE}/${id}`);
}

export async function atualizar(genero) {
  return await api.put(`${RESOURCE}/${genero.id}`, genero);
}

export async function remover(id) {
  return await api.delete(`${RESOURCE}/${id}`);
}
