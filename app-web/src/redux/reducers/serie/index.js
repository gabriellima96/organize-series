import createReducer from '../create-reducer'
import {
  SERIE_FAILURE,
  SERIE_REQUEST,
  SERIE_CLEAN_ERRO,
  SERIE_LIST_SUCCESS,
  SERIE_DELETE_SUCCESS,
  SERIE_EDIT_SUCCESS
} from './actions'

const initialState = {
  series: [],
  alert: {
    codigo: 0,
    mensagem: '',
    erro: false
  },
  isLoading: false
}

const serie = createReducer(initialState, {
  [SERIE_REQUEST]: (state, action) => ({
    ...state,
    isLoading: true
  }),
  [SERIE_CLEAN_ERRO]: (state, action) => ({
    ...state,
    alert: { codigo: 0, mensagem: '', erro: false }
  }),
  [SERIE_FAILURE]: (state, action) => ({
    ...state,
    alert: action.payload.alert,
    isLoading: false
  }),
  [SERIE_LIST_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    series: action.payload.series
  }),
  [SERIE_DELETE_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    series: state.series.filter(serie => serie.id !== action.payload.id)
  }),
  [SERIE_EDIT_SUCCESS]: (state, action) => ({
    ...state,
    isLoading: false,
    alert: action.payload.alert,
    series: state.series.map(serie =>
      serie.id !== action.payload.serie.id ? serie : action.payload.serie
    )
  })
})

export default serie
