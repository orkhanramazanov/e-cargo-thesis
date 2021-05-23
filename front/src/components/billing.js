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
class Billing extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      billsCount: 0,
      bills: [],
    };
  }

  componentDidMount() {
    this.fetchCompletedReservations();
  }

  fetchCompletedReservations = async () => {
    const response = await axios.get(
      `http://localhost:8080/auth/client/getCompletedOrders/${sessionStorage.getItem(
        "id"
      )}`
    );
    if (response.status === 200) {
      const reservations = [];

      response.data.map((reservation) => {
        const reserve = {
          id: reservation.id,
          itemName: reservation.itemName,
          totalPrice: reservation.totalPrice,
          date: reservation.updated,
        };
        reservations.push(reserve);
      });

      this.setState({
        billsCount: response.data.length,
        bills: reservations,
      });
    }
  };

  render() {
    const columns = [
      {
        dataField: "id",
        text: "Order ID",
        filter: textFilter(),
      },
      {
        dataField: "itemName",
        text: "Item Name",
        filter: textFilter(),
      },
      {
        dataField: "totalPrice",
        text: "Price",
        filter: textFilter(),
      },
      {
        dataField: "date",
        text: "Date",
        filter: textFilter(),
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
                    <Link style={{ color: "black" }} to="/reservations">
                      My reservations
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
                    <Link style={{ color: "black" }} to="/billing">
                      {" "}
                      <b> Billing</b>
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
                  <b>Previous Orders</b>{" "}
                </p>
                <hr />

                <div className="col-lg-12">
                  <BootstrapTable
                    onDataSizeChange={this.handleActiveReservations}
                    keyField="id"
                    data={this.state.bills}
                    columns={columns}
                    filter={filterFactory()}
                    pagination={paginationFactory()}
                  />
                </div>

                <br />
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Billing;
