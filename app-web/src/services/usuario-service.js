import api from './api'

const RESOURCE = '/usuarios'

export async function salvar (usuario) {
  const response = await api.post(RESOURCE, usuario)
  return response
}

export async function auth (credenciais) {
  const response = await api.post(`${RESOURCE}/autenticacao`, credenciais)
  return response
}
