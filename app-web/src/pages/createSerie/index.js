import React, { Fragment } from 'react'
import NavBar from '../../components/NavBar'

const CreateSeries = () => (
  <Fragment>
    <NavBar />
    <div id='main' className='container-fluid'>
      <h3 className='pb-2 mt-4 mb-2 ml-4 border-bottom'>Adicionar Serie</h3>
      <form action='index.html'>
        <div className='form-group col-md-4'>
          <label htmlFor='name'>Nome</label>
          <input type='text' className='form-control' id='name' />
        </div>

        <div className='form-group col-md-4'>
          <label htmlFor='details'>Detalhes</label>
          <input type='text' className='form-control' id='details' />
        </div>

        <div className='form-group col-md-4'>
          <label htmlFor='year'>Ano de lançamento</label>
          <input type='text' className='form-control' id='year' />
        </div>

        <hr />
        <div id='actions' className='row'>
          <div className='col-md-12'>
            <button type='submit' className='btn btn-primary'>
              Salvar
            </button>
            <a href='index.html' className='btn btn-default'>
              Cancelar
            </a>
          </div>
        </div>
      </form>
    </div>
  </Fragment>
)

export default CreateSeries
