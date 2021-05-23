import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { ToastContainer, toast } from "react-toastify";
import axios from "../axiosinterceptor";
import { Link } from "react-router-dom";
class DriverRequest extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      trucktype: "",
      freespace: "",
      priceperkg: "",
      departurelocation: "",
      arrivallocation: "",
      departuretime: "",
      arrivaltime: "",
      truckphoto: "",
      truckName: "",
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
  componentDidMount() {
    var now = new Date();
    this.fetchAllCountriesInDB();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    this.setState({
      minDate: now.toISOString().slice(0, 16),
    });
  }

  handleForm = (e) => {
    e.preventDefault();

    if (e.target.name === "priceperkg" || e.target.name === "freespace") {
      if (e.target.value <= 0) {
        toast("Field cannot be nagtive , setting default to 0", {
          type: "error",
        });
        this.setState({
          [e.target.name]: 0,
        });
      } else {
        this.setState({
          [e.target.name]: e.target.value,
        });
      }
    } else {
      this.setState({
        [e.target.name]: e.target.value,
      });
    }
  };

  handleTruckPhoto = (e) => {
    e.preventDefault();
    let file = e.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.setState({
        truckphoto: reader.result,
      });
    };
  };

  submitForm = async (e) => {
    e.preventDefault();
    if (
      this.state.trucktype === "" ||
      this.state.freespace <= 0 ||
      this.state.priceperkg <= 0 ||
      this.state.departurelocation === "" ||
      this.state.arrivallocation === "" ||
      this.state.departuretime === "" ||
      this.state.truckphoto === "" ||
      this.state.truckName === "" ||
      this.state.arrivaltime === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      const body = {
        truckType: this.state.trucktype,
        freeSpace: this.state.freespace,
        pricePerKg: this.state.priceperkg,
        departureLocation: this.state.departurelocation,
        arrivalLocation: this.state.arrivallocation,
        departureTime: this.state.departuretime,
        arrivalTime: this.state.arrivaltime,
        truckName: this.state.truckName,
        photo: this.state.truckphoto,
      };

      try {
        const response = await axios.post(
          `http://localhost:8080/auth/driverCreateOrder/${sessionStorage.getItem(
            "id"
          )}`,
          body
        );

        if (response.status === 200) {
          toast("Request Created", {
            type: "success",
          });
        } else {
          toast("Route Already Created", {
            type: "error",
          });
        }
      } catch (err) {
        toast("Route Already Created", {
          type: "error",
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
            height: "100vh",
            minHeight: "100vh",
          }}
        >
          <br />
          <div
            style={{
              background: "white",
              marginTop: "20px",
              borderRadius: "5px",
            }}
            className="container"
          >
            <div className="row justify-content-center">
              <div className="col-lg-12" style={{ padding: "10px" }}>
                <form className="form-horizontal">
                  <div className="form-group">
                    <div className="col-lg-4">
                      <h4>Add new route</h4>
                      <label>
                        <b>Truck Type</b>
                      </label>
                      <br />
                      <select
                        style={{
                          borderRadius: "2px",
                          padding: "10px",
                          background: "white",
                        }}
                        name="trucktype"
                        onChange={this.handleForm}
                      >
                        <option value="">Select Truck</option>
                        <option value="SEMITRAILER">SEMITRAILER</option>
                        <option value="REFREGIRATOR">REFREGIRATOR</option>
                        <option value="ISOTHERM">ISOTHERM</option>
                        <option value="TAILLIFT">TAILLIFT</option>
                        <option value="JUMBO">JUMBO</option>
                      </select>
                    </div>
                  </div>

                  <div className="col-lg-4">
                    <label>
                      <b>Truck Name</b>
                    </label>
                    <br />
                    <input
                      type="text"
                      className="form-control input-group-lg"
                      placeholder="Truck Name"
                      name="truckName"
                      aria-label="truckName"
                      aria-describedby="basic-addon"
                      onChange={this.handleForm}
                    />
                  </div>
                  <br />
                  <div className="col-lg-4">
                    <label>
                      <b>Truck Photo</b>
                    </label>

                    <input
                      type="file"
                      accept="image/*"
                      style={{ padding: "3px" }}
                      className="form-control input-group-lg"
                      name="truckphoto"
                      aria-label="truckphoto"
                      aria-describedby="basic-addon"
                      onChange={this.handleTruckPhoto}
                    />
                  </div>
                  <br />

                  <div className="input-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Price Per Kg</b>
                      </label>

                      <input
                        type="number"
                        className="form-control input-group-lg"
                        placeholder="Price"
                        name="priceperkg"
                        aria-label="priceperkg"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4">
                      <label>
                        <b>Free Space</b>
                      </label>

                      <input
                        type="number"
                        className="form-control form-control input-group-lg"
                        placeholder="freespace"
                        name="freespace"
                        aria-label="freespace"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                  <br />

                  <div className="input-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Departure Location</b>
                      </label>
                      <br />
                      <select
                        style={{
                          borderRadius: "2px",
                          padding: "10px",
                          background: "white",
                        }}
                        name="departurelocation"
                        onChange={this.handleForm}
                      >
                        <option value="">Select Country</option>
                        {this.state.options}
                      </select>
                    </div>
                    <div className="col-lg-4">
                      <label>
                        <b>Departure Time</b>
                      </label>

                      <input
                        type="datetime-local"
                        min={this.state.minDate}
                        className="form-control"
                        aria-label="departuretime"
                        name="departuretime"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                  <br />
                  <div className="form-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Arrival Location</b>
                      </label>
                      <br />

                      <select
                        style={{
                          borderRadius: "2px",
                          padding: "10px",
                          background: "white",
                        }}
                        name="arrivallocation"
                        onChange={this.handleForm}
                      >
                        <option value="">Select Country</option>
                        {this.state.options}
                      </select>
                    </div>
                  </div>

                  <div className="form-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Arrival Time</b>
                      </label>
                      <br />
                      <input
                        type="datetime-local"
                        className="form-control"
                        min={this.state.departuretime}
                        aria-label="arrivaltime"
                        name="arrivaltime"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <div className="form-group">
                    <Button
                      onClick={this.submitForm}
                      style={{
                        background: "red",
                        border: "none",
                        marginLeft: "20px",
                        marginTop: "30px",
                      }}
                    >
                      Add Route
                    </Button>

                    <Link to="/driver/account">
                      <Button
                        style={{
                          background: "green",
                          border: "none",
                          marginLeft: "20px",
                          marginTop: "30px",
                        }}
                      >
                        {" "}
                        Go Back
                      </Button>
                    </Link>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default DriverRequest;
