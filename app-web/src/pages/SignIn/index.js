import React from 'react'
import PropTypes from 'prop-types'
import { Link, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'

import { login } from '../../redux/reducers/usuario/action-creators'

export const SignIn = ({ erro: { mensagem }, isLoading, handleSubmit }) => (
  <div className="container-fluid">
    <div className="row justify-content-center align-items-center center">
      <div className="col-8 col-sm-8 col-md-8 col-lg-6 col-xl-4">
        <div className="card">
          <form onSubmit={handleSubmit}>
            <h2 className="card-header text-center">Organize Series</h2>
            <div className="card-body">
              <h4 className="card-title text-center">Entrar</h4>
              {mensagem && (
                <div className="alert alert-danger" role="alert">
                  {mensagem}
                </div>
              )}
              <div className="form-group input-group-lg">
                <input
                  type="email"
                  id="email"
                  className="form-control"
                  placeholder="Endereço de e-mail"
                  name="email"
                />
              </div>
              <div className="form-group input-group-lg">
                <input
                  type="password"
                  id="password"
                  className="form-control"
                  placeholder="Senha"
                  name="senha"
                />
              </div>
              <button
                disabled={isLoading}
                type="submit"
                className="btn btn-primary btn-block btn-lg"
              >
                {!isLoading ? (
                  'Entrar'
                ) : (
                  <i className="fa fa-spinner fa-pulse" />
                )}
              </button>
            </div>
          </form>
          <Link to="/signup" className="text-center">
            Criar conta
          </Link>
        </div>
      </div>
    </div>
  </div>
)

SignIn.propTypes = {
  erro: PropTypes.shape({
    codigo: PropTypes.number,
    mensagem: PropTypes.string.isRequired
  }),
  isLoading: PropTypes.bool.isRequired,
  handleSubmit: PropTypes.func.isRequired
}

const mapStateToProps = state => state.usuario

const mapDispatchToProps = dispatch => ({
  handleSubmit: e => {
    e.preventDefault()
    dispatch(login(e.target.email.value, e.target.senha.value))
  }
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(SignIn))
