import api from './api'

const RESOURCE = '/series'

export async function salvar (id, serie) {
  const response = await api.post(`/${id}${RESOURCE}`, serie)
  return response
}

export async function buscarTodos (id) {
  const response = await api.get(`/${id}${RESOURCE}`)
  return response
}

export async function buscarPorId (id, serieId) {
  const response = await api.get(`/${id}${RESOURCE}/${serieId}`)
  return response
}

export async function atualizar (id, serie) {
  const response = await api.put(`/${id}${RESOURCE}/${serie.id}`, serie)
  return response
}

export async function remover (id, serieId) {
  const response = await api.delete(`/${id}${RESOURCE}/${serieId}`)
  return response
}
