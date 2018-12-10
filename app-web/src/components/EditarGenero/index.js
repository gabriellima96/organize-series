import React, { Fragment } from 'react'
import PropTypes from 'prop-types'

import './styles.css'

const EditarGenero = ({ nome, handleEditGenero, isLoading }) => (
  <Fragment>
    <button
      type="button"
      className="btn btn-outline-warning ml-2"
      data-toggle="modal"
      data-target={`#${nome}`}
    >
      <i className="fa fa-edit" /> Editar
    </button>

    <div
      className="modal fade"
      id={nome}
      tabIndex="-1"
      role="dialog"
      aria-labelledby="modalLabel"
      aria-hidden="false"
    >
      <div className="modal-dialog " role="document">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="modalLabel">
              ATUALIZAR GÊNERO
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
          <form onSubmit={handleEditGenero}>
            <div className="modal-body">
              <div className="form-group">
                <label htmlFor="name">Nome do Gênero</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  placeholder="Terror, Crime, Romance..."
                  name="nomeEdit"
                  defaultValue={nome}
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

EditarGenero.propTypes = {
  nome: PropTypes.string.isRequired,
  handleEditGenero: PropTypes.func.isRequired,
  isLoading: PropTypes.bool.isRequired
}

export default EditarGenero
