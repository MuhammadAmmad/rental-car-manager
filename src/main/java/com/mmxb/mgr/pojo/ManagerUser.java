package com.mmxb.mgr.pojo;

public class ManagerUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager_user.enabled
     *
     * @mbggenerated
     */
    private Integer enabled;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.id
     *
     * @return the value of manager_user.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.id
     *
     * @param id the value for manager_user.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.username
     *
     * @return the value of manager_user.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.username
     *
     * @param username the value for manager_user.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.password
     *
     * @return the value of manager_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.password
     *
     * @param password the value for manager_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manager_user.enabled
     *
     * @return the value of manager_user.enabled
     *
     * @mbggenerated
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manager_user.enabled
     *
     * @param enabled the value for manager_user.enabled
     *
     * @mbggenerated
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}