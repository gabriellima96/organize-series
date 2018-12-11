import React, { Fragment } from 'react'
import PropTypes from 'prop-types'

const ModalCreateSeries = ({ handleSubmitCreate, isLoading }) => (
  <Fragment>
    <button
      type="button"
      className="btn btn-primary btn-lg"
      data-toggle="modal"
      data-target="#modalCreateSerie"
    >
      <i className="fa fa-plus" /> Adicionar
    </button>

    <div
      className="modal fade"
      id="modalCreateSerie"
      tabIndex="-1"
      role="dialog"
      aria-labelledby="modalLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog " role="document">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="modalLabel">
              Nova Serie
            </h5>
            <button
              type="button"
              className="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form onSubmit={handleSubmitCreate}>
            <div className="modal-body">
              <div className="form-group">
                <label htmlFor="name">Nome</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  name="nome"
                />
              </div>

              <div className="form-group">
                <label htmlFor="details">Detalhes</label>
                <input
                  type="text"
                  className="form-control"
                  id="details"
                  name="detalhe"
                />
              </div>

              <div className="form-group">
                <label htmlFor="year">Ano de lançamento</label>
                <input
                  type="number"
                  className="form-control"
                  id="year"
                  name="ano"
                />
              </div>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-dismiss="modal"
              >
                Cancelar
              </button>
              <button
                type="submit"
                className="btn btn-primary btn-custom"
                disabled={isLoading}
              >
                {!isLoading ? (
                  'Salvar'
                ) : (
                  <i className="fa fa-spinner fa-pulse" />
                )}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </Fragment>
)

ModalCreateSeries.propTypes = {
  handleSubmitCreate: PropTypes.func.isRequired,
  isLoading: PropTypes.bool.isRequired
}

export default ModalCreateSeries
