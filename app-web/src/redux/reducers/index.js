import { combineReducers } from 'redux'
import { connectRouter } from 'connected-react-router'
import usuario from './usuario'

export default history =>
  combineReducers({
    router: connectRouter(history),
    usuario
  })
