package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private int id;
    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_in")
    private String createdIn;
    private String email;
    private String firstname;
    private String lastname;
    @JsonProperty("store_id")
    private int storeId;
    @JsonProperty("website_id")
    private int websiteId;
    private List<Address> addresses;
    @JsonProperty("disable_auto_group_change")
    private int disableAutoGroupChange;
    @JsonProperty("extension_attributes")
    private ExtensionAttributes extensionAttributes;

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public int getDisableAutoGroupChange() {
        return disableAutoGroupChange;
    }

    public ExtensionAttributes getExtensionAttributes() {
        return extensionAttributes;
    }

}
