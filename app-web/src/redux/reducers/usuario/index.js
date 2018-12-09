import createReducer from '../create-reducer'
import { LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE } from './actions'

const initialState = {
  id: 0,
  nome: '',
  email: '',
  senha: '',
  erro: {
    codigo: 0,
    mensagem: ''
  },
  isLoading: false
}

const auth = createReducer(initialState, {
  [LOGIN_REQUEST]: (state, action) => ({
    ...state,
    isLoading: true
  }),
  [LOGIN_SUCCESS]: (state, action) => ({
    ...state,
    ...action.payload.user,
    isLoading: false
  }),
  [LOGIN_FAILURE]: (state, action) => ({
    ...state,
    erro: action.payload.erro,
    isLoading: false
  })
})

export default auth
