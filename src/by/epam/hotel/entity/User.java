package by.epam.hotel.entity;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.epam.hotel.entity.enumeration.AccessLevel;

/**
 * The Class User.
 */
public class User implements Serializable, Cloneable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2342612600404618064L;

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(User.class);

	/** The id. */
	private int id;

	/** The login. */
	private String login;

	/** The password. */
	private int password;

	/** The email. */
	private String email;

	/** The name. */
	private String name;

	/** The first name. */
	private String firstName;

	/** The surname. */
	private String surname;

	/** The age. */
	private int age;

	/** The access. */
	private AccessLevel access;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		LOG.info("Create 'User' object.");
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the login.
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 * 
	 * @param login
	 *            the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public int getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param i
	 *            the new password
	 */
	public void setPassword(int i) {
		this.password = i;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 * 
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 * 
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the age.
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 * 
	 * @param age
	 *            the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the access.
	 * 
	 * @return the access
	 */
	public AccessLevel getAccess() {
		return access;
	}

	/**
	 * Sets the access.
	 * 
	 * @param access
	 *            the new access
	 */
	public void setAccess(AccessLevel access) {
		this.access = access;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + age;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + password;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		if (access != user.access)
			return false;
		if (age != user.age)
			return false;
		if (email == null) {
			if (user.email != null)
				return false;
		} else if (!email.equals(user.email))
			return false;
		if (firstName == null) {
			if (user.firstName != null)
				return false;
		} else if (!firstName.equals(user.firstName))
			return false;
		if (id != user.id)
			return false;
		if (login == null) {
			if (user.login != null)
				return false;
		} else if (!login.equals(user.login))
			return false;
		if (name == null) {
			if (user.name != null)
				return false;
		} else if (!name.equals(user.name))
			return false;
		if (password != user.password)
			return false;
		if (surname == null) {
			if (user.surname != null)
				return false;
		} else if (!surname.equals(user.surname))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(getClass().getSimpleName())
				.append(": id:").append(id).append(", login: ").append(login)
				.append(", password: ").append(password).append(", email: ")
				.append(email).append(", name:").append(name)
				.append(", firstName: ").append(firstName)
				.append(", secondName: ").append(surname).append(", age:")
				.append(age).append(", access: ").append(access).append(".")
				.toString();
	}
}
