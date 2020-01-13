/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohammed.ayad
 */
@Entity
@Table(name = "delivered_packet")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DeliveredPacket.findAll", query = "SELECT d FROM DeliveredPacket d"),
		@NamedQuery(name = "DeliveredPacket.findByPacketId", query = "SELECT d FROM DeliveredPacket d WHERE d.packetId = :packetId"),
		@NamedQuery(name = "DeliveredPacket.findByLocation", query = "SELECT d FROM DeliveredPacket d WHERE d.location = :location"),
		@NamedQuery(name = "DeliveredPacket.findByKilometersNumber", query = "SELECT d FROM DeliveredPacket d WHERE d.kilometersNumber = :kilometersNumber"),
		@NamedQuery(name = "DeliveredPacket.findByBarcodePhotoPath", query = "SELECT d FROM DeliveredPacket d WHERE d.barcodePhotoPath = :barcodePhotoPath") })
public class DeliveredPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "packet_id")
	private Integer packetId;
	@Basic(optional = false)
	@Column(name = "location")
	private String location;
	@Basic(optional = false)
	@Column(name = "kilometers_number")
	private long kilometersNumber;
	@Basic(optional = false)
	@Column(name = "barcode_photo_path")
	private String barcodePhotoPath;
	@JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
	@ManyToOne(optional = false)
	private DeliveryDetails deliveryId;

	public DeliveredPacket() {
	}

	public DeliveredPacket(Integer packetId) {
		this.packetId = packetId;
	}

	public DeliveredPacket(Integer packetId, String location, long kilometersNumber, String barcodePhotoPath) {
		this.packetId = packetId;
		this.location = location;
		this.kilometersNumber = kilometersNumber;
		this.barcodePhotoPath = barcodePhotoPath;
	}

	public Integer getPacketId() {
		return packetId;
	}

	public void setPacketId(Integer packetId) {
		this.packetId = packetId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getKilometersNumber() {
		return kilometersNumber;
	}

	public void setKilometersNumber(long kilometersNumber) {
		this.kilometersNumber = kilometersNumber;
	}

	public String getBarcodePhotoPath() {
		return barcodePhotoPath;
	}

	public void setBarcodePhotoPath(String barcodePhotoPath) {
		this.barcodePhotoPath = barcodePhotoPath;
	}

	public DeliveryDetails getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(DeliveryDetails deliveryId) {
		this.deliveryId = deliveryId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (packetId != null ? packetId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DeliveredPacket)) {
			return false;
		}
		DeliveredPacket other = (DeliveredPacket) object;
		if ((this.packetId == null && other.packetId != null)
				|| (this.packetId != null && !this.packetId.equals(other.packetId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "truckTracker.DeliveredPacket[ packetId=" + packetId + " ]";
	}

}
