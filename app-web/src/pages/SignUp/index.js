import React from 'react'
import PropTypes from 'prop-types'
import { Link, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'

import { createUser } from '../../redux/reducers/usuario/action-creators'

import './styles.css'

const SignUp = ({ erro, isLoading, handleSubmit }) => (
  <div className="container-fluid">
    <div className="row justify-content-center align-items-center center">
      <div className="col-8 col-sm-8 col-md-8 col-lg-6 col-xl-4">
        <form onSubmit={handleSubmit}>
          <div className="card">
            <h2 className="card-header text-center">Organize Series</h2>
            <div className="card-body">
              <h4 className="card-title text-center">Criar conta</h4>
              {erro.mensagem && (
                <div
                  className={`alert ${
                    erro.codigo !== 201 ? 'alert-danger' : 'alert-success'
                  }`}
                  role="alert"
                >
                  {erro.mensagem}
                </div>
              )}
              <div className="form-group input-group-lg">
                <input
                  type="name"
                  id="name"
                  className="form-control"
                  placeholder="Nome completo"
                  name="nome"
                />
              </div>
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
                  'Criar conta'
                ) : (
                  <i className="fa fa-spinner fa-pulse" />
                )}
              </button>
            </div>
            <Link to="/" className="text-center">
              Entrar
            </Link>
          </div>
        </form>
      </div>
    </div>
  </div>
)

SignUp.propTypes = {
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
    dispatch(
      createUser({
        email: e.target.email.value,
        senha: e.target.senha.value,
        nome: e.target.nome.value
      })
    )

    e.target.email.value = ''
    e.target.senha.value = ''
    e.target.nome.value = ''
  }
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(SignUp))
