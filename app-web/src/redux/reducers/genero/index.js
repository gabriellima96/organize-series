import createReducer from '../create-reducer'
import {
  GENERO_REQUEST,
  GENERO_FAILURE,
  GENERO_DELETE_SUCCESS,
  GENERO_EDIT_SUCCESS,
  GENERO_LIST_SUCCESS,
  GENERO_CLEAN_ERRO
} from './actions'

const initialState = {
  generos: [],
  alert: {
    codigo: 0,
    mensagem: '',
    erro: false
  },
  isLoading: false
}

const genero = createReducer(initialState, {
  [GENERO_REQUEST]: (state, action) => ({
    ...state,
    isLoading: true
  }),
  [GENERO_CLEAN_ERRO]: (state, action) => ({
    ...state,
    alert: { codigo: 0, mensagem: '', erro: false }
  }),
  [GENERO_FAILURE]: (state, action) => ({
    ...state,
    alert: action.payload.alert,
    isLoading: false
  }),
  [GENERO_LIST_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    generos: action.payload.generos
  }),
  [GENERO_DELETE_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    generos: state.generos.filter(genero => genero.id !== action.payload.id)
  }),
  [GENERO_EDIT_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    generos: state.generos.map(genero =>
      genero.id !== action.payload.genero.id ? genero : action.payload.genero
    )
  })
})

export default genero
