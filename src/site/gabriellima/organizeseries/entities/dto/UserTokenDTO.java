package site.gabriellima.organizeseries.entities.dto;

import site.gabriellima.organizeseries.entities.User;

public class UserTokenDTO {

	private User user;
	private String token;

	public UserTokenDTO() {

	}

	public UserTokenDTO(User user, String token) {
		this.user = user;
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
