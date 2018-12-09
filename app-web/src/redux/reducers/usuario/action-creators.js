import { push } from 'connected-react-router'
import { auth } from '../../../services/usuario-service'
import { LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE } from './actions'

export const login = (email, senha) => async (dispatch, getState) => {
  dispatch({ type: LOGIN_REQUEST })

  if (!email || !senha) {
    dispatch({
      type: LOGIN_FAILURE,
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
          type: LOGIN_FAILURE,
          payload: { erro: e.response.data }
        })
      } else {
        dispatch({
          type: LOGIN_FAILURE,
          payload: {
            erro: { codigo: 500, mensagem: 'Ocorreu um erro inesperado' }
          }
        })
      }
    }
  }
}
