package com.orkhan.web.out.ecargo.controller;

import javax.validation.Valid;

import com.orkhan.web.out.ecargo.entity.Order;
import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.message.request.OrderSubmitForm;
import com.orkhan.web.out.ecargo.message.request.TruckDriverOrderForm;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.service.OrderService;
import com.orkhan.web.out.ecargo.service.TruckRequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orkhan.web.out.ecargo.util.Client;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class OrderController {

	final String clientUrl = Client.clientUrl;

	@Autowired
	private TruckRequestService service;

	@Autowired
	private OrderService orderService;



	@PostMapping("/driverCreateOrder/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> createOrder(@PathVariable("uid") Long uid, @Valid @RequestBody TruckDriverOrderForm truckDriverOrderForm) {

		try
		{
			Truck truck = new Truck();
			BeanUtils.copyProperties(truckDriverOrderForm, truck);
			truck = service.saveTruckOrder(truck,uid);
			return new ResponseEntity<Truck>(truck, HttpStatus.OK);
		}
		catch (Exception e)
		{
			ResponseMessage responseMessage = new ResponseMessage("Route Already Created");
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/driver/completeOrderById/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<ResponseMessage> completeOrderByDriver(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.completeOrderByDriver(uid), HttpStatus.OK);
	}

	@GetMapping("/driver/cancelOrderById/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<ResponseMessage> cancelOrderByDriver(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.cancelOrderByDriver(uid), HttpStatus.OK);
	}

	@GetMapping("/driver/acceptOrderById/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<ResponseMessage> acceptOrderByDriver(@PathVariable("uid") Long uid) throws Exception {
			return new ResponseEntity<>(orderService.acceptOrderByDriver(uid), HttpStatus.OK);
	}

	@GetMapping("/client/getActiveOrders/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getActiveOrders(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.getActiveOrders(uid), HttpStatus.OK);
	}

	@GetMapping("/admin/getAllOrders")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getAllOrders() throws Exception {
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}

	@GetMapping("/driver/getOrderByDriver/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getOrderByDriver(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.getOrdersByTruckDriver(uid), HttpStatus.OK);
	}

	@GetMapping("/driver/getCompletedOrdersByDriver/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getCompletedOrderByDriver(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.getCompletedOrdersByDriver(uid), HttpStatus.OK);
	}

	@GetMapping("/client/getOrderById/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<Optional<Order>>(orderService.getOrderById(uid), HttpStatus.OK);
	}

	@GetMapping("/client/getInActiveOrders/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getInactiveOrders(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.getInactiveOrders(uid), HttpStatus.OK);
	}

	@DeleteMapping("/client/cancelOrder/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<Order>cancelOrder(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.cancelOrder(uid), HttpStatus.OK);
	}

	@PostMapping("/client/updateOrder/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?>updateOrder(@PathVariable("uid") Long uid,@Valid @RequestBody OrderSubmitForm orderSubmitForm) throws Exception {

		try
		{
			Order order= new Order();
			BeanUtils.copyProperties(orderSubmitForm, order);
			return new ResponseEntity<>(orderService.updateOrder(uid,order), HttpStatus.OK);
		}
		catch (Exception e)
		{
			ResponseMessage responseMessage = new ResponseMessage("An error occured. Try Again");
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/client/getCompletedOrders/{uid}")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<List<Order>> getCompletedOrders(@PathVariable("uid") Long uid) throws Exception {
		return new ResponseEntity<>(orderService.getCompletedUsers(uid), HttpStatus.OK);
	}

	@PostMapping("/clientCreateOrder")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> createClientOrder(@Valid @RequestBody OrderSubmitForm orderSubmitForm) {

		try
		{
			Order order= new Order();
			BeanUtils.copyProperties(orderSubmitForm, order);
			order = orderService.addNewOrder(order);
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		}
		catch (Exception e)
		{
			ResponseMessage responseMessage = new ResponseMessage("An error occured. Try Again");
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);

		}
	}

}
