import createReducer from '../create-reducer'
import {
  REQUEST,
  LOGIN_SUCCESS,
  FAILURE,
  CREATE_USER_SUCCESS,
  CLEAN_ERRO
} from './actions'

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
  [REQUEST]: (state, action) => ({
    ...state,
    isLoading: true
  }),
  [LOGIN_SUCCESS]: (state, action) => ({
    ...state,
    ...action.payload.user,
    isLoading: false
  }),
  [FAILURE]: (state, action) => ({
    ...state,
    erro: action.payload.erro,
    isLoading: false
  }),
  [CREATE_USER_SUCCESS]: (state, action) => ({
    ...initialState,
    erro: action.payload.erro
  }),
  [CLEAN_ERRO]: (state, action) => ({
    ...state,
    erro: { codigo: 0, mensagem: '' }
  })
})

export default auth
