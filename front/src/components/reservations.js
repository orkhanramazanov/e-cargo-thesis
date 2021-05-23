import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSignOutAlt,
  faDatabase,
  faUser,
  faFile,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import axios from "../axiosinterceptor";
import { ToastContainer, toast } from "react-toastify";

class Reservations extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayActiveReservations: false,
      displayArchieveReservations: false,
      activeReservations: [],
      archieveReservations: [],
      activeReservationsRowCount: 0,
      archieveReservationsRowCount: 0,
    };

    this.toggleActiveReservations = this.toggleActiveReservations.bind(this);
    this.toggleArchieveReservations = this.toggleArchieveReservations.bind(
      this
    );
    this.handleActiveReservations = this.handleActiveReservations.bind(this);
    this.handleArchieveReservations = this.handleArchieveReservations.bind(
      this
    );
  }

  cancel = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/auth/client/cancelOrder/${id}`
      );

      if (response.status === 200) {
        this.fetchActiveReservations();
        this.fetchInActiveReservations();
        toast("Order Cancelled", {
          type: "success",
        });
      } else {
        toast("An error occured", {
          type: "error",
        });
      }
    } catch (err) {
      toast(err.response.data.message, {
        type: "error",
      });
    }
  };

  fetchActiveReservations = async () => {
    const response = await axios.get(
      `http://localhost:8080/auth/client/getActiveOrders/${sessionStorage.getItem(
        "id"
      )}`
    );

    if (response.status === 200) {
      const reservations = [];

      response.data.map((reservation) => {
        const date1 = new Date(reservation.departureTime);
        const hours = Math.abs(date1 - new Date()) / 36e5;

        const reserve = {
          id: reservation.id,
          weight: reservation.itemWeight,
          quantity: reservation.itemQuantity,
          length: reservation.itemLength,
          height: reservation.itemHeight,
          additionalInformation: (
            <textarea style={{ overflow: "scroll", maxHeight: "300px" }}>
              {reservation.additionalInformation}
            </textarea>
          ),
          itemName: reservation.itemName,
          itemCategory: reservation.itemCategory,
          itemWidth: reservation.itemWidth,
          status: reservation.status,
          totalPrice: reservation.totalPrice,
          updated: reservation.updated,
          cancel: (
            <Button
              style={{
                pointerEvents: hours <= 486 ? "all" : "none",
              }}
              onClick={() => this.cancel(reservation.id)}
              disabled={hours <= 486 ? false : true}
              className="btn btn-danger"
            >
              Cancel
            </Button>
          ),
          modify: (
            <Link
              style={{
                pointerEvents: hours <= 486 ? "all" : "none",
                backgroundColor: "none",
                borderColor: "none",
              }}
              to={`/modifyreservation/${reservation.id}/${reservation.truckId}`}
            >
              {" "}
              <Button
                style={{
                  pointerEvents: hours <= 486 ? "all" : "none",
                }}
                disabled={hours <= 486 ? false : true}
                className="btn btn-info"
              >
                Modify
              </Button>
            </Link>
          ),
        };

        reservations.push(reserve);
      });

      this.setState({
        activeReservationsRowCount: response.data.length,
        activeReservations: reservations,
      });
    }
  };

  fetchInActiveReservations = async () => {
    const response = await axios.get(
      `http://localhost:8080/auth/client/getInActiveOrders/${sessionStorage.getItem(
        "id"
      )}`
    );
    if (response.status === 200) {
      const reservations = [];

      response.data.map((reservation) => {
        const reserve = {
          id: reservation.id,
          weight: reservation.itemWeight,
          quantity: reservation.itemQuantity,
          length: reservation.itemLength,
          height: reservation.itemHeight,
          itemWidth: reservation.itemWidth,
          additionalInformation: (
            <textarea style={{ overflow: "scroll", maxHeight: "300px" }}>
              {reservation.additionalInformation}
            </textarea>
          ),
          status: reservation.status,
          itemName: reservation.itemName,
          itemCategory: reservation.itemCategory,
          totalPrice: reservation.totalPrice,
          updated: reservation.updated,
        };
        reservations.push(reserve);
      });

      this.setState({
        archieveReservationsRowCount: response.data.length,
        archieveReservations: reservations,
      });
    }
  };

  componentDidMount() {
    this.fetchActiveReservations();
    this.fetchInActiveReservations();
  }

  handleActiveReservations = ({ dataSize }) => {
    this.setState({ activeReservationsRowCount: dataSize });
  };
  handleArchieveReservations = ({ dataSize }) => {
    this.setState({ archieveReservationsRowCount: dataSize });
  };
  toggleActiveReservations() {
    this.setState({
      displayActiveReservations: !this.state.displayActiveReservations,
    });
  }

  toggleArchieveReservations() {
    this.setState({
      displayArchieveReservations: !this.state.displayArchieveReservations,
    });
  }

  render() {
    const columns = [
      {
        dataField: "id",
        text: "Order ID",
        filter: textFilter(),
      },
      {
        dataField: "weight",
        text: "Weight",
        filter: textFilter(),
      },
      {
        dataField: "quantity",
        text: "Quantity",
        filter: textFilter(),
      },
      {
        dataField: "length",
        text: "Length",
        filter: textFilter(),
      },
      {
        dataField: "height",
        text: "Height",
        filter: textFilter(),
      },
      {
        dataField: "additionalInformation",
        text: "Additional Information",
        filter: textFilter(),
      },
      {
        dataField: "itemName",
        text: "Item Name",
        filter: textFilter(),
      },
      {
        dataField: "itemWidth",
        text: "Item Width",
        filter: textFilter(),
      },
      {
        dataField: "itemCategory",
        text: "Item Category",
        filter: textFilter(),
      },

      {
        dataField: "totalPrice",
        text: "Price",
        filter: textFilter(),
      },
      {
        dataField: "updated",
        text: "Date",
        filter: textFilter(),
      },
      {
        dataField: "status",
        text: "Status",
      },
      {
        dataField: "cancel",
        text: "Cancel",
      },
      {
        dataField: "modify",
        text: "Modify",
      },
    ];

    const columns1 = [
      {
        dataField: "id",
        text: "Order ID",
        filter: textFilter(),
      },
      {
        dataField: "weight",
        text: "Weight",
        filter: textFilter(),
      },
      {
        dataField: "itemWidth",
        text: "Item Width",
        filter: textFilter(),
      },
      {
        dataField: "quantity",
        text: "Quantity",
        filter: textFilter(),
      },
      {
        dataField: "length",
        text: "Length",
        filter: textFilter(),
      },
      {
        dataField: "height",
        text: "Height",
        filter: textFilter(),
      },
      {
        dataField: "itemName",
        text: "Item Name",
        filter: textFilter(),
      },
      {
        dataField: "itemCategory",
        text: "Item Category",
        filter: textFilter(),
      },

      {
        dataField: "totalPrice",
        text: "Price",
        filter: textFilter(),
      },
      {
        dataField: "updated",
        text: "Date",
        filter: textFilter(),
      },
      {
        dataField: "status",
        text: "Status",
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
                    <Link style={{ color: "black" }} to="/reservations">
                      <b>My reservations</b>
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

                    <Link style={{ color: "black" }} to="/account">
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
                    <Link style={{ color: "black" }} to="/billing">
                      {" "}
                      Billing
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
                    <Link style={{ color: "black" }} to="/login">
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
                }}
              >
                <p style={{ padding: "10px", marginTop: "25px" }}>
                  <b>My reservations</b> <br />
                  <br />
                  <span>
                    <Button
                      style={{
                        float: "right",
                        backgroundColor: "#991A00",
                        borderColor: "#991A00",
                      }}
                    >
                      <Link style={{ color: "white" }} to="/">
                        New reservation
                      </Link>
                    </Button>
                  </span>
                  <b>
                    {" "}
                    You can cancel or modify your order 48 hours before the
                    departure date of Truck driver
                  </b>
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
                    <b onClick={this.toggleActiveReservations}>
                      Active reservations (
                      {this.state.activeReservationsRowCount})
                    </b>
                  </Button>
                  <div
                    id="active"
                    style={{
                      overflow: "scroll",
                      display: this.state.displayActiveReservations
                        ? "block"
                        : "none",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleActiveReservations}
                      keyField="id"
                      data={this.state.activeReservations}
                      columns={columns}
                      filter={filterFactory()}
                      pagination={paginationFactory()}
                    />
                  </div>
                </div>

                <div className="col-lg-12">
                  <Button
                    style={{
                      backgroundColor: "white",
                      borderColor: "white",
                      color: "black",
                    }}
                  >
                    <b onClick={this.toggleArchieveReservations}>
                      Archieve reservations (
                      {this.state.archieveReservationsRowCount})
                    </b>
                  </Button>
                  <div
                    id="archieve"
                    style={{
                      overflow: "scroll",
                      display: this.state.displayArchieveReservations
                        ? "block"
                        : "none",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleArchieveReservations}
                      keyField="id"
                      data={this.state.archieveReservations}
                      columns={columns1}
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

export default Reservations;
