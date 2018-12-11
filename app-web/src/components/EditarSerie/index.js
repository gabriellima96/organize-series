import React, { Fragment, PureComponent } from 'react'

import './styles.css'

class EditarSerie extends PureComponent {
  constructor (props) {
    super(props)

    this.state = {
      listGenerosEdit: []
    }
    this.select = React.createRef()
    this.handleAddGenre = this.handleAddGenre.bind(this)
  }

  handleAddGenre (e) {
    e.preventDefault()
    const id = this.select.current.value
    const generos = this.state.listGenerosEdit

    const isValid = generos.filter(genero => +genero.id === +id)

    if (!isValid.length) {
      const genero = this.props.generos.filter(genero => +genero.id === +id)
      let { listGenerosEdit } = this.state
      listGenerosEdit = [...listGenerosEdit, ...genero]
      this.setState({ listGenerosEdit })
    }
  }

  componentDidMount () {
    const { generos } = this.props.serie
    this.setState({ listGenerosEdit: [...generos] })
  }

  render () {
    const { serie, generos, handleEditSerie } = this.props
    const { listGenerosEdit } = this.state

    return (
      <Fragment>
        <button
          type="button"
          className="btn btn-outline-warning ml-2"
          data-toggle="modal"
          data-target={`#serie${serie.id}`}
        >
          <i className="fa fa-edit" /> Editar
        </button>
        <div
          className="modal fade"
          id={`serie${serie.id}`}
          tabIndex="-1"
          role="dialog"
          aria-labelledby="modalLabel"
          aria-hidden="false"
        >
          <div className="modal-dialog " role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="modalLabel">
                  ATUALIZAR SERIE
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
              <form onSubmit={e => handleEditSerie(listGenerosEdit)(e)}>
                <div className="modal-body">
                  <div className="form-group">
                    <label htmlFor="name">Nome da Serie</label>
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      name="nomeEdit"
                      defaultValue={serie.nome}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="details">Detalhes</label>
                    <input
                      type="text"
                      className="form-control"
                      id="details"
                      name="detalhesEdit"
                      defaultValue={serie.detalhes}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="year">Ano de lançamento</label>
                    <input
                      type="number"
                      className="form-control"
                      id="year"
                      name="anoEdit"
                      defaultValue={serie.anoLancamento}
                    />
                  </div>
                  <div className="input-group">
                    <select
                      className="custom-select"
                      id="genero"
                      name="selectGeneros"
                      ref={this.select}
                    >
                      {generos.map(genero => (
                        <option key={genero.id} value={genero.id}>
                          {genero.nome}
                        </option>
                      ))}
                    </select>
                    <div className="input-group-prepend">
                      <button
                        className="btn btn-outline-secondary"
                        type="button"
                        onClick={e => this.handleAddGenre(e)}
                      >
                        Adicionar
                      </button>
                    </div>
                  </div>
                  <div>
                    {listGenerosEdit.length > 0 &&
                      listGenerosEdit.map(genero => (
                        <div className="form-inline" key={genero.id}>
                          <div className="form-group mb-2">
                            <p>{genero.nome}</p>
                            <button
                              onClick={e =>
                                this.setState({
                                  listGenerosEdit: this.state.listGenerosEdit.filter(
                                    genr => genr.id !== genero.id
                                  )
                                })
                              }
                              className="btn btn-danger btn-sm"
                            >
                              Remove
                            </button>
                          </div>
                        </div>
                      ))}
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
                  <button type="submit" className="btn btn-primary btn-custom">
                    Salvar
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </Fragment>
    )
  }
}

export default EditarSerie
