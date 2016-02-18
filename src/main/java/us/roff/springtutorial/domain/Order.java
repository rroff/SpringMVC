package us.roff.springtutorial.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import us.roff.springtutorial.enums.OrderStatus;

@Entity
@Table(name="ordertable")
public class Order extends AbstractDomainObject {

	@OneToOne
	private Customer customer;
	
	@Embedded
	private Address shippingAddress;
	
	private OrderStatus orderStatus = OrderStatus.NEW;
	
	private Date dateShipped;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Address getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Date getDateShipped() {
		return dateShipped;
	}
	
	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public void addOrderDetail(OrderDetail orderDetail) {
		orderDetails.add(orderDetail);
		orderDetail.setOrder(this);
	}
	
	public void removeOrderDetail(OrderDetail orderDetail) {
		orderDetail.setOrder(null);
		orderDetails.remove(orderDetail);
	}
}
