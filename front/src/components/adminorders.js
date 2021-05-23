import { Button } from "react-bootstrap";
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSignOutAlt,
  faDatabase,
  faUser,
  faFile,
  faFlag,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import Header from "./header";
import axios from "../axiosinterceptor";
import { ToastContainer, toast } from "react-toastify";

class AdminOrders extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayActiveReservations: false,
      activeReservationsRowCount: 0,
      orderData: [],
    };

    this.toggleActiveReservations = this.toggleActiveReservations.bind(this);
  }

  fetchOrders = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/auth/admin/getAllOrders`
      );

      if (response.status === 200) {
        const orders = [];
        response.data.map((Order) => {
          const order = {
            id: Order.id,
            departureTime: Order.departureTime,
            paymentType: Order.paymentType,
            status: Order.status,
            itemHeight: Order.itemHeight,
            updated: Order.updated,
            firstName: Order.firstName,
            email: Order.email,
            idType: Order.idType,
            idNumber: Order.idNumber,
            residence: Order.residence,
            zip: Order.zip,
            street: Order.street,
            driverId: Order.truckDriverId,
            city: Order.city,
            phone: Order.phone,
            packageType: Order.packageType,
            itemCategory: Order.itemCategory,
            additionalInformation: Order.additionalInformation,
            itemName: Order.itemName,
            itemWeight: Order.itemWeight,
            itemQuantity: Order.itemQuantity,
            itemLength: Order.itemLength,
            itemDiameter: Order.itemDiameter,
            totalPrice: Order.totalPrice,
          };

          orders.push(order);
        });

        this.setState({
          orderData: orders,
          activeReservationsRowCount: response.data.length,
        });
      }
    } catch (err) {
      toast("Error occured while fetching data", {
        type: "error",
      });
    }
  };

  componentDidMount() {
    this.fetchOrders();
  }

  handleActiveReservations = ({ dataSize }) => {
    this.setState({ activeReservationsRowCount: dataSize });
  };

  toggleActiveReservations() {
    this.setState({
      displayActiveReservations: !this.state.displayActiveReservations,
    });
  }

  render() {
    const columns = [
      {
        dataField: "id",
        text: "Order ID",
      },
      {
        dataField: "firstName",
        text: "Client Name",
      },
      {
        dataField: "paymentType",
        text: "Payment Type",
      },
      {
        dataField: "email",
        text: "Email",
      },
      {
        dataField: "idType",
        text: "ID Type",
      },
      {
        dataField: "idNumber",
        text: "Id Number",
      },
      {
        dataField: "residence",
        text: "Residence",
      },
      {
        dataField: "zip",
        text: "Zip",
      },
      {
        dataField: "driverId",
        text: "Truck Driver Id",
      },
      {
        dataField: "street",
        text: "Street",
      },
      {
        dataField: "city",
        text: "City",
      },
      {
        dataField: "phone",
        text: "Phone",
      },
      {
        dataField: "packageType",
        text: "Packaging Type",
      },
      {
        dataField: "itemCategory",
        text: "Item Category",
      },
      {
        dataField: "itemName",
        text: "Item Name",
      },
      {
        dataField: "itemWeight",
        text: "Item Weight",
      },
      {
        dataField: "itemQuantity",
        text: "Item Quantity",
      },
      {
        dataField: "itemLength",
        text: "Item Length",
      },
      {
        dataField: "itemDiameter",
        text: "Item Diameter",
      },
      {
        dataField: "itemHeight",
        text: "Item Height",
      },
      {
        dataField: "totalPrice",
        text: "Total Price",
      },
      {
        dataField: "status",
        text: "Status",
      },
      {
        dataField: "updated",
        text: "Date Updated",
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
                    <Link style={{ color: "black" }} to="/admin/users">
                      View Users
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
                    <Link style={{ color: "black" }} to="/admin/orders">
                      <b>View Orders</b>
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
                className="col-lg-7"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                  overflow: "scroll",
                }}
              >
                <p style={{ padding: "10px", marginTop: "25px" }}>
                  <b>All Orders</b>{" "}
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
                      Orders ({this.state.activeReservationsRowCount})
                    </b>
                  </Button>
                  <div
                    id="active"
                    style={{
                      display: this.state.displayActiveReservations
                        ? "block"
                        : "none",
                    }}
                  >
                    <BootstrapTable
                      onDataSizeChange={this.handleActiveReservations}
                      keyField="id"
                      data={this.state.orderData}
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

export default AdminOrders;
