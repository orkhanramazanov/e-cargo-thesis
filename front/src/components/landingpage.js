import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarker, faSearch } from "@fortawesome/free-solid-svg-icons";
import Header from "./header";
import { Button } from "react-bootstrap";
import { ToastContainer, toast } from "react-toastify";
import axios from "../axiosinterceptor";
import { Link } from "react-router-dom";

class LandingPage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      trucktypes: [],
      pickuptime: "",
      pickuplocation: "",
      dropofftime: "",
      dropofflocation: "",
      truckData: [],
      minDate: "",
      countryDb: [],
      options: [],
    };
  }

  fetchAllCountriesInDB = async () => {
    const response = await axios.get(
      "http://localhost:8080/auth/country/getAll"
    );

    this.setState({
      countryDb: response.data,
    });
    const ss = [];
    this.state.countryDb.map((country) => {
      const s = (
        <option key={country.id} value={country.name}>
          {country.name}
        </option>
      );

      ss.push(s);
    });

    this.setState({
      options: ss,
    });
  };

  getAllTrucks = async () => {
    const response = await axios.get(
      "http://localhost:8080/auth/trucks/getAllTrucks"
    );

    this.setState({
      truckData: response.data,
    });
  };

  componentDidMount() {
    this.fetchAllCountriesInDB();
    this.getAllTrucks();

    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    this.setState({
      minDate: now.toISOString().slice(0, 16),
    });
  }

  handleSearch = (e) => {
    e.preventDefault();
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  submitSearch = async (e) => {
    e.preventDefault();
    if (
      this.state.pickuptime === "" ||
      this.state.pickuplocation === "" ||
      this.state.dropofflocation === "" ||
      this.state.dropofftime === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      toast("Searching", {
        type: "success",
      });

      const body = {
        pickUpLocation: this.state.pickuplocation,
        pickUpTime: this.state.pickuptime,
        dropOffLocation: this.state.dropofflocation,
        dropOffTime: this.state.dropofftime,
      };

      const response = await axios.post(
        "http://localhost:8080/auth/trucks/FilterTruck",
        body
      );

      if (response.status === 200) {
        this.setState({
          truckData: response.data,
        });
      }
    }
  };

  filteredSearch = async (e) => {
    e.preventDefault();
    const body = {
      types: this.state.trucktypes,
    };

    const response = await axios.post(
      "http://localhost:8080/auth/trucks/getAllTrucksByTypes",
      body
    );

    if (response.status === 200) {
      this.setState({
        truckData: response.data,
      });
    }
  };

  filterSearch = (e) => {
    e.preventDefault();
    if (e.target.checked) {
      const trucks = this.state.trucktypes;
      if (!trucks.includes(e.target.value)) {
        trucks.push(e.target.value);
        this.setState({
          trucktypes: trucks,
        });
      }
    } else if (!e.target.checked) {
      const trucks = this.state.trucktypes;
      if (trucks.includes(e.target.value)) {
        var filteredAry = trucks.filter(function (s) {
          return s !== e.target.value;
        });
        this.setState({
          trucktypes: filteredAry,
        });
      }
    }
  };

  render() {
    return (
      <React.Fragment>
        <Header></Header>
        <div
          style={{
            background: "#F5F5F5",
            width: "100%",
            height: "120vh",
            minHeight: "120vh",
          }}
        >
          <div className="container">
            <div
              className="row"
              style={{ marginLeft: "15px", marginRight: "15px" }}
            >
              <br />
              <div
                style={{
                  background: "white",
                  borderRadius: "5px",
                  paddingTop: "20px",
                  paddingBottom: "20px",
                }}
              >
                <div className="justify-content-center">
                  <form>
                    <div className="input-group">
                      <div className="col-sm-2 col-md-3 align-self-start">
                        <label>
                          <b>Pickup Location</b>
                        </label>

                        <div className="input-group-prepend">
                          <select
                            name="pickuplocation"
                            style={{ padding: "10px" }}
                            onChange={this.handleSearch}
                          >
                            <option value="">Select Location</option>

                            {this.state.options}
                          </select>
                          <span
                            className="input-group-text"
                            style={{ backgroundColor: "white" }}
                            id="basic-addon"
                          >
                            <FontAwesomeIcon
                              style={{ borderRight: "none" }}
                              icon={faMapMarker}
                            />
                          </span>
                        </div>
                      </div>
                      <div className="col-sm-2 col-md-3 align-self-start">
                        <label>
                          <b>Pickup Time</b>
                        </label>

                        <div className="input-group-prepend">
                          <input
                            type="datetime-local"
                            className="form-control"
                            min={this.state.minDate}
                            name="pickuptime"
                            aria-label="pickuptime"
                            aria-describedby="basic-addon"
                            onChange={this.handleSearch}
                          />
                        </div>
                      </div>

                      <div className="col-sm-2 col-md-3 align-self-start">
                        <label>
                          <b>Dropoff Location</b>
                        </label>

                        <div className="input-group-prepend">
                          <select
                            name="dropofflocation"
                            style={{ padding: "10px" }}
                            onChange={this.handleSearch}
                          >
                            <option value="">Select Location</option>
                            {this.state.options}
                          </select>
                          <span
                            className="input-group-text"
                            style={{ backgroundColor: "white" }}
                            id="basic-addon"
                          >
                            <FontAwesomeIcon
                              style={{ borderRight: "none" }}
                              icon={faMapMarker}
                            />
                          </span>
                        </div>
                      </div>
                      <div className="col-sm-2 col-md-3 align-self-start">
                        <label>
                          <b>Dropoff Time</b>
                        </label>

                        <div className="input-group-prepend">
                          <input
                            type="datetime-local"
                            className="form-control"
                            aria-label="dropofftime"
                            min={this.state.pickuptime}
                            name="dropofftime"
                            aria-describedby="basic-addon"
                            onChange={this.handleSearch}
                          />
                        </div>
                      </div>

                      <div className="col-sm-12 col-md-12 align-self-start">
                        <center>
                          <Button
                            onClick={this.submitSearch}
                            style={{
                              background: "red",
                              border: "none",
                              marginLeft: "20px",
                              marginTop: "30px",
                            }}
                          >
                            Search <FontAwesomeIcon icon={faSearch} />
                          </Button>
                          <Button
                            onClick={this.getAllTrucks}
                            style={{
                              background: "green",
                              border: "none",
                              marginLeft: "20px",
                              marginTop: "30px",
                            }}
                          >
                            Clear Filters
                          </Button>
                        </center>
                      </div>
                    </div>
                  </form>
                </div>
              </div>

              <div
                className="col-lg-4"
                style={{
                  height: "fit-content",
                  background: "white",
                  marginTop: "10px",
                }}
              >
                <div className="row ">
                  <form className="form-group">
                    <label>
                      <b>Truck type</b>
                    </label>
                    <div className="col-lg-12 justify-content-center  form-group">
                      <input
                        type="checkbox"
                        value="ISOTHERM"
                        onChange={this.filterSearch}
                      />{" "}
                      {"   "}
                      <b>ISOTHERM</b>
                    </div>
                    <div className="col-lg-12 justify-content-center  form-group">
                      <input
                        type="checkbox"
                        value="SEMITRAILER"
                        onChange={this.filterSearch}
                      />{" "}
                      {"   "}
                      <b>SEMITRAILER</b>
                    </div>
                    <div className="col-lg-12 justify-content-center  form-group">
                      <input
                        type="checkbox"
                        value="TAILLIFT"
                        onChange={this.filterSearch}
                      />{" "}
                      {"   "}
                      <b>TAILLIFT</b>
                    </div>
                    <div className="col-lg-12 justify-content-center  form-group">
                      <input
                        type="checkbox"
                        value="JUMBO"
                        onChange={this.filterSearch}
                      />{" "}
                      {"   "}
                      <b>JUMBO</b>
                    </div>
                    <div className="col-lg-12 justify-content-center  form-group">
                      <input
                        type="checkbox"
                        value="REFRIGIRATOR"
                        onChange={this.filterSearch}
                      />{" "}
                      {"   "}
                      <b>REFRIGIRATOR</b>
                    </div>

                    <Button
                      style={{
                        background: "black",
                        border: "none",
                        marginLeft: "20px",
                        marginTop: "30px",
                      }}
                      onClick={this.filteredSearch}
                    >
                      Filter
                    </Button>
                  </form>
                </div>
              </div>

              <div
                className=" col-lg-8"
                style={{
                  marginTop: "10px",
                  borderRadius: "5px",
                  maxHeight: "600px",
                  overflowY: "scroll",
                }}
              >
                <div className="" style={{ paddingBottom: "20px" }}>
                  <div
                    className="col-lg-12"
                    style={{
                      padding: "10px",
                      marginBottom: "10px",
                      background: "white",
                    }}
                  >
                    <p>
                      <b>{this.state.truckData.length} trucks</b> found based on
                      your conditons
                    </p>
                  </div>

                  {this.state.truckData.map((truck) => (
                    <div key={truck.id} className="col-lg-12">
                      <div
                        className="row"
                        style={{
                          background: "white",
                          paddingBottom: "20px",
                          padding: "10px",
                        }}
                      >
                        <div
                          className="col-lg-3"
                          style={{
                            border: "1px solid #E0E0E0",
                            background: "white",
                            padding: "0px",
                          }}
                        >
                          <img
                            alt="truck"
                            style={{
                              height: "100%",
                              width: "100%",
                              padding: "10px",

                              objectFit: "cover",
                            }}
                            src={truck.photo}
                          />
                        </div>
                        <div
                          style={{
                            fontSize: "x-small",
                            fontFamily: "system-ui",
                          }}
                          className="col-lg-7"
                        >
                          <p>
                            <b>{truck.truckName}</b>
                          </p>
                          <p>
                            {" "}
                            <b>Pickup -- </b> &nbsp;
                            {truck.departureLocation}
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <b>Dropoff -- </b> {truck.arrivalLocation}
                          </p>
                          <p>
                            <b>Departure Time</b> {truck.departureTime}
                            &nbsp;&nbsp;
                            <b>Arrival Time</b> {truck.arrivalTime}&nbsp;
                          </p>{" "}
                          <p>
                            <b>Truck Type</b> {truck.truckType}{" "}
                          </p>
                          <p>
                            <b>Free Space</b> &nbsp; {truck.freeSpace} KG
                          </p>
                          <hr
                            style={{
                              backgroundColor: "grey",
                              height: 1,
                            }}
                          />
                        </div>

                        <div
                          className="col-lg-2"
                          style={{ background: "#E0E0E0", padding: "10px" }}
                        >
                          <center>
                            <p
                              style={{ fontSize: "0.8rem", fontStyle: "bold" }}
                            >
                              Price per kg
                              <br />
                              <b>${truck.pricePerKg} </b>
                            </p>
                            <Button
                              style={{
                                background: "red",
                                border: "none",
                                marginTop: "5px",
                              }}
                            >
                              <p
                                style={{
                                  fontSize: "0.8rem",
                                  paddingTop: "10px",
                                }}
                              >
                                <Link
                                  style={{ color: "white" }}
                                  to={`/newreservation/${truck.id}`}
                                >
                                  <b>Book Now</b>
                                </Link>
                              </p>
                            </Button>
                          </center>
                        </div>
                      </div>
                      <br />
                    </div>
                  ))}
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

export default LandingPage;
