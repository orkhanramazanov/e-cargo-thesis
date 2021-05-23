import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDollarSign, faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { ToastContainer, toast } from "react-toastify";
import axios from "../axiosinterceptor";
import history from "./browserhistory";
class ModifyReservation extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayPasswordForm: false,
      displayAccountForm: false,
      isBarrel: false,
      email: "",
      firstname: "",
      lastname: "",
      nationality: "",
      dob: "",
      birthplace: "",
      mothersname: "",
      id: "",
      idnumber: "",
      country: "",
      zipcode: "",
      city: "",
      street: "",
      phone: "",
      packageType: "",
      itemcategory: "",
      additionalinformation: "",
      itemname: "",
      itemlength: 0,
      itemweight: 0,
      itemheight: 0,
      itemquantity: 0,
      itemwidth: 0,
      payment: "",
      truckData: {},
      totalprice: 0,
      itemdiameter: 0,
      orderData: {},
      countryDb: [],
      options: [],
    };

    this.togglePasswordForm = this.togglePasswordForm.bind(this);
    this.toggleAccountForm = this.toggleAccountForm.bind(this);
  }
  togglePasswordForm() {
    this.setState({
      displayPasswordForm: !this.state.displayPasswordForm,
    });
  }

  toggleAccountForm() {
    this.setState({
      displayAccountForm: !this.state.displayAccountForm,
    });
  }

  calculatePrice = () => {
    if (
      this.state.packageType !== "" &&
      this.state.itemquantity >= 0 &&
      this.state.itemweight >= 0
    ) {
      if (this.state.packageType === "boxes") {
        const totalprice =
          this.state.itemweight *
          this.state.itemquantity *
          this.state.truckData.pricePerKg;
        this.setState({
          totalprice: totalprice,
        });
      } else if (this.state.packageType === "sacks") {
        const totalprice =
          this.state.itemweight *
          this.state.itemquantity *
          this.state.truckData.pricePerKg;
        this.setState({
          totalprice: totalprice,
        });
      } else if (this.state.packageType === "barrels") {
        const totalprice =
          this.state.itemweight *
          this.state.itemquantity *
          this.state.truckData.pricePerKg;
        this.setState({
          totalprice: totalprice,
        });
      }
    }
  };

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
        <option
          selected={this.state.country === country.name ? "selected" : ""}
          key={country.id}
          defaultValue={country.name}
        >
          {country.name}
        </option>
      );

      ss.push(s);
    });

    this.setState({
      options: ss,
    });
  };

  fetchTruckDetails = async (id) => {
    const response = await axios.get(
      `http://localhost:8080/auth/trucks/getTruckById/${id}`
    );

    if (response.status === 200) {
      this.setState({
        truckData: response.data,
      });
    } else {
      history.push("/");
    }
  };

  fetchOrderDetails = async (id) => {
    const response = await axios.get(
      `http://localhost:8080/auth/client/getOrderById/${id}`
    );

    if (response.status === 200) {
      this.setState({
        orderData: response.data,
        email: response.data.email,
        firstname: response.data.firstName,
        lastname: response.data.lastName,
        nationality: response.data.nationalityName,
        dob: response.data.dateofBirth,
        birthplace: response.data.birthPlace,
        mothersname: response.data.motherMaidenName,
        id: response.data.idType,
        idnumber: response.data.idNumber,
        country: response.data.residence,
        zipcode: response.data.zip,
        city: response.data.city,
        street: response.data.street,
        phone: response.data.phone,
        packageType: response.data.packageType,
        itemcategory: response.data.itemCategory,
        additionalinformation: response.data.additionalInformation,
        itemname: response.data.itemName,
        itemlength: response.data.itemLength,
        itemweight: response.data.itemWeight,
        itemheight: response.data.itemHeight,
        itemquantity: response.data.itemQuantity,
        itemwidth: response.data.itemWidth,
        payment: response.data.paymentType,
        totalprice: response.data.totalPrice,
        itemdiameter: response.data.itemDiameter,
      });
    } else {
      history.push("/");
    }
  };

  componentDidMount() {
    var day1 = new Date();
    day1.setFullYear(day1.getDate() - 16);

    var dd = day1.getDate();

    var mm = day1.getMonth() + 1;

    mm = "0" + mm;
    var yyyy = day1.getFullYear();

    day1 = 2000 + yyyy + "-" + mm + "-" + dd;

    this.setState({
      minDate: day1,
    });
    this.fetchTruckDetails(this.props.match.params.truckId);
    this.fetchOrderDetails(this.props.match.params.orderId);
    this.fetchAllCountriesInDB();
  }

  handlePackage = (e) => {
    if (e.target.name === "packageType" && e.target.value === "barrels") {
      this.setState({
        isBarrel: true,
        [e.target.name]: e.target.value,
      });
    } else if (
      e.target.name === "packageType" &&
      e.target.value !== "barrels"
    ) {
      this.setState({
        isBarrel: false,
        [e.target.name]: e.target.value,
      });
    }
    this.calculatePrice();
  };

  handleForm = (e) => {
    e.preventDefault();
    this.setState(
      {
        [e.target.name]: e.target.value,
      },
      () => {
        if (
          e.target.name === "itemweight" ||
          e.target.name === "itemheight" ||
          e.target.name === "itemlength" ||
          e.target.name === "itemdiameter" ||
          e.target.name === "phone" ||
          e.target.name === "itemquantity"
        ) {
          if (e.target.value <= 0) {
            this.setState(
              {
                [e.target.name]: 1,
              },
              () => {
                toast("Please enter positive number ,default changed to 1", {
                  type: "error",
                });
              }
            );
          }
        }
        if (
          e.target.name === "itemweight" &&
          e.target.value * this.state.itemquantity >
            this.state.truckData.freeSpace
        ) {
          toast("Item Space Cant go Above Current Capacity", {
            type: "error",
          });
          this.setState({
            itemweight: 0,
          });
        } else if (
          e.target.name === "itemquantity" &&
          e.target.value * this.state.itemweight >
            this.state.truckData.freeSpace
        ) {
          toast("Item Space Cant go Above Current Capacity", {
            type: "error",
          });
          this.setState({
            itemquantity: 0,
          });
        } else if (e.target.name === "city") {
          var matches = e.target.value.match(/\d+/g);
          if (matches != null) {
            toast("City Can't Contain Numbers", {
              type: "error",
            });
            this.setState({
              city: "",
            });
          } else {
            this.setState({
              city: e.target.value,
            });
          }
        }
        this.calculatePrice();
      }
    );
  };


  updateReservation = async (e) => {
    e.preventDefault();
    if (
      this.state.email === "" ||
      this.state.firstname === "" ||
      this.state.lastname === "" ||
      this.state.nationality === "" ||
      this.state.dob === "" ||
      this.state.birthplace === "" ||
      this.state.mothersname === "" ||
      this.state.id === "" ||
      this.state.idnumber === "" ||
      this.state.country === "" ||
      this.state.zipcode === "" ||
      this.state.city === "" ||
      this.state.street === "" ||
      this.state.phone <= 0 ||
      this.state.packageType === "" ||
      this.state.itemcategory === "" ||
      this.state.additionalinformation === "" ||
      this.state.itemname === "" ||
      this.state.itemweight <= 0 ||
      this.state.itemheight <= 0 ||
      this.state.itemquantity <= 0 ||
      this.state.payment === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      try {
        const body = {
          email: this.state.email,
          firstName: this.state.firstname,
          lastName: this.state.lastname,
          nationalityName: this.state.nationality,
          dateofBirth: this.state.dob,
          birthPlace: this.state.birthplace,
          motherMaidenName: this.state.mothersname,
          truckId: this.state.truckData.id,
          idNumber: this.state.idnumber,
          idType: this.state.id,
          residence: this.state.country,
          zip: this.state.zipcode,
          city: this.state.city,
          street: this.state.street,
          phone: this.state.phone,
          packageType: this.state.packageType,
          itemCategory: this.state.itemcategory,
          additionalInformation: this.state.additionalinformation,
          itemName: this.state.itemname,
          itemWeight: this.state.itemweight,
          itemHeight: this.state.itemheight,
          itemWidth: this.state.itemwidth,
          itemLength: this.state.itemlength,
          itemDiameter: this.state.itemdiameter,
          paymentType: this.state.payment,
          totalPrice: this.state.totalprice,
          itemQuantity: this.state.itemquantity,
          departureTime: this.state.truckData.departureTime,
          clientId: sessionStorage.getItem("id"),
          truckDriverId: this.state.truckData.truckDriverId,
        };
        const response = await axios.post(
          `http://localhost:8080/auth/client/updateOrder/${this.props.match.params.orderId}`,
          body
        );
        if (response.status === 200) {
          toast("Order Sent For Review", {
            type: "success",
          });

          setTimeout(function () {
            history.push("/account");
          }, 2000);
        } else {
          toast("An Error occured", {
            type: "error",
          });
        }
      } catch (err) {
        toast(err.response.data.message, {
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
            height: "250vh",
            minHeight: "250vh",
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
                    }}
                  >
                    Base Price /Day &nbsp;
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faDollarSign}
                    />
                    {this.state.truckData.pricePerKg}
                  </div>

                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    Total Price &nbsp;
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faDollarSign}
                    />
                    {this.state.totalprice}
                  </div>
                </div>
              </div>

              <div
                className="col-lg-8"
                style={{
                  marginLeft: "5px",
                  height: "fit-content",
                }}
              >
                <div style={{ background: "white", paddingBottom: "10px" }}>
                  <p style={{ padding: "10px" }}>
                    <b>Truck Name</b>{" "}
                  </p>
                  <hr />
                  <div className="input-group">
                    <div className="col-lg-4">
                      <img
                        className="img-responsive"
                        style={{ width: "inherit", height: "auto" }}
                        src={this.state.truckData.photo}
                        alt="truck"
                      />
                    </div>

                    <div
                      className="col-lg-7"
                      style={{
                        fontSize: "x-small",
                        fontFamily: "system-ui",
                      }}
                    >
                      <p>
                        <b>{this.state.truckData.truckName}</b>
                      </p>
                      <p>
                        {" "}
                        <b>Pickup -- </b> &nbsp;
                        {this.state.truckData.departureLocation}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b>Dropoff -- </b>{" "}
                        {this.state.truckData.arrivalLocation}
                      </p>
                      <p>
                        <b>Departure Time</b>{" "}
                        {this.state.truckData.departureTime}
                        &nbsp;&nbsp;
                        <b>Arrival Time</b> {this.state.truckData.arrivalTime}
                        &nbsp;
                      </p>{" "}
                      <p>
                        <b>Truck Type</b> {this.state.truckData.truckType}{" "}
                      </p>
                      <p>
                        <b>Free Space</b> &nbsp;{" "}
                        {this.state.truckData.freeSpace} KG
                      </p>
                      <hr
                        style={{
                          backgroundColor: "grey",
                          height: 1,
                        }}
                      />
                    </div>
                  </div>
                </div>

                <div style={{ background: "white", paddingBottom: "10px" }}>
                  <p
                    style={{
                      padding: "10px",
                      marginTop: "25px",
                      background: "rgb(227, 226, 226)",
                    }}
                  >
                    <b>Contact</b>{" "}
                  </p>
                  <div className="col-sm-6 col-md-6 align-self-start">
                    <label>
                      <b>Email</b>
                    </label>

                    <div className="input-group-prepend">
                      <span className="input-group-text" id="basic-addon">
                        <FontAwesomeIcon
                          style={{ borderRight: "none" }}
                          icon={faEnvelope}
                        />
                      </span>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Email"
                        defaultValue={this.state.orderData.email}
                        aria-label="Email"
                        disabled
                        name="email"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                </div>

                <div style={{ background: "white", paddingBottom: "10px" }}>
                  <p
                    style={{
                      padding: "10px",
                      marginTop: "25px",
                      background: "rgb(227, 226, 226)",
                    }}
                  >
                    <b>Client Data</b>{" "}
                  </p>
                  <div className="input-group">
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Last Name</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="Last Name"
                        aria-label="LastName"
                        defaultValue={this.state.orderData.lastName}
                        name="lastname"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>First Name</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="First Name"
                        aria-label="FirstName"
                        defaultValue={this.state.orderData.firstName}
                        name="firstname"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Nationality</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="Nationality"
                        name="nationality"
                        defaultValue={this.state.orderData.nationalityName}
                        aria-label="Nationality"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <br />

                  <div className="input-group">
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Date of birth</b>
                      </label>

                      <input
                        type="date"
                        className="form-control"
                        placeholder="dob"
                        name="dob"
                        max={this.state.minDate}
                        defaultValue={this.state.orderData.dateofBirth}
                        aria-label="dob"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Birthplace</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="Birth Place"
                        name="birthplace"
                        defaultValue={this.state.orderData.birthPlace}
                        aria-label="Birthplace"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Mother's full maiden name</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="Mother name"
                        name="mothersname"
                        defaultValue={this.state.orderData.motherMaidenName}
                        aria-label="Mothername"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <hr />

                  <div className="input-group">
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Id type</b>
                      </label>

                      <select
                        onChange={this.handleForm}
                        style={{ padding: "10px" }}
                        name="id"
                      >
                        <option defaultValue="">Select ID</option>
                        <option
                          selected={
                            this.state.orderData.idType === "EU Issued ID Card"
                              ? "selected"
                              : ""
                          }
                          defaultValue="EU Issued ID Card"
                        >
                          EU Issued ID Card
                        </option>
                        <option
                          selected={
                            this.state.orderData.idType === "Passport"
                              ? "selected"
                              : ""
                          }
                          defaultValue="Passport"
                        >
                          Passport
                        </option>
                      </select>
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Id number</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="Id number"
                        aria-label="Idnumber"
                        defaultValue={this.state.orderData.idNumber}
                        name="idnumber"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <hr />

                  <div className="input-group">
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Country of residence</b>
                      </label>

                      <select
                        onChange={this.handleForm}
                        style={{ padding: "10px" }}
                        name="country"
                      >
                        <option defaultValue="">Select Country</option>
                        {this.state.options}
                      </select>
                    </div>
                  </div>
                  <br />

                  <div className="input-group">
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Zip code</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="zip code"
                        aria-label="zipcode"
                        defaultValue={this.state.orderData.zip}
                        name="zipcode"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>City</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="city"
                        aria-label="city"
                        defaultValue={this.state.orderData.city}
                        name="city"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4 col-md-4 align-self-start">
                      <label>
                        <b>Street/Number</b>
                      </label>

                      <input
                        type="text"
                        className="form-control"
                        placeholder="street"
                        name="street"
                        defaultValue={this.state.orderData.street}
                        aria-label="street"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <br />
                  <div className="col-lg-4 col-md-4 align-self-start">
                    <label>
                      <b>Phone number</b>
                    </label>

                    <input
                      type="number"
                      className="form-control"
                      placeholder="phone"
                      aria-label="phone"
                      defaultValue={this.state.orderData.phone}
                      name="phone"
                      aria-describedby="basic-addon"
                      onChange={this.handleForm}
                    />
                  </div>
                </div>

                <br />
                <div style={{ background: "white", paddingBottom: "10px" }}>
                  <p style={{ padding: "10px" }}>
                    <b>Select Packaging Type</b>{" "}
                  </p>
                  <hr />
                  <div className="input-group">
                    <div className="col-lg-4">
                      <input
                        type="radio"
                        name="packageType"
                        value="boxes"
                        checked={
                          this.state.packageType === "boxes" ||
                          this.state.orderData.packageType === "boxes"
                            ? "checked"
                            : ""
                        }
                        onChange={this.handlePackage}
                      />

                      <img
                        src="../../box.jpg"
                        alt="boxes"
                        style={{ width: "auto", height: "100px" }}
                      />

                      <p>Boxes</p>
                    </div>
                    <div className="col-lg-4">
                      <input
                        onChange={this.handlePackage}
                        type="radio"
                        name="packageType"
                        value="sacks"
                        checked={
                          this.state.packageType === "sacks" ||
                          this.state.orderData.packageType === "sacks"
                            ? "checked"
                            : ""
                        }
                      />

                      <img
                        src="../../sack.jpg"
                        alt="sacks"
                        style={{ width: "auto", height: "100px" }}
                      />

                      <p>Sacks</p>
                    </div>
                    <div className="col-lg-4">
                      <input
                        onChange={this.handlePackage}
                        type="radio"
                        name="packageType"
                        value="barrels"
                        checked={
                          this.state.packageType === "barrels" ||
                          this.state.orderData.packageType === "barrels"
                            ? "checked"
                            : ""
                        }
                      />

                      <img
                        src="../../barrel.jpg"
                        style={{ width: "auto", height: "100px" }}
                        alt="barrels"
                      />

                      <p>Barrels</p>
                    </div>
                  </div>

                  <br />
                  <div style={{ background: "white", paddingBottom: "10px" }}>
                    <p
                      style={{
                        padding: "10px",
                        marginTop: "25px",
                        background: "rgb(227, 226, 226)",
                      }}
                    >
                      <b>Select Item Category</b>{" "}
                    </p>
                    <div className="col-sm-6 col-md-6 align-self-start">
                      <div className="">
                        <select
                          onChange={this.handleForm}
                          style={{ padding: "10px" }}
                          name="itemcategory"
                        >
                          <option defaultValue="">Select Item Category</option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Agriculture & Food"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Agriculture & Food"
                          >
                            Agriculture & Food
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Apparel,Textiles & Accessories"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Apparel,Textiles & Accessories"
                          >
                            Apparel,Textiles & Accessories
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Auto & Transportation"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Auto & Transportation"
                          >
                            Auto & Transportation
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Bags, Shoes & Accessories"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Bags, Shoes & Accessories"
                          >
                            Bags, Shoes & Accessories
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Electronics"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Electronics"
                          >
                            Electronics
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Electrical Equipment, Components & Telecoms"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Electrical Equipment, Components & Telecoms"
                          >
                            Electrical Equipment, Components & Telecoms
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Home, Lights & Construction"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Home, Lights & Construction"
                          >
                            Home, Lights & Construction
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Machinery, Industrial Parts & Tools"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Machinery, Industrial Parts & Tools"
                          >
                            Machinery, Industrial Parts & Tools
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Metallurgy, Chemicals, Rubber & Plastics"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Metallurgy, Chemicals, Rubber & Plastics"
                          >
                            Metallurgy, Chemicals, Rubber & Plastics
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory ===
                              "Packaging, Advertising & Office"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Packaging, Advertising & Office"
                          >
                            Packaging, Advertising & Office
                          </option>
                          <option
                            selected={
                              this.state.orderData.itemCategory === "Other"
                                ? "selected"
                                : ""
                            }
                            defaultValue="Other"
                          >
                            Other
                          </option>
                        </select>

                        <div>
                          <p
                            style={{
                              paddingTop: "7px",
                            }}
                          >
                            <b>Other addtional information :</b>
                          </p>
                          <textarea
                            onChange={this.handleForm}
                            rows="4"
                            cols="50"
                            defaultValue={
                              this.state.orderData.additionalInformation
                            }
                            name="additionalinformation"
                            placeholder="Add information here"
                          ></textarea>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="col-lg-12">
                    <p>
                      <b>Enter details</b>
                    </p>
                    <br />

                    <div className="input-group">
                      <div className="col-lg-4 col-md-4 align-self-start">
                        <label>
                          <b>Name</b>
                        </label>

                        <input
                          type="text"
                          className="form-control"
                          placeholder="name"
                          aria-label="name"
                          defaultValue={this.state.orderData.itemName}
                          name="itemname"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                      <div className="col-lg-4 col-md-4 align-self-start">
                        <label>
                          <b>Weight (kg)</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="weight"
                          aria-label="weight"
                          defaultValue={this.state.orderData.itemWeight}
                          name="itemweight"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                      <div className="col-lg-4 col-md-4 align-self-start">
                        <label>
                          <b>Quantity</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="quantity"
                          name="itemquantity"
                          aria-label="quantity"
                          defaultValue={this.state.orderData.itemQuantity}
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                      <br />

                      <div
                        className="col-lg-4 col-md-4 align-self-start"
                        style={{
                          display: this.state.isBarrel ? "none" : "block",
                        }}
                      >
                        <label>
                          <b>Length (mm)</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="length"
                          defaultValue={this.state.orderData.itemLength}
                          aria-label="length"
                          name="itemlength"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                      <div
                        className="col-lg-4 col-md-4 align-self-start"
                        style={{
                          display: this.state.isBarrel ? "none" : "block",
                        }}
                      >
                        <label>
                          <b>Width (mm)</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="width"
                          defaultValue={this.state.orderData.itemWidth}
                          aria-label="width"
                          name="itemwidth"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>

                      <div
                        className="col-lg-4 col-md-4 align-self-start"
                        style={{
                          display: this.state.isBarrel ? "block" : "none",
                        }}
                      >
                        <label>
                          <b>Diameter (mm)</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="diamter"
                          aria-label="length"
                          defaultValue={this.state.orderData.itemDiameter}
                          name="itemdiameter"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>

                      <div className="col-lg-4 col-md-4 align-self-start">
                        <label>
                          <b>Height (mm)</b>
                        </label>

                        <input
                          type="number"
                          className="form-control"
                          placeholder="height"
                          name="itemheight"
                          defaultValue={this.state.orderData.itemHeight}
                          aria-label="height"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                    </div>

                    <div style={{ background: "white", paddingBottom: "10px" }}>
                      <p
                        style={{
                          padding: "10px",
                          marginTop: "25px",
                          background: "rgb(227, 226, 226)",
                        }}
                      >
                        <b>Choose payment method</b>{" "}
                      </p>
                      <div className="col-sm-6 col-md-6 align-self-start">
                        <div className="">
                          <select
                            onChange={this.handleForm}
                            style={{ padding: "10px" }}
                            name="payment"
                          >
                            <option defaultValue="">Select payment</option>
                            <option
                              selected={
                                this.state.orderData.paymentType === "Pay later"
                                  ? "selected"
                                  : ""
                              }
                              defaultValue="Pay later"
                            >
                              Pay later (free cancellation)
                            </option>
                          </select>
                        </div>
                        <br />
                        <Button
                          onClick={this.updateReservation}
                          style={{
                            backgroundColor: "#991A00",
                            borderColor: "#991A00",
                          }}
                        >
                          Update Order
                        </Button>
                      </div>
                    </div>
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

export default ModifyReservation;
