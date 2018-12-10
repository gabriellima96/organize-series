import React, { Fragment } from 'react'
import NavBar from '../../components/NavBar'
import ModalCreateSeries from '../../components/ModalCreateSeries'

const Dashboard = () => (
  <Fragment>
    <NavBar />
    <div className="jumbotron">
      <div className="container">
        <div className="row">
          <div className="col-2">
            <h1 className="display-5">Series</h1>
          </div>
          <div className="col-3">
            <ModalCreateSeries />
          </div>
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
              <tr key="1">
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>
                  <a className="btn btn-outline-primary" href="edit.html">
                    Temporadas
                  </a>
                  <a className="btn btn-outline-warning ml-2" href="edit.html">
                    Editar
                  </a>
                  <a className="btn btn-outline-danger ml-2" href="edit.html">
                    Excluir
                  </a>
                </td>
              </tr>
              <tr key="2">
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>@mdo</td>
                <td>
                  <a className="btn btn-outline-primary" href="edit.html">
                    Temporadas
                  </a>
                  <a className="btn btn-outline-warning ml-2" href="edit.html">
                    Editar
                  </a>
                  <a className="btn btn-outline-danger ml-2" href="edit.html">
                    Excluir
                  </a>
                </td>
              </tr>
              <tr key="3">
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
                <td>@mdo</td>
                <td>
                  <a className="btn btn-outline-primary" href="edit.html">
                    Temporadas
                  </a>
                  <a className="btn btn-outline-warning ml-2" href="edit.html">
                    Editar
                  </a>
                  <a className="btn btn-outline-danger ml-2" href="edit.html">
                    Excluir
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </Fragment>
)

export default Dashboard
