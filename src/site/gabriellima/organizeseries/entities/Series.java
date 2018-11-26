package site.gabriellima.organizeseries.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Series implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private User user;
	private String name;
	private String description;
	private Date releaseDate;

	private Set<Genre> genres = new HashSet<>();

	public Series() {

	}

	public Series(Integer id, User user, String name, String description, Date releaseDate) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
	}

	public Series(User user, String name, String description, Date releaseDate) {
		super();
		this.user = user;
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Series [id=" + id + ", user=" + user + ", name=" + name + ", description=" + description
				+ ", releaseDate=" + releaseDate + "]";
	}
}
