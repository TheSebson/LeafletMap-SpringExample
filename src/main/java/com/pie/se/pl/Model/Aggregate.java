package com.pie.se.pl.Model;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Aggregate")
@EntityListeners(AuditingEntityListener.class)
public class Aggregate {
	public Aggregate() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String type;

	@OneToMany
	@JoinColumn(name = "owner_id")
	private List<Marker> markers;
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdAt;


	public Aggregate(String type) {
		this.type = type;
		this.markers = new ArrayList<>();
	}



	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addToList(Marker item){
		markers.add(item);
	}


	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



}
