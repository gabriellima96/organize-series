import { combineReducers } from 'redux'
import { connectRouter } from 'connected-react-router'
import usuario from './usuario'
import genero from './genero'
import serie from './serie'

export default history =>
  combineReducers({
    router: connectRouter(history),
    usuario,
    genero,
    serie
  })
