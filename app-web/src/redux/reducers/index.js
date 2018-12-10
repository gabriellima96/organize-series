import { combineReducers } from 'redux'
import { connectRouter } from 'connected-react-router'
import usuario from './usuario'
import genero from './genero'

export default history =>
  combineReducers({
    router: connectRouter(history),
    usuario,
    genero
  })
