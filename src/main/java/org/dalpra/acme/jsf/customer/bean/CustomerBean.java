package org.dalpra.acme.jsf.customer.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dalpra.acme.jsf.customer.entity.Customer;
import org.dalpra.acme.jsf.utility.Stato;
import org.primefaces.PrimeFaces;
import org.primefaces.showcase.domain.Product;
import org.testcontainers.shaded.com.github.dockerjava.core.RemoteApiVersion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class CustomerBean implements Serializable {
    private final Client client =
            ClientBuilder.newBuilder().build();
    private final String jaxrsResource = "http://localhost:8080/customers";
    private List<Customer> customerList;
    private Customer customer;
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dob;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Stato state;

    public CustomerBean() {
        customer = new Customer();
    }

    public List<Customer> getCustomerList() {

        customerList = client.target(jaxrsResource).request().get(new GenericType<List<Customer>>() {
        });
        return customerList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Stato getState() {
        return state;
    }

    public void setState(Stato state) {
        this.state = state;
    }

    public void saveCustomer() {
        if (this.customer.getId() == null) {
            Response response =
                    client.target(jaxrsResource)
                            .request(MediaType.APPLICATION_JSON)
                            .post(Entity.entity(customer, MediaType.APPLICATION_JSON),
                                    Response.class);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Added","Customer Add correctly"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Added","Error to add Customer"));
            }
        }
        else {
            //Customer tmpCustomer = client.target(jaxrsResource+"/"+customer.getId()).request().get().readEntity(Customer.class);

            Response response =
                    client.target(jaxrsResource)
                            .request(MediaType.APPLICATION_JSON)
                            .put(Entity.entity(customer, MediaType.APPLICATION_JSON),
                                    Response.class);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Update","Customer updated correctly"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Update","Error to update Customer"));
            }
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void openNew() {
        this.customer = new Customer();
    }
}
