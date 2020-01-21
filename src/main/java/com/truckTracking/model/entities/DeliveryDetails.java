/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohammed.ayad
 */
@Entity
@Table(name = "delivery_details")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DeliveryDetails.findAll", query = "SELECT d FROM DeliveryDetails d"),
		@NamedQuery(name = "DeliveryDetails.findByDeliveryId", query = "SELECT d FROM DeliveryDetails d WHERE d.deliveryId = :deliveryId"),
		@NamedQuery(name = "DeliveryDetails.findByRoadNumber", query = "SELECT d FROM DeliveryDetails d WHERE d.roadNumber = :roadNumber"),
		@NamedQuery(name = "DeliveryDetails.findByCarNumber", query = "SELECT d FROM DeliveryDetails d WHERE d.carNumber = :carNumber"),
		@NamedQuery(name = "DeliveryDetails.findByStartCarMeterReader", query = "SELECT d FROM DeliveryDetails d WHERE d.startCarMeterReader = :startCarMeterReader"),
		@NamedQuery(name = "DeliveryDetails.findByEndCarMeterReader", query = "SELECT d FROM DeliveryDetails d WHERE d.endCarMeterReader = :endCarMeterReader"),
		@NamedQuery(name = "DeliveryDetails.findByDeliveryStartTime", query = "SELECT d FROM DeliveryDetails d WHERE d.deliveryStartTime = :deliveryStartTime"),
		@NamedQuery(name = "DeliveryDetails.findByDeliveryEndTime", query = "SELECT d FROM DeliveryDetails d WHERE d.deliveryEndTime = :deliveryEndTime") })
public class DeliveryDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "delivery_id")
	private Integer deliveryId;
	@Basic(optional = false)
	@Column(name = "road_number")
	private int roadNumber;
	@Basic(optional = false)
	@Column(name = "car_number")
	private String carNumber;
	@Basic(optional = false)
	@Column(name = "start_car_meter_reader")
	private long startCarMeterReader;
	@Column(name = "end_car_meter_reader")
	private BigInteger endCarMeterReader;
	@Basic(optional = false)
	@Column(name = "delivery_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryStartTime;
	@Column(name = "delivery_end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryEndTime;
	@Column(name = "location_latitude")
	private String locationLatitude;
	@Column(name = "location_longitude")
	private String locationLongitude;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryId")
	private List<DeliveredPacket> deliveredPacketList;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false)
	private User userId;

	public DeliveryDetails() {
	}

	public DeliveryDetails(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public DeliveryDetails(Integer deliveryId, int roadNumber, String carNumber, long startCarMeterReader,
			Date deliveryStartTime) {
		this.deliveryId = deliveryId;
		this.roadNumber = roadNumber;
		this.carNumber = carNumber;
		this.startCarMeterReader = startCarMeterReader;
		this.deliveryStartTime = deliveryStartTime;
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public int getRoadNumber() {
		return roadNumber;
	}

	public void setRoadNumber(int roadNumber) {
		this.roadNumber = roadNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public long getStartCarMeterReader() {
		return startCarMeterReader;
	}

	public void setStartCarMeterReader(long startCarMeterReader) {
		this.startCarMeterReader = startCarMeterReader;
	}

	public BigInteger getEndCarMeterReader() {
		return endCarMeterReader;
	}

	public void setEndCarMeterReader(BigInteger endCarMeterReader) {
		this.endCarMeterReader = endCarMeterReader;
	}

	public Date getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Date deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Date getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Date deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public String getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	@XmlTransient
	public List<DeliveredPacket> getDeliveredPacketList() {
		return deliveredPacketList;
	}

	public void setDeliveredPacketList(List<DeliveredPacket> deliveredPacketList) {
		this.deliveredPacketList = deliveredPacketList;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (deliveryId != null ? deliveryId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DeliveryDetails)) {
			return false;
		}
		DeliveryDetails other = (DeliveryDetails) object;
		if ((this.deliveryId == null && other.deliveryId != null)
				|| (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
			return false;
		}
		return true;
	}

//	@Override
//	public String toString() {
//		return "truckTracker.DeliveryDetails[ deliveryId=" + deliveryId + " ]";
//	}

}
