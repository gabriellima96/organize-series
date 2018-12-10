import {
  buscarTodos,
  salvar,
  remover,
  atualizar
} from '../../../services/genero-servico'
import {
  GENERO_REQUEST,
  GENERO_LIST_SUCCESS,
  GENERO_FAILURE,
  GENERO_CLEAN_ERRO,
  GENERO_DELETE_SUCCESS,
  GENERO_EDIT_SUCCESS
} from './actions'

export const createGenero = genero => async (dispatch, getState) => {
  dispatch({ type: GENERO_REQUEST })

  console.log('entrou aqui', genero)

  if (!genero.nome) {
    dispatch({
      type: GENERO_FAILURE,
      payload: {
        alert: {
          codigo: 400,
          mensagem: 'Campo nome é obrigatorio para adicionar um Genero',
          erro: true
        }
      }
    })
  }

  try {
    await salvar(genero)

    const response = await buscarTodos()

    dispatch({
      type: GENERO_LIST_SUCCESS,
      payload: {
        generos: response.data,
        alert: {
          codigo: response.status,
          mensagem: 'Genero criado com sucesso',
          erro: false
        }
      }
    })
  } catch (e) {
    error(e, dispatch)
  } finally {
    clean(dispatch)
  }
}

export const deleteGenero = id => async (dispatch, getState) => {
  dispatch({ type: GENERO_REQUEST })

  try {
    await remover(id)

    dispatch({
      type: GENERO_DELETE_SUCCESS,
      payload: {
        id,
        alert: {
          codigo: 201,
          mensagem: 'Genero deletado com sucesso',
          erro: false
        }
      }
    })
  } catch (e) {
    error(e, dispatch)
  } finally {
    clean(dispatch)
  }
}

export const findAllGenero = () => async (dispatch, getState) => {
  dispatch({ type: GENERO_REQUEST })

  try {
    const response = await buscarTodos()

    dispatch({
      type: GENERO_LIST_SUCCESS,
      payload: {
        generos: response.data,
        alert: {
          codigo: response.status,
          mensagem: 'Busca realizada com sucesso',
          erro: false
        }
      }
    })
  } catch (e) {
    error(e, dispatch)
  } finally {
    clean(dispatch)
  }
}

export const editGenero = genero => async (dispatch, getState) => {
  dispatch({ type: GENERO_REQUEST })

  console.log('entrou aqui edit', genero)

  if (!genero.nome) {
    dispatch({
      type: GENERO_FAILURE,
      payload: {
        alert: {
          codigo: 400,
          mensagem: 'Campo nome é obrigatorio para editar um Genero',
          erro: true
        }
      }
    })
  }

  try {
    await atualizar(genero)

    dispatch({
      type: GENERO_EDIT_SUCCESS,
      payload: {
        genero: genero,
        alert: {
          codigo: 200,
          mensagem: 'Genero editado com sucesso',
          erro: false
        }
      }
    })
  } catch (e) {
    error(e, dispatch)
  } finally {
    clean(dispatch)
  }
}

function error (e, dispatch) {
  if (e.response) {
    dispatch({
      type: GENERO_FAILURE,
      payload: { alert: { ...e.response.data, erro: true } }
    })
  } else {
    dispatch({
      type: GENERO_FAILURE,
      payload: {
        alert: {
          codigo: 500,
          mensagem: 'Ocorreu um erro inesperado',
          erro: true
        }
      }
    })
  }
}

function clean (dispatch) {
  setTimeout(() => {
    dispatch({
      type: GENERO_CLEAN_ERRO
    })
  }, 5000)
}
