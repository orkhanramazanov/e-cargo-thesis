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
import { ToastContainer, toast } from "react-toastify";
import axios from "../axiosinterceptor";

class AdminCountry extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayCountries: false,
      countriesRowCount: 0,
      country: "",
      countries: [],
      countriesDb: [],
    };

    this.toggleCountries = this.toggleCountries.bind(this);
    this.handleCountries = this.handleCountries.bind(this);
  }

  deleteCountry = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/auth/country/deleteById/${id}`
      );

      if (response.status === 200) {
        toast("Country Deleted Successfully", {
          type: "success",
        });
      }
    } catch (err) {
      toast("Error occured while deleting country", {
        type: "error",
      });
    }

    this.fetchAllCountriesInDB();
  };

  fetchAllCountriesInDB = async () => {
    const response = await axios.get(
      "http://localhost:8080/auth/country/getAll"
    );
    if (response.data) {
      const AllCountries = [];
      response.data.map((obj) => {
        const country = {
          id: obj.id,
          name: obj.name,
          remove: (
            <Button
              className="btn btn-danger"
              onClick={() => this.deleteCountry(obj.id)}
            >
              Remove
            </Button>
          ),
        };
        AllCountries.push(country);
      });
      this.setState({
        countriesDb: AllCountries,
      });
    }
  };

  fetchAllCountries = () => {
    fetch("https://restcountries.eu/rest/v2/all")
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          const countries = data.map((obj) => obj.name);
          this.setState({
            countries: countries,
          });
        }
      });
  };

  componentDidMount() {
    this.fetchAllCountries();
    this.fetchAllCountriesInDB();
  }

  handleCountries = ({ dataSize }) => {
    this.setState({ countriesRowCount: dataSize });
  };

  handleCountry = (e) => {
    e.preventDefault();
    this.setState({
      country: e.target.value,
    });
  };

  addCountry = async (e) => {
    e.preventDefault();
    if (this.state.country === "") {
      toast("Please select a country from list", {
        type: "error",
      });
    } else {
      try {
        const response = await axios.post(
          `http://localhost:8080/auth/country/addCountry`,
          { countryName: this.state.country }
        );

        if (response.status === 200) {
          toast("Country Added Successfully", {
            type: "success",
          });
        }
      } catch (err) {
        toast("Country Already Exists in DB", {
          type: "error",
        });
      }

      this.fetchAllCountriesInDB();
    }
  };

  toggleCountries() {
    this.setState({
      displayCountries: !this.state.displayCountries,
    });
  }

  render() {
    const columns = [
      {
        dataField: "id",
        text: "Country ID",
        filter: textFilter(),
      },
      {
        dataField: "name",
        text: "Country Name",
        filter: textFilter(),
      },

      {
        dataField: "remove",
        text: "Remove",
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
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                      }}
                      icon={faFile}
                    />
                    <Link style={{ color: "black" }} to="/admin/users">
                      View Users
                    </Link>
                  </div>
                  <hr />
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
                      icon={faFlag}
                    />
                    <Link style={{ color: "black" }} to="/admin/countries">
                      <b>Edit Countries</b>
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
                  <b>Countries</b>{" "}
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
                    <b onClick={this.toggleCountries}>
                      View All Countries In DB
                    </b>
                  </Button>
                  <div
                    id="active"
                    style={{
                      display: this.state.displayCountries ? "block" : "none",
                      overflowY: "scroll",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleCountries}
                      keyField="id"
                      data={this.state.countriesDb}
                      columns={columns}
                      filter={filterFactory()}
                      pagination={paginationFactory()}
                    />
                  </div>
                </div>

                <br />
                <div className="col-lg-12">
                  <div>
                    <form>
                      <div className="form-group col-lg-12">
                        <p>
                          <b>Select a country from list to add it your DB</b>
                        </p>
                        <select
                          style={{
                            padding: "10px",
                            borderRadius: "5px",
                            background: "white",
                          }}
                          name="countries"
                          onChange={this.handleCountry}
                        >
                          <option value="">Select Country</option>
                          {this.state.countries.map((c) => (
                            <option key={c} value={c}>
                              {c}
                            </option>
                          ))}
                        </select>
                        <br />
                        <br />
                        <Button
                          className="btn btn-primary"
                          onClick={this.addCountry}
                        >
                          Add Country
                        </Button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default AdminCountry;
