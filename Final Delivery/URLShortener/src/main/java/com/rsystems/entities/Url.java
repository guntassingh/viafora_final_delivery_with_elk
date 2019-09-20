package com.rsystems.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.rsystems.utils.Constants;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "All details of URL in the application for a domain")
@Entity
public class Url implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = Constants.URL_CODE_SIZE)
	private String code;

	@Column(length = Constants.MAX_LONG_URL_SIZE)
	private String longUrl;

	@Column(length = Constants.MAX_LONG_URL_SIZE)
	private String customerId;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "url")
	private List<Statistic> statistics = new ArrayList<>();

	public Url() {

	}

	public Url(String code, String longUrl, String customerId) {
		super();
		this.code = code;
		this.longUrl = longUrl;
		this.customerId = customerId;
	}

	@PrePersist
	protected void prePersist() {
		if (this.createdAt == null)
			createdAt = new Date();
		if (this.updatedAt == null)
			updatedAt = new Date();
	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedAt = new Date();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Statistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
