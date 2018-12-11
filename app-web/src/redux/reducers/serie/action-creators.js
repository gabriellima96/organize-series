import {
  buscarTodos,
  salvar,
  remover,
  atualizar
} from '../../../services/serie-servico'
import {
  SERIE_REQUEST,
  SERIE_LIST_SUCCESS,
  SERIE_FAILURE,
  SERIE_CLEAN_ERRO,
  SERIE_DELETE_SUCCESS,
  SERIE_EDIT_SUCCESS
} from './actions'

export const deleteSerie = (usuarioId, id) => async (dispatch, getState) => {
  dispatch({ type: SERIE_REQUEST })

  try {
    await remover(usuarioId, id)

    dispatch({
      type: SERIE_DELETE_SUCCESS,
      payload: {
        id,
        alert: {
          codigo: 201,
          mensagem: 'Serie deletado com sucesso',
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

export const findAllSerie = usuarioId => async (dispatch, getState) => {
  dispatch({ type: SERIE_REQUEST })

  try {
    const response = await buscarTodos(usuarioId)

    dispatch({
      type: SERIE_LIST_SUCCESS,
      payload: {
        series: response.data,
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

export const editSerie = serie => async (dispatch, getState) => {
  dispatch({ type: SERIE_REQUEST })

  if (!serie.nome) {
    dispatch({
      type: SERIE_FAILURE,
      payload: {
        alert: {
          codigo: 400,
          mensagem: 'Campo nome é obrigatorio para editar um Serie',
          erro: true
        }
      }
    })
  }

  try {
    await atualizar(serie.usuarioId, serie)

    dispatch({
      type: SERIE_EDIT_SUCCESS,
      payload: {
        serie: serie,
        alert: {
          codigo: 200,
          mensagem: 'Serie editado com sucesso',
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

export const createSerie = (usuarioId, serie) => async (dispatch, getState) => {
  dispatch({ type: SERIE_REQUEST })

  if (!serie.nome) {
    dispatch({
      type: SERIE_FAILURE,
      payload: {
        alert: {
          codigo: 400,
          mensagem: 'Campo nome é obrigatorio para adicionar uma Serie',
          erro: true
        }
      }
    })
  }

  try {
    await salvar(usuarioId, serie)

    const response = await buscarTodos(usuarioId)

    dispatch({
      type: SERIE_LIST_SUCCESS,
      payload: {
        series: response.data,
        alert: {
          codigo: response.status,
          mensagem: 'Serie criado com sucesso',
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
      type: SERIE_FAILURE,
      payload: { alert: { ...e.response.data, erro: true } }
    })
  } else {
    dispatch({
      type: SERIE_FAILURE,
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
      type: SERIE_CLEAN_ERRO
    })
  }, 5000)
}
