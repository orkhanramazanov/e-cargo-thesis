import { Button } from "react-bootstrap";
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSignOutAlt,
  faDatabase,
  faUser,
  faFile,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import Header from "./header";
import axios from "../axiosinterceptor";
import { ToastContainer, toast } from "react-toastify";

class DriverTrucks extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayActiveTrucks: false,
      activeTrucksRowCount: 0,
      truckData: [],
      truckphoto: "",
    };

    this.toggleActiveTrucks = this.toggleActiveTrucks.bind(this);
  }

  updatePhoto = async (id) => {
    if (this.state.truckphoto !== "") {
      try {
        const response = await axios.post(
          `http://localhost:8080/auth/driver/updateTruckPhoto`,
          { id: id, photo: this.state.truckphoto }
        );

        if (response.status === 200) {
          toast("Photo Updated Successfully", {
            type: "success",
          });
        }
      } catch (err) {
        toast("Error occured while updating photo", {
          type: "error",
        });
      }
    } else {
      toast("Select an image first", {
        type: "error",
      });
    }

    this.fetchTrucks();
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

  fetchTrucks = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/auth/driver/getTrucksByDriverId/${sessionStorage.getItem(
          "id"
        )}`
      );

      if (response.status === 200) {
        const trucks = [];
        response.data.map((Truck) => {
          const truck = {
            id: Truck.id,
            name: Truck.truckName,
            departureDate: Truck.departureTime,
            departureLocation: Truck.departureLocation,
            arrivalDate: Truck.arrivalTime,
            arrivalLocation: Truck.arrivalLocation,
            freeSpace: Truck.freeSpace,
            truckType: Truck.truckType,
            pricePerKg: Truck.pricePerKg,
            photo: (
              <img
                src={`${Truck.photo}`}
                alt="truck"
                style={{ height: "120px", width: "auto" }}
              />
            ),
            action: (
              <div>
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
                <Button
                  onClick={() => this.updatePhoto(Truck.id)}
                  className="btn btn-danger"
                >
                  Update Photo
                </Button>
              </div>
            ),
          };

          trucks.push(truck);
        });

        this.setState({
          truckData: trucks,
          activeTrucksRowCount: response.data.length,
        });
      }
    } catch (err) {
      toast("Error occured while fetching data", {
        type: "error",
      });
    }
  };

  componentDidMount() {
    this.fetchTrucks();
  }

  handleActiveTrucks = ({ dataSize }) => {
    this.setState({ activeTrucksRowCount: dataSize });
  };

  toggleActiveTrucks() {
    this.setState({
      displayActiveTrucks: !this.state.displayActiveTrucks,
    });
  }

  render() {
    const columns = [
      {
        dataField: "id",
        text: "Truck ID",
      },
      {
        dataField: "name",
        text: "Truck Name",
      },

      {
        dataField: "departureDate",
        text: "Departure Date",
      },
      {
        dataField: "departureLocation",
        text: "Departure Location",
      },
      {
        dataField: "arrivalDate",
        text: "Arrival Date",
      },
      {
        dataField: "arrivalLocation",
        text: "Arrival Location",
      },
      {
        dataField: "freeSpace",
        text: "Free Space",
      },
      {
        dataField: "truckType",
        text: "Truck Type",
      },
      {
        dataField: "pricePerKg",
        text: "Price / kg",
      },
      {
        dataField: "photo",
        text: "Truck Image",
      },

      {
        dataField: "action",
        text: "Action",
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
                className="col-lg-4"
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
                    <Link style={{ color: "black" }} to="/driver/reservations">
                      <b>My Orders</b>
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

                    <Link style={{ color: "black" }} to="/driver/account">
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
                      icon={faDatabase}
                    />
                    <Link style={{ color: "black" }} to="/driver/billing">
                      {" "}
                      Billing
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                      borderLeft: "7px solid #991A00 ",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                        color: "#991A00",
                      }}
                      icon={faDatabase}
                    />
                    <Link style={{ color: "black" }} to="/driver/trucks">
                      {" "}
                      <b>My Trucks</b>
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
                    <Link style={{ color: "black" }} to="/logout">
                      {" "}
                      Log out
                    </Link>
                  </div>
                </div>
              </div>
              <hr />
              <div
                className="col-lg-7"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                  overflow: "scroll",
                }}
              >
                <p style={{ padding: "10px", marginTop: "25px" }}>
                  <b>All Trucks</b>{" "}
                  <span>
                    <Button
                      style={{
                        float: "right",
                        backgroundColor: "#991A00",
                        borderColor: "#991A00",
                      }}
                    >
                      <Link style={{ color: "white" }} to="/driver/request">
                        New Request
                      </Link>
                    </Button>
                  </span>
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
                    <b onClick={this.toggleActiveTrucks}>
                      Active Trucks ({this.state.activeTrucksRowCount})
                    </b>
                  </Button>
                  <div
                    id="active"
                    style={{
                      display: this.state.displayActiveTrucks
                        ? "block"
                        : "none",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleActiveTrucks}
                      keyField="id"
                      data={this.state.truckData}
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

export default DriverTrucks;
