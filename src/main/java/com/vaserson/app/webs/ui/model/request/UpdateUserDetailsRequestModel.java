package com.vaserson.app.webs.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	@NotNull(message="First Name cannot be empty")
	@Size(min=2, message="First Name cannot be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last Name cannot be empty")
	@Size(min=2, message="Last Name cannot be less than 2 characters")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
