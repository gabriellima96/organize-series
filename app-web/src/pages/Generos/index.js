import React, { Fragment, PureComponent } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import NavBar from '../../components/NavBar'
import ModalCreateGeneros from '../../components/ModalCreateGeneros'
import EditarGenero from '../../components/EditarGenero'
import {
  findAllGenero,
  createGenero,
  deleteGenero,
  editGenero
} from '../../redux/reducers/genero/action-creators'

import './style.css'

class Generos extends PureComponent {
  async componentDidMount () {
    await this.props.findAllGeneros()
  }

  render () {
    const {
      generos,
      isLoading,
      alert: { mensagem, erro },
      handleSubmitCreate,
      handleDeleteGenero,
      handleEditGenero
    } = this.props

    return (
      <Fragment>
        <NavBar active="generos" />
        <div className="jumbotron max">
          <div className="container">
            <div className="row">
              <div className="col-2">
                <h1 className="display-5">Gêneros</h1>
              </div>
              <div className="col-3">
                <ModalCreateGeneros
                  handleSubmitCreate={handleSubmitCreate}
                  isLoading={isLoading}
                />
              </div>
              {isLoading && (
                <div className="col-5">
                  <i className="fa fa-spinner fa-pulse" />
                </div>
              )}
              {mensagem && (
                <div className="col-5">
                  <div
                    className={`alert ${
                      erro ? 'alert-danger' : 'alert-success'
                    }`}
                    role="alert"
                  >
                    {mensagem}
                  </div>
                </div>
              )}
            </div>
            <hr className="my-4" />
            <div className="row">
              <table className="table">
                <thead className="thead-dark">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {generos.length > 0 ? (
                    generos.map(genero => (
                      <tr key={genero.id}>
                        <th scope="row">{genero.id}</th>
                        <td>{genero.nome}</td>
                        <td>
                          <EditarGenero
                            {...genero}
                            handleEditGenero={handleEditGenero(genero)}
                            isLoading={isLoading}
                          />
                          <button
                            type="button"
                            className="btn btn-outline-danger ml-2"
                            onClick={handleDeleteGenero(genero.id)}
                          >
                            <i className="fa fa-trash" /> Deletar
                          </button>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td>Sem dados.</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </Fragment>
    )
  }
}

Generos.propTypes = {
  alert: PropTypes.shape({
    codigo: PropTypes.number,
    mensagem: PropTypes.string.isRequired,
    erro: PropTypes.bool.isRequired
  }),
  generos: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      nome: PropTypes.string.isRequired
    })
  ),
  isLoading: PropTypes.bool.isRequired,
  findAllGeneros: PropTypes.func.isRequired,
  handleSubmitCreate: PropTypes.func.isRequired,
  handleDeleteGenero: PropTypes.func.isRequired,
  handleEditGenero: PropTypes.func.isRequired
}

const mapStateToProps = state => state.genero

const mapDispatchToProps = dispatch => ({
  findAllGeneros: () => dispatch(findAllGenero()),
  handleSubmitCreate: e => {
    e.preventDefault()
    dispatch(createGenero({ nome: e.target.nome.value }))
    e.target.nome.value = ''
    window.$('#modalCreateGenero').modal('hide')
  },
  handleDeleteGenero: id => e => {
    e.preventDefault()
    dispatch(deleteGenero(id))
  },
  handleEditGenero: genero => e => {
    e.preventDefault()
    dispatch(editGenero({ id: genero.id, nome: e.target.nomeEdit.value }))
    window.$(`#${genero.nome}`).modal('hide')
  }
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Generos)
