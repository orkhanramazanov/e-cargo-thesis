import axios from "axios";

axios.interceptors.request.use(
  (request) => {
    if (
      !request.url.includes("signin") ||
      !request.url.includes("signup") ||
      request.url !== "https://restcountries.eu/rest/v2/all"
    ) {
      request.headers["Authorization"] = `Bearer ${sessionStorage.getItem(
        "token"
      )}`;
    }
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axios;
