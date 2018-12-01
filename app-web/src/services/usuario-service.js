import api from "./api";

const RESOURCE = "/usuarios";

export async function salvar(usuario) {
  return await api.post(RESOURCE, usuario);
}

export async function auth(credenciais) {
  return await api.post(`${RESOURCE}/autenticacao`, credenciais);
}
