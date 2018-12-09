import { createStore, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'
import reducers from '../reducers'

const middlewares = [thunk]

export default ({ initialState } = {}) => {
  const enhancer = compose(
    applyMiddleware(...middlewares),
    logger()
  )

  const store = createStore(reducers, initialState, enhancer)

  return store
}

const logger = () =>
  window.__REDUX_DEVTOOLS_EXTENSION__
    ? window.__REDUX_DEVTOOLS_EXTENSION__()
    : x => x
