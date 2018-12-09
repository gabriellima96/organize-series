import { createStore, applyMiddleware, compose } from 'redux'
import { routerMiddleware } from 'connected-react-router'
import thunk from 'redux-thunk'
import reducers from '../reducers'

import history from '../../routes/history'

const middlewares = [thunk, routerMiddleware(history)]

export default ({ initialState } = {}) => {
  const enhancer = compose(
    applyMiddleware(...middlewares),
    logger()
  )

  const store = createStore(reducers(history), initialState, enhancer)

  return store
}

const logger = () =>
  window.__REDUX_DEVTOOLS_EXTENSION__
    ? window.__REDUX_DEVTOOLS_EXTENSION__()
    : x => x
