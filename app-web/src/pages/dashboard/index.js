import React, { Fragment, PureComponent } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import NavBar from '../../components/NavBar'
import ModalCreateSeries from '../../components/ModalCreateSeries'
import EditarSerie from '../../components/EditarSerie'

import {
  createSerie,
  findAllSerie,
  deleteSerie,
  editSerie
} from '../../redux/reducers/serie/action-creators'
import { findAllGenero } from '../../redux/reducers/genero/action-creators'

import './style.css'

class Dashboard extends PureComponent {
  async componentDidMount () {
    await this.props.findAllSerie(this.props.usuario.id)
    await this.props.findAllGenero()
  }

  render () {
    const {
      serie,
      handleSubmitCreate,
      usuario,
      handleDeleteSerie,
      generos,
      handleEditSerie,
      serie: {
        isLoading,
        alert: { mensagem, erro }
      }
    } = this.props
    return (
      <Fragment>
        <NavBar active="series" />
        <div className="jumbotron max">
          <div className="container">
            <div className="row">
              <div className="col-2">
                <h1 className="display-5">Series</h1>
              </div>
              <div className="col-3">
                <ModalCreateSeries
                  handleSubmitCreate={handleSubmitCreate(usuario.id)}
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
                    <th scope="col">Detalhes</th>
                    <th scope="col">Ano de Lançamento</th>
                    <th scope="col">Genero</th>
                    <th scope="col">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {serie.series.length > 0 ? (
                    serie.series.map(serieObj => (
                      <tr key={serieObj.id}>
                        <th scope="row">{serieObj.id}</th>
                        <td>{serieObj.nome}</td>
                        <td>{serieObj.detalhes}</td>
                        <td>{serieObj.anoLancamento}</td>
                        <td>
                          {serieObj.generos.map(genero => genero.nome + ' ')}
                        </td>
                        <td>
                          <a
                            className="btn btn-outline-primary"
                            href="edit.html"
                          >
                            Temporadas
                          </a>
                          <EditarSerie
                            serie={serieObj}
                            generos={generos}
                            handleEditSerie={handleEditSerie(serieObj)}
                          />
                          <button
                            type="button"
                            className="btn btn-outline-danger ml-2"
                            onClick={handleDeleteSerie(usuario.id, serieObj.id)}
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

Dashboard.propTypes = {
  findAllSerie: PropTypes.func.isRequired,
  serie: PropTypes.shape({
    alert: PropTypes.shape({
      mensagem: PropTypes.string,
      codigo: PropTypes.number,
      erro: PropTypes.bool
    }),
    isLoading: PropTypes.bool,
    series: PropTypes.array
  }),
  usuario: PropTypes.shape({
    id: PropTypes.number
  }),
  handleSubmitCreate: PropTypes.func.isRequired,
  handleDeleteSerie: PropTypes.func
}

const mapStateToProps = state => ({
  serie: state.serie,
  usuario: state.usuario,
  generos: state.genero.generos
})

const mapDispatchToProps = dispatch => ({
  findAllSerie: usuarioId => dispatch(findAllSerie(usuarioId)),
  findAllGenero: () => dispatch(findAllGenero()),
  handleSubmitCreate: usuarioId => e => {
    e.preventDefault()

    const serie = {
      nome: e.target.nome.value,
      detalhes: e.target.detalhe.value,
      anoLancamento: e.target.ano.value
    }

    dispatch(createSerie(usuarioId, serie))
    window.$('#modalCreateSerie').modal('hide')
  },
  handleDeleteSerie: (usuarioId, id) => e => {
    e.preventDefault()
    dispatch(deleteSerie(usuarioId, id))
  },
  handleEditSerie: serie => serieGenres => e => {
    e.preventDefault()
    const serieObj = {
      id: serie.id,
      usuarioId: serie.usuarioId,
      nome: e.target.nomeEdit.value,
      detalhes: e.target.detalhesEdit.value,
      anoLancamento: e.target.anoEdit.value,
      generos: serieGenres
    }
    dispatch(editSerie(serieObj))
    window.$(`#serie${serie.id}`).modal('hide')
  }
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Dashboard)
