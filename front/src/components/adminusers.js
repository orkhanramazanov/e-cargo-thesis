import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSignOutAlt,
  faUser,
  faFile,
  faFlag,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import axios from "../axiosinterceptor";
import { ToastContainer, toast } from "react-toastify";

class AdminUsers extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayClients: false,
      clientsRowCount: 0,
      userData: [],
    };

    this.toggleClients = this.toggleClients.bind(this);
    this.handleClients = this.handleClients.bind(this);
  }

  blockUser = async (id) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/auth/admin/deactivateUser/${id}`
      );

      if (response.status === 200) {
        toast("User Deactivated Successfully", {
          type: "success",
        });
      }
    } catch (err) {
      toast("Error occured while deactivating user", {
        type: "error",
      });
    }

    this.fetchUsers();
  };

  activateUser = async (id) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/auth/admin/activateUser/${id}`
      );

      if (response.status === 200) {
        toast("User Activated Successfully", {
          type: "success",
        });
      }
    } catch (err) {
      toast("Error occured while activating user", {
        type: "error",
      });
    }

    this.fetchUsers();
  };

  handleClients = ({ dataSize }) => {
    this.setState({ clientsRowCount: dataSize });
  };

  toggleClients() {
    this.setState({
      displayClients: !this.state.displayClients,
    });
  }

  componentDidMount() {
    this.fetchUsers();
  }

  fetchUsers = async () => {
    const response = await axios.get(
      `http://localhost:8080/auth/admin/getAllUser`
    );

    if (response.status === 200) {
      const users = [];
      response.data.map((User) => {
        const user = {
          id: User.id,
          phone: User.phone,
          email: User.email,
          firstname: User.firstname,
          surname: User.surname,
          country: User.country,
          role: User.role,
          status: User.status,
          block: (
            <Button
              onClick={() => this.blockUser(User.id)}
              className="btn btn-danger"
            >
              Deactivate
            </Button>
          ),
          activate: (
            <Button
              onClick={() => this.activateUser(User.id)}
              className="btn btn-success"
            >
              Activate
            </Button>
          ),
        };

        users.push(user);
      });

      this.setState({
        userData: users,
        clientsRowCount: response.data.length,
      });
    } else {
      toast("Error occured while fetching data", {
        type: "error",
      });
    }
  };

  render() {
    const columns = [
      {
        dataField: "id",
        text: "User ID",
        filter: textFilter(),
      },
      {
        dataField: "firstname",
        text: "First Name",
        filter: textFilter(),
      },
      {
        dataField: "surname",
        text: "SurName",
        filter: textFilter(),
      },
      {
        dataField: "email",
        text: "Email",
        filter: textFilter(),
      },
      {
        dataField: "phone",
        text: "Phone",
        filter: textFilter(),
      },
      {
        dataField: "role",
        text: "Role",
        filter: textFilter(),
      },
      {
        dataField: "country",
        text: "Country",
        filter: textFilter(),
      },
      {
        dataField: "status",
        text: "Status",
        filter: textFilter(),
      },
      {
        dataField: "block",
        text: "Deactivate",
      },
      {
        dataField: "activate",
        text: "Activate",
      },
    ];

    return (
      <React.Fragment>
        <Header></Header>
        <div
          style={{
            background: "#F5F5F5",
            width: "100%",
            height: "210vh",
            minHeight: "210vh",
          }}
        >
          <br />
          <div className="container">
            <div className="row">
              <div
                className="col-lg-3"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                }}
              >
                <p style={{ padding: "10px" }}>
                  <b>{sessionStorage.getItem("fname")}'s Account</b>{" "}
                  <span>{sessionStorage.getItem("email")}</span>
                </p>

                <hr style={{ borderTop: "3px solid #991A00" }} />

                <div>
                  <div
                    style={{
                      padding: "10px",
                      borderWidth: "1px",
                      borderLeft: "7px solid #991A00 ",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                        color: "#991A00",
                      }}
                      icon={faFile}
                    />
                    <Link style={{ color: "black" }} to="/admin/users">
                      <b>View Users</b>
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                      borderWidth: "1px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                      }}
                      icon={faFlag}
                    />
                    <Link style={{ color: "black" }} to="/admin/countries">
                      Edit Countries
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                      borderWidth: "1px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                      }}
                      icon={faFile}
                    />
                    <Link style={{ color: "black" }} to="/admin/orders">
                      View Orders
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                      }}
                      icon={faUser}
                    />

                    <Link style={{ color: "black" }} to="/admin/account">
                      {" "}
                      My data
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faSignOutAlt}
                    />
                    <Link style={{ color: "black" }} to="/admin/login">
                      {" "}
                      Log out
                    </Link>
                  </div>
                </div>
              </div>
              <hr />
              <div
                className="col-lg-8"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                }}
              >
                <p style={{ padding: "10px", marginTop: "25px" }}>
                  <b>View Users</b>{" "}
                </p>
                <hr />

                <div className="col-lg-12">
                  <Button
                    style={{
                      backgroundColor: "white",
                      borderColor: "white",
                      color: "black",
                    }}
                  >
                    <b onClick={this.toggleClients}>View Users</b>
                  </Button>
                  <div
                    id="active"
                    style={{
                      display: this.state.displayClients ? "block" : "none",
                      overflowY: "scroll",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleClients}
                      keyField="id"
                      data={this.state.userData}
                      columns={columns}
                      filter={filterFactory()}
                      pagination={paginationFactory()}
                    />
                  </div>
                </div>

                <br />
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default AdminUsers;
