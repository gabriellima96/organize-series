import { push } from 'connected-react-router'
import { auth, salvar } from '../../../services/usuario-service'
import {
  REQUEST,
  LOGIN_SUCCESS,
  FAILURE,
  CREATE_USER_SUCCESS,
  CLEAN_ERRO
} from './actions'

export const login = (email, senha) => async (dispatch, getState) => {
  dispatch({ type: REQUEST })

  if (!email || !senha) {
    dispatch({
      type: FAILURE,
      payload: {
        erro: { codigo: 400, mensagem: 'Todos os campos são obrigátorios' }
      }
    })
  } else {
    try {
      const response = await auth({ email, senha })

      dispatch({
        type: LOGIN_SUCCESS,
        payload: { user: response.data }
      })

      dispatch(push('/dashboard'))
    } catch (e) {
      if (e.response) {
        dispatch({
          type: FAILURE,
          payload: { erro: e.response.data }
        })
      } else {
        dispatch({
          type: FAILURE,
          payload: {
            erro: { codigo: 500, mensagem: 'Ocorreu um erro inesperado' }
          }
        })
      }
    } finally {
      setInterval(() => {
        dispatch({
          type: CLEAN_ERRO
        })
      }, 5000)
    }
  }
}

export const createUser = ({ email, senha, nome }) => async (
  dispatch,
  getState
) => {
  dispatch({ type: REQUEST })

  if (!email || !senha || !nome) {
    dispatch({
      type: FAILURE,
      payload: {
        erro: { codigo: 400, mensagem: 'Todos os campos são obrigátorios' }
      }
    })
  } else {
    try {
      await salvar({ email, senha, nome })

      dispatch({
        type: CREATE_USER_SUCCESS,
        payload: { erro: { codigo: 201, mensagem: 'Conta criada com sucesso' } }
      })
    } catch (e) {
      if (e.response) {
        dispatch({
          type: FAILURE,
          payload: { erro: e.response.data }
        })
      } else {
        dispatch({
          type: FAILURE,
          payload: {
            erro: { codigo: 500, mensagem: 'Ocorreu um erro inesperado' }
          }
        })
      }
    } finally {
      setTimeout(() => {
        dispatch({
          type: CLEAN_ERRO
        })
      }, 5000)
    }
  }
}
